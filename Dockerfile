# --- Etapa 1: Compilación ---
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# Copiar el pom.xml y descargar las dependencias (se cachea si no cambia el pom)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el proyecto saltándose los tests
COPY src ./src
RUN mvn package -DskipTests

# --- Etapa 2: Imagen de ejecución ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el .jar generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto por el que escucha Spring Boot
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]