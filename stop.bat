@echo off
echo ===================================================
echo   Stopping Java Application and Infrastructure...
echo ===================================================

echo [1/2] Stopping Docker containers...
docker-compose down

echo [2/2] Cleanup completed.
echo Application infrastructure stopped successfully!
echo ===================================================
