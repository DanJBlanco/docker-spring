FROM openjdk:17.0.2-slim as builder

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

FROM openjdk:17.0.2-slim

WORKDIR /app
RUN mkdir "./logs"
COPY --from=builder /app/msvc-users/target/msvc-users-0.0.1-SNAPSHOT.jar .
ENV PORT 8000
EXPOSE $PORT

CMD ["java", "-jar", "msvc-users-0.0.1-SNAPSHOT.jar"]


