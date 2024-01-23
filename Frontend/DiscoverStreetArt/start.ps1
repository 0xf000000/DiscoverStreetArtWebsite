$javaVersion = Get-Command java | Select-Object Version


echo $javaVersion