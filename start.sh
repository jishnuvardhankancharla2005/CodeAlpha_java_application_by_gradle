#!/bin/bash
echo "==================================================="
echo "  Starting Java Application and Infrastructure..."
echo "==================================================="

echo "[1/2] Launching Docker containers in background..."
docker-compose up -d --build

echo "[2/2] Waiting for application startup..."
sleep 6

echo "Opening application in web browser..."
if command -v xdg-open &> /dev/null; then
    xdg-open http://localhost:8085
elif command -v open &> /dev/null; then
    open http://localhost:8085
elif command -v start &> /dev/null; then
    start http://localhost:8085
fi

echo ""
echo "==================================================="
echo "  APPLICATION STARTED!"
echo "  Application URL: http://localhost:8085"
echo "==================================================="
