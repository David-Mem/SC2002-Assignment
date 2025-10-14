@echo off
echo Starting Internship Placement Management System...
echo.

if not exist bin (
    echo Project not compiled. Running compile.bat first...
    call compile.bat
    echo.
)

java -cp bin edu.ntu.ccds.sc2002.Main