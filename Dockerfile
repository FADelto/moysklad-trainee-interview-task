# First stage: complete build environment
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/
# package jar
RUN mvn clean package -DskipTests

#  Second stage: minimal runtime environment
FROM amazoncorretto:17-alpine3.19 as run
# copy jar from the first stage
COPY --from=builder target/*.jar myProducts.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myProducts.jar"]