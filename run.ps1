$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    if (-not (Test-Path -LiteralPath 'database.properties')) {
        throw 'Run setup-database.ps1 first to configure the database.'
    }
    New-Item -ItemType Directory -Path 'build/classes' -Force | Out-Null
    $taskSources = @(Get-ChildItem 'src/studentmanagementsystem/*.java' | ForEach-Object { $_.FullName })
    & javac -encoding UTF-8 -cp 'lib/*' -d 'build/classes' $taskSources
    if ($LASTEXITCODE -ne 0) { throw 'Java compilation failed.' }
    Copy-Item -LiteralPath 'src/resources' -Destination 'build/classes' -Recurse -Force
    & java -cp 'build/classes;lib/*' studentmanagementsystem.Login
    if ($LASTEXITCODE -ne 0) { throw 'Application exited with an error.' }
} finally {
    Pop-Location
}
