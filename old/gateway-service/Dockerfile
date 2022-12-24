FROM openjdk:17-jdk-slim
RUN addgroup --system spring && adduser --system spring -ingroup spring
USER spring:spring

ADD build/libs/gateway-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
