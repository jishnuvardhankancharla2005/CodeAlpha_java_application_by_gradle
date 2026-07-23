@echo off
echo ===================================================
echo   Starting Java Application and Infrastructure...
echo ===================================================

echo [1/2] Launching Docker containers in background...
docker-compose up -d --build

echo [2/2] Waiting for application startup...
ping 127.0.0.1 -n 6 >nul

echo Opening application in web browser...
start http://localhost:5173

echo.
echo ===================================================
echo   APPLICATION STARTED!
echo   Application URL: http://localhost:5173
echo ===================================================
