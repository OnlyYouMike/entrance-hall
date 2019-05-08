# 基础镜像
FROM openjdk:8-jdk-alpine

# 对应pom.xml文件中的dockerfile-maven-plugin插件JAR_FILE的值
ARG JAR_FILE

COPY ${JAR_FILE} /opt/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/app.jar"]
