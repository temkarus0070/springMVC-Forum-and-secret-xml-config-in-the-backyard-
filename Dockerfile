FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} myMvcApp.jar
ENTRYPOINT ["java","-jar","/myMvcApp.jar"]
