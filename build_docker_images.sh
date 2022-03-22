#!/bin/bash

########################################################

## Shell Script to Build Docker Images

########################################################

echo "Build Planner Docker image"
docker build -t torrespro/planner:1.0.0 planner/
echo "Push Planner Docker image"
docker push torrespro/planner:1.0.0

echo "Build Server Docker image"
docker build -t torrespro/server:1.0.0 server/
echo "Push Server Docker image"
docker push torrespro/server:1.0.0

echo "Build TopoService Docker image using Jib"
mvn clean package -f toposervice/pom.xml

echo "Build WeatherService Docker image using Pack"
pack build torrespro/weatherservice:1.0.0 --path weatherservice/ --builder docker.io/paketobuildpacks/builder:base
echo "Push WeatherService Docker image"
docker push torrespro/weatherservice:1.0.0
