FROM openjdk:8
EXPOSE 8080
ADD target/spring-boot-exchange-app.jar spring-boot-exchange-app.jar
ENTRYPOINT ["java","-jar","/spring-boot-exchange-app.jar"]