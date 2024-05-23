## Software Requirements
```
Jdk 1.8 or higher
Maven latest
Tomcat 8.0 or higher
Eclipse latest
Git latest
```
# Add environment variables
```
create the below environment variables

JAVA_HOME  (ex: C:\Program Files\Java\jdk1.8.0_221)
MAVEN_HOME (ex: C:\Software\apache-maven-3.6.1)
```
# Clone the project repository
```
ex: git clone https://github.com/HayGroup/KFServices.git
```
# import project to Eclipse 

```
- open Eclipse

- select file->import->maven->Existing maven projects

- select browse-> path of your pom.xml in the project directory

- finish

```
# build project
```
Eclipse -> right click on project -> run as-> maven build

command line -> mvn clean install

```
# Add tomcat server
```

follow the instructions below link ( only Configuring the Server part)

https://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.stardust.docs.wst%2Fhtml%2Fwst-integration%2Fconfiguration.html

```

# deploy artifacts to tomcat server
```
right click on the server

select 'Add and Remove..'

select project artifacts on the left and select 'Add'

finish

Right click on the server and select 'Start'

```



### Run V1 API in docker 

# this will compile build package and create docker running your package.

# pre requisite have maven and docker  installed before running.


```
:kfservicesapi baghelo$ ./runAPITC.sh 
============================
= Stating  KF API V1 APP =
============================
Building and packaging war file...
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.haygroup:leapcommon >-----------------------
[INFO] Building HGLeapAPI 1.0.0.RELEASE
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ leapcommon ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ leapcommon ---
[INFO] 
[INFO] --- maven-compiler-plugin:3.2:compile (default-compile) @ leapcommon ---
[INFO] Compiling 134 source files to kfservicesapi/target/classes
[INFO] Packaging webapp
[INFO] Assembling webapp [leapcommon] in [kfservicesapi/target/v1]
[INFO] Processing war project
[INFO] Copying webapp resources [kfservicesapi/src/main/webapp]
[INFO] Webapp assembled in [278 msecs]
[INFO] Building war: /kfservicesapi/target/v1.war
[INFO] WEB-INF/web.xml already added, skipping
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.200 s
[INFO] Finished at: 2019-06-05T12:03:21-04:00
[INFO] ------------------------------------------------------------------------
Creating war and properties...
Dockerfile not present.  writting simple for testing...
Removing container...
tc-kfspring-api
Compiling the image of the app...
Sending build context to Docker daemon  57.71MB
Step 1/6 : FROM tomcat:9.0
 ---> 9547777f82a4
Step 2/6 : MAINTAINER Om Baghel <om.baghel@kornferry.com>
 ---> Using cache
 ---> b1bf4bff89d2
Step 3/6 : COPY target/v1.war /usr/local/tomcat/webapps/
 ---> 957487befd2b
Step 4/6 : COPY src/main/resources/IS.properties /usr/local/tomcat/lib
 ---> 480883bf7eb9
Step 5/6 : COPY src/main/resources/TalentArchitect.properties /usr/local/tomcat/lib
 ---> 2f2bcce6a6ff
Step 6/6 : EXPOSE 8080
 ---> Running in 8589209c1bb3
Removing intermediate container 8589209c1bb3
 ---> ec25897d3e83
Successfully built ec25897d3e83
Successfully tagged tc-kfspring-api:latest
Stating app instance...
afc57780013939b6fc76a122be32652a7b6e4c0f1988f499f66bb478e23ef8f4
Sleeping 10s...
Cleaning...
[INFO] --------------------------------[ cleaning war ]---------------------------------
[INFO] 
[INFO] Deleting kfservicesapi/target
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.263 s
[INFO] Finished at: 2019-06-05T12:03:36-04:00
[INFO] ------------------------------------------------------------------------
kfservicesapi baghelo$ 
```

Enabling TLS for Mongo connectivity
1. Added a flag in properties to set if TLS enabled on Server
2. When building MongoClient if TLS is enabled, same is applied on the client
3. Mongo server credentials are included when creating the client if available in configuration