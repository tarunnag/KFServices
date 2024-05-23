#! /bin/bash

API_IMAGE='tc-kfspring-api'
API_CONTAINER='tc-kfspring-api'
API_ENV='IS_Dev'
PORT=8080

echo "============================"
echo "= Stating  KF API V1 APP ="
echo "============================"

echo "Building and packaging war file..."
mvn clean package

echo "Creating war and properties..."
# /Users/baghelo/Desktop/Project/kfcpdvcs-srcs/kfservicesapi/src/main/resources/IS.properties
 
echo "Checking Dockerfile..."	
if [ -f Dockerfile ]; then
echo "Dockerfile not present.  writting simple for testing..."
/bin/cat <<EOM >Dockerfile
FROM tomcat:9.0
MAINTAINER Om Baghel <om.baghel@kornferry.com>
COPY target/v1.war /usr/local/tomcat/webapps/
COPY src/main/resources/IS.properties /usr/local/tomcat/lib
COPY src/main/resources/TalentArchitect.properties /usr/local/tomcat/lib
EXPOSE 8080 
EOM
fi

echo "Removing container..."
docker rm --force $API_CONTAINER

echo "Compiling the image of the app..."
docker build -t $API_IMAGE .
cd ..
echo "Stating app instance..."
docker run -d --name $API_CONTAINER  -p $PORT:8080  $API_IMAGE
echo "Sleeping 10s..."
sleep 10s
echo "Cleaning..."
cd kfservicesapi
mvn clean
# docker exec -it $API_CONTAINER /bin/bash