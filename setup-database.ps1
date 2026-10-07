$ErrorActionPreference = 'Stop'
$taskMysql = 'C:\Program Files\MySQL\MySQL Server 9.6\bin\mysql.exe'
if (-not (Test-Path -LiteralPath $taskMysql)) {
    throw "MySQL client not found: $taskMysql"
}
if ((Get-Service MySQL96).Status -ne 'Running') {
    throw 'Start MySQL first: open PowerShell as Administrator and run Start-Service MySQL96.'
}

$taskAdmin = Read-Host 'MySQL administrator username (Enter for root)'
if ([string]::IsNullOrWhiteSpace($taskAdmin)) { $taskAdmin = 'root' }
$taskSecurePassword = Read-Host 'MySQL administrator password (Enter if empty)' -AsSecureString
$taskCredential = [System.Net.NetworkCredential]::new('', $taskSecurePassword)
$taskPreviousPassword = $env:MYSQL_PWD
try {
    $env:MYSQL_PWD = $taskCredential.Password
    & $taskMysql --host=127.0.0.1 --port=3306 --user=$taskAdmin --connect-timeout=5 --execute='SELECT 1;' | Out-Null
    if ($LASTEXITCODE -ne 0) { throw 'MySQL login failed. Check the administrator username and password.' }

    # Generate a dedicated application account; administrator credentials are not saved.
    $taskSuffix = [Guid]::NewGuid().ToString('N').Substring(0, 8)
    $taskAppUser = "sms_app_$taskSuffix"
    $taskAppPassword = [Guid]::NewGuid().ToString('N') + [Guid]::NewGuid().ToString('N')
    $taskSql = Get-Content -LiteralPath (Join-Path $PSScriptRoot 'database.sql') -Raw
    $taskSql += "`nCREATE USER '$taskAppUser'@'localhost' IDENTIFIED BY '$taskAppPassword';"
    $taskSql += "`nGRANT SELECT, INSERT, UPDATE, DELETE ON student_management_system.* TO '$taskAppUser'@'localhost';"
    $taskSql | & $taskMysql --host=127.0.0.1 --port=3306 --user=$taskAdmin --default-character-set=utf8mb4
    if ($LASTEXITCODE -ne 0) { throw 'Database setup failed; local connection settings were not saved.' }

    $taskConfig = "db.url=jdbc:mysql://127.0.0.1:3306/student_management_system`ndb.user=$taskAppUser`ndb.password=$taskAppPassword`n"
    [System.IO.File]::WriteAllText((Join-Path $PSScriptRoot 'database.properties'), $taskConfig)
    $env:MYSQL_PWD = $taskAppPassword
    & $taskMysql --host=127.0.0.1 --port=3306 --user=$taskAppUser --database=student_management_system --execute='SELECT COUNT(*) AS users FROM users; SELECT COUNT(*) AS students FROM students;'
    if ($LASTEXITCODE -ne 0) { throw 'Application account verification failed.' }
    Write-Host 'Database ready. Connection settings saved to database.properties. Run the app and use Sign Up to create your first Admin account.'
} finally {
    $env:MYSQL_PWD = $taskPreviousPassword
    $taskCredential = $null
    $taskAppPassword = $null
    $taskConfig = $null
    $taskSql = $null
}
