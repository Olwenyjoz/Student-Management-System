# Run from an Administrator PowerShell window. Uses MySQL's init-file method.
$ErrorActionPreference = 'Stop'
$taskIdentity = [Security.Principal.WindowsIdentity]::GetCurrent()
$taskPrincipal = [Security.Principal.WindowsPrincipal]::new($taskIdentity)
if (-not $taskPrincipal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    throw 'Open PowerShell using Run as Administrator, then run this script again.'
}
$taskBin = 'C:\Program Files\MySQL\MySQL Server 9.6\bin'
$taskIni = 'C:\ProgramData\MySQL\MySQL Server 9.6\my.ini'
foreach ($taskRequired in @("$taskBin\mysqld.exe", "$taskBin\mysql.exe", "$taskBin\mysqladmin.exe", $taskIni)) {
    if (-not (Test-Path -LiteralPath $taskRequired)) { throw "Missing file: $taskRequired" }
}
$taskService = Get-CimInstance Win32_Service -Filter "Name='MySQL96'"
if (-not $taskService -or $taskService.PathName -notlike '*MySQL Server 9.6*') {
    throw 'MySQL96 service does not match the expected installation.'
}

$taskSecure = Read-Host 'Choose a NEW MySQL root password' -AsSecureString
$taskConfirm = Read-Host 'Enter the NEW password again' -AsSecureString
$taskPassword = [Net.NetworkCredential]::new('', $taskSecure).Password
$taskConfirmation = [Net.NetworkCredential]::new('', $taskConfirm).Password
if ([string]::IsNullOrEmpty($taskPassword)) { throw 'The new password cannot be empty.' }
if ($taskPassword -cne $taskConfirmation) { throw 'Passwords did not match. Run the script again.' }
if ($taskPassword.IndexOfAny([char[]]@([char]0, [char]10, [char]13)) -ge 0) {
    throw 'The password cannot contain line breaks or null characters.'
}

$taskFolder = Join-Path ([IO.Path]::GetTempPath()) ('sms-mysql-reset-' + [Guid]::NewGuid().ToString('N'))
$taskInit = Join-Path $taskFolder 'reset.sql'
$taskOutput = Join-Path $taskFolder 'server-output.log'
$taskErrorLog = Join-Path $taskFolder 'server-error.log'
$taskOldEnvPassword = $env:MYSQL_PWD
$taskProcess = $null
$taskStopped = $false
$taskVerified = $false
try {
    New-Item -ItemType Directory -Path $taskFolder | Out-Null
    # Restrict the temporary password file and logs to this administrator.
    $taskAcl = [Security.AccessControl.DirectorySecurity]::new()
    $taskAcl.SetAccessRuleProtection($true, $false)
    $taskAcl.SetOwner($taskIdentity.User)
    $taskRule = [Security.AccessControl.FileSystemAccessRule]::new(
        $taskIdentity.User, 'FullControl', 'ContainerInherit, ObjectInherit', 'None', 'Allow')
    $taskAcl.AddAccessRule($taskRule)
    Set-Acl -LiteralPath $taskFolder -AclObject $taskAcl
    $taskQuotedPassword = $taskPassword.Replace("'", "''")
    $taskSql = "SET SESSION sql_mode='NO_BACKSLASH_ESCAPES';`nALTER USER 'root'@'localhost' IDENTIFIED BY '$taskQuotedPassword';`n"
    [IO.File]::WriteAllText($taskInit, $taskSql, [Text.UTF8Encoding]::new($false))

    Write-Host 'Stopping MySQL96 and resetting the root password...'
    Stop-Service MySQL96
    (Get-Service MySQL96).WaitForStatus('Stopped', [TimeSpan]::FromSeconds(30))
    $taskStopped = $true
    $taskArgs = @("--defaults-file=`"$taskIni`"", "--init-file=`"$($taskInit.Replace('\','/'))`"", '--bind-address=127.0.0.1', '--console')
    $taskProcess = Start-Process -FilePath "$taskBin\mysqld.exe" -ArgumentList $taskArgs -WindowStyle Hidden -PassThru -RedirectStandardOutput $taskOutput -RedirectStandardError $taskErrorLog
    $env:MYSQL_PWD = $taskPassword
    $taskDeadline = [DateTime]::UtcNow.AddSeconds(60)
    do {
        Start-Sleep -Seconds 1
        $taskProcess.Refresh()
        if ($taskProcess.HasExited) { throw "MySQL reset startup failed. Diagnostic logs are in $taskFolder. Do not share logs without checking for passwords." }
        # Failed probes are expected while the server is starting (Windows PowerShell 5).
        $ErrorActionPreference = 'Continue'
        & "$taskBin\mysql.exe" --host=127.0.0.1 --port=3306 --user=root --connect-timeout=2 --execute='SELECT 1;' 2>$null | Out-Null
        $taskProbeExit = $LASTEXITCODE
        $ErrorActionPreference = 'Stop'
        if ($taskProbeExit -eq 0) { $taskVerified = $true; break }
    } while ([DateTime]::UtcNow -lt $taskDeadline)
    if (-not $taskVerified) { throw "Could not verify the new password. Diagnostic logs are in $taskFolder." }
    Remove-Item -LiteralPath $taskInit -Force
    & "$taskBin\mysqladmin.exe" --host=127.0.0.1 --port=3306 --user=root --connect-timeout=5 shutdown
    if ($LASTEXITCODE -ne 0) { throw 'Could not shut down the temporary MySQL server normally.' }
    if (-not $taskProcess.WaitForExit(30000)) { throw 'MySQL shutdown is still in progress. Do not start a second server.' }
    Start-Service MySQL96
    (Get-Service MySQL96).WaitForStatus('Running', [TimeSpan]::FromSeconds(30))
    & "$taskBin\mysql.exe" --host=127.0.0.1 --port=3306 --user=root --connect-timeout=5 --execute='SELECT CURRENT_USER();'
    if ($LASTEXITCODE -ne 0) { throw 'Password was reset, but normal service login verification failed.' }
    Write-Host 'Password reset verified. MySQL96 is running. Run setup-database.ps1, use root, and enter your NEW password.'
} finally {
    if (Test-Path -LiteralPath $taskInit) { Remove-Item -LiteralPath $taskInit -Force }
    if ($taskProcess) {
        $taskProcess.Refresh()
        if (-not $taskProcess.HasExited) {
            & "$taskBin\mysqladmin.exe" --host=127.0.0.1 --port=3306 --user=root --connect-timeout=5 shutdown 2>$null | Out-Null
            [void]$taskProcess.WaitForExit(30000)
            $taskProcess.Refresh()
        }
    }
    if ($taskStopped -and (-not $taskProcess -or $taskProcess.HasExited)) {
        if ((Get-Service MySQL96).Status -eq 'Stopped') { Start-Service MySQL96 }
    } elseif ($taskProcess -and -not $taskProcess.HasExited) {
        Write-Warning "Temporary MySQL process $($taskProcess.Id) is still running. Do not start MySQL96 until it has been shut down. Logs: $taskFolder"
    }
    $env:MYSQL_PWD = $taskOldEnvPassword
    $taskPassword = $null
    $taskConfirmation = $null
    $taskQuotedPassword = $null
    $taskSql = $null
}
