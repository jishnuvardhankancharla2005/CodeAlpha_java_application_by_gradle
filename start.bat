@echo off
echo ===================================================
echo   Starting Java Application and Infrastructure...
echo ===================================================

echo [1/3] Packaging Spring Boot executable Jar...
call gradle bootJar --no-daemon

echo [2/3] Launching Docker containers in background...
docker-compose up -d --build

echo [3/3] Waiting for application startup...
ping 127.0.0.1 -n 6 >nul

echo Opening application in web browser...
start http://localhost:8085

echo.
echo ===================================================
echo   APPLICATION STARTED!
echo   Application URL: http://localhost:8085
echo ===================================================
