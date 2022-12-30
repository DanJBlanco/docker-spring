FROM --platform=linux/x86_64 openjdk:17-jdk-alpine3.14 as builder

WORKDIR /app/msvc-users

COPY ./pom.xml /app
COPY ./msvc-users/.mvn ./.mvn
COPY ./msvc-users/mvnw .
COPY ./msvc-users/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
# RUN ./mvnw dependency:go-offline

COPY ./msvc-users/src ./src

RUN ./mvnw clean package -DskipTests

#============#

FROM --platform=linux/x86_64 openjdk:17-jdk-alpine3.14

WORKDIR /app

COPY --from=builder/ /app/msvc-users/target/msvc-users-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "msvc-users-0.0.1-SNAPSHOT.jar"]


