FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

CMD ["java", "-jar", "target/*.jar"]