#!/bin/bash
echo "Starting Internship Placement Management System..."
echo ""

if [ ! -d "bin" ]; then
    echo "Project not compiled. Running compile.sh first..."
    ./compile.sh
    echo ""
fi

java -cp bin edu.ntu.ccds.sc2002.Main