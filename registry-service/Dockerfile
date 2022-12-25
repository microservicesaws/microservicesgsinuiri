FROM eclipse-temurin:17-jdk-jammy
RUN addgroup --system spring && adduser --system spring -ingroup spring
USER spring:spring

ADD target/registry-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]


# FROM eclipse-temurin:17-jdk-jammy

# WORKDIR /app

# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
# RUN ./mvnw dependency:resolve

# COPY src ./src

# CMD ["./mvnw", "spring-boot:run"]