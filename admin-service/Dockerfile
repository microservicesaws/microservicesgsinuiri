FROM eclipse-temurin:17-jdk-jammy
RUN addgroup --system spring && adduser --system spring -ingroup spring
USER spring:spring

ADD target/admin-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
