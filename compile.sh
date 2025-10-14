#!/bin/bash
echo "Compiling Internship Placement Management System..."

mkdir -p bin

javac -d bin -sourcepath src \
    src/edu/ntu/ccds/sc2002/*.java \
    src/edu/ntu/ccds/sc2002/entity/*.java \
    src/edu/ntu/ccds/sc2002/control/*.java \
    src/edu/ntu/ccds/sc2002/boundary/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo "Run './run.sh' to start the application."
else
    echo "❌ Compilation failed!"
    exit 1
fi