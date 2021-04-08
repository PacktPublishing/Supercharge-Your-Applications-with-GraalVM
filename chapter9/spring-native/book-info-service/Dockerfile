FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bookinfo.jar
ENTRYPOINT ["java","-jar","/bookinfo.jar"]