@echo off

echo Stop the Tomcat9
net stop Tomcat9

set "FILE=C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps_talentarchitect\v1.war"
set "FILE2=C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\v1.war"
set "BACKUP_FILE=C:\Backup\v1_%date:~-10,2%-%date:~-7,2%-%date:~-4,4%-%time:~0,2%:%time:~3,2%:%time:~6,2%.war"
set "NEW_FILE=C:\Backup\new_v1.war"
set "LOCATION=C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps_talentarchitect"
set "LOGS=C:\Program Files\Apache Software Foundation\Tomcat 9.0\logs\*.*" /F /Q"

IF exist "%FILE%" (echo found FILE for backup)

echo Backup the file from %LOCATION% to C:\Backup
IF exist "%NEW_FILE%" ( echo found NEW_FILE && COPY "%FILE%" "%BACKUP_FILE%" ) ELSE (echo no NEW_FILE to copy)

echo Deploying new file to %LOCATION%
COPY "%NEW_FILE%" "%FILE%"
COPY "%NEW_FILE%" "%FILE2%"

echo Removing logs
DEL %LOGS%

DEL "%NEW_FILE%"

echo Start Tomcat9
net start Tomcat9