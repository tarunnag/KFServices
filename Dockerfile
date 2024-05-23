FROM java:8

MAINTAINER Om Baghel <om.baghel@kornferry.com>

ENV OB_WORKDIR    "/opt/kf/home"
ENV OB_PORT  "8081"

VOLUME /tmp
RUN mkdir -p ${OB_WORKDIR}
WORKDIR ${OB_PORT}
#ARG ALL JAR_FILE
#ADD target/${JAR_FILE} ./your-app.jar
#ADD /build/libs/*.jar /app/

ADD target/v1.jar ./v1.jar
 
CMD ["java", "-Xmx256m", "-jar", "/app/v1.jar"]

EXPOSE ${OB_PORT}
