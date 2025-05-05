# Etapa 1: construir con mvnw
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY src/ src/

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Etapa 2: ejecutar
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar evaluacion.jar
ENTRYPOINT ["java", "-jar", "evaluacion.jar"]
