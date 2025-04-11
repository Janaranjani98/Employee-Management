FROM openjdk:21-jdk-slim
ADD build/libs/myapp.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]