FROM openjdk:17.0.2-slim as builder

WORKDIR /app/msvc-courses

COPY ./pom.xml /app
COPY ./msvc-courses/.mvn ./.mvn
COPY ./msvc-courses/mvnw .
COPY ./msvc-courses/pom.xml .


# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./msvc-courses/src ./src

RUN ./mvnw clean package -DskipTests

#============#

FROM openjdk:17.0.2-slim

WORKDIR /app
RUN mkdir "./logs"

COPY --from=builder /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD sleep 10 && java -jar msvc-courses-0.0.1-SNAPSHOT.jar

#CMD ["java", "-jar", "msvc-courses-0.0.1-SNAPSHOT.jar"]


