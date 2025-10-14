@echo off
echo Compiling Internship Placement Management System...

if not exist bin mkdir bin

javac -d bin -sourcepath src ^
    src\edu\ntu\ccds\sc2002\*.java ^
    src\edu\ntu\ccds\sc2002\entity\*.java ^
    src\edu\ntu\ccds\sc2002\control\*.java ^
    src\edu\ntu\ccds\sc2002\boundary\*.java

if %ERRORLEVEL% EQU 0 (
    echo ✅ Compilation successful!
    echo Run 'run.bat' to start the application.
) else (
    echo ❌ Compilation failed!
    exit /b 1
)