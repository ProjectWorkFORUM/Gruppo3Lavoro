# =====================================================================
# Dockerfile dell'app Forum (Spring Boot + Thymeleaf)
#
# Multi-stage build:
#   - stage 1 "build":  compila il progetto con Maven e produce il fat JAR
#   - stage 2 (finale): solo JRE + il JAR -> immagine finale leggera
# =====================================================================

# -------- STAGE 1: build del JAR --------
FROM eclipse-temurin:25-jdk AS build
WORKDIR /workspace

# prima solo i file di build: cosi' Docker mette in cache le dipendenze
# e non le riscarica a ogni modifica del codice sorgente
COPY mvnw pom.xml ./
COPY .mvn .mvn
# sed: rimuove i fine riga Windows (CRLF) che romperebbero la shebang su Linux
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw && ./mvnw dependency:go-offline -q

# poi il codice e la build vera e propria (il JAR contiene anche i template Thymeleaf)
COPY src src
RUN ./mvnw package -DskipTests -q

# -------- STAGE 2: immagine finale (solo runtime) --------
FROM eclipse-temurin:25-jre
WORKDIR /app

# prendo solo il fat JAR prodotto nello stage di build
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
