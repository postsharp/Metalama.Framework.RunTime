(& dotnet nuget locals http-cache -c) | Out-Null
& dotnet run --project "$PSScriptRoot\eng\src\BuildMetalamaFrameworkRunTime.csproj" -- $args
exit $LASTEXITCODE

