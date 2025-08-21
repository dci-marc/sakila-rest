#!/bin/bash

SWD="$(dirname "$(readlink -f "${BASH_SOURCE[0]}")")"
VERSION=$(grep -A1 '<artifactId>sakila-rest</artifactId>' ${SWD}/pom.xml | grep '<version>' | sed 's/.*<version>\(.*\)<\/version>.*/\1/')

mkdir -p ${SWD}/bin
docker create --name temp sakila-rest:${VERSION:-latest}
docker cp temp:/workspace/org.dcistudent.sakilarest.SakilaRestApplication ${SWD}/bin/sakila-rest-native
docker rm temp
chmod +x ${SWD}/bin/sakila-rest-native
