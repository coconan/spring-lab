#!/bin/bash
mvn -s .mvn/settings.xml clean install
mvn -s .mvn/settings.xml package
java -jar target/hello-1.0.0-SNAPSHOT.jar

