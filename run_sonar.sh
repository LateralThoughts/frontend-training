#!/bin/sh
mvn -f pom_sonar_js.xml sonar:sonar -Dsonar.branch=js -Dsonar.language=js -Dsonar.exclusions='**/vendor/**'
