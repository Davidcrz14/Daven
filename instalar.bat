@echo off
echo ========================================
echo   INSTALANDO DAVEN EN TU DISPOSITIVO
echo ========================================
echo.

C:\Android\platform-tools\adb.exe devices
echo.
echo Instalando APK...
C:\Android\platform-tools\adb.exe install -r app\build\outputs\apk\debug\app-debug.apk

echo.
echo ========================================
echo   INSTALACION COMPLETADA
echo ========================================
pause
