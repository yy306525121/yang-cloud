FROM java:8-jre-alpine

COPY yang-auth-web.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=prod"]
