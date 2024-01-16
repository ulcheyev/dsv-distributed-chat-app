#!/bin/bash

if [ "$#" -gt 5 ]; then
    echo "Max is 5 args"
    exit 1
fi

MavenProjectPath="../"

args=""
for arg in "$@"; do
     args="$args $arg"
done

cd "$MavenProjectPath"

mvn clean install
mvn exec:java -Dexec.mainClass="cz.cvut.fel.dsv.Node" -Dexec.args="$args"
