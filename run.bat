@echo off
rem Run the Ebank Login System
cd /d "%~dp0"
chcp 65001 >nul
java -Dfile.encoding=UTF-8 -cp bin ebank.presentation.Main
pause
