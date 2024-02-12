

FOR /F "tokens=* USEBACKQ" %%F IN (`java -version`) DO (
SET var=%%F
)

echo.%var% | findstr /C: "17" > nul 2>&1

if  errorlevel 1 (

    echo "please install Java 17 :("
    exit     
)
powershell -Command "(New-Object Net.WebClient).DownloadFile('http://www.example.com/package.zip', 'package.zip')"









