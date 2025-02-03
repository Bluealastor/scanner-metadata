# Usa un'immagine di base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Imposta la cartella di lavoro nel container
WORKDIR /app

# Copia il file JAR generato nel container
COPY target/*.jar app.jar

# Espone la porta 8080
EXPOSE 8080

# Comando per avviare l'applicazione Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
