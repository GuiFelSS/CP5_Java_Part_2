# --- Estágio 1: Build da Aplicação com Maven ---
FROM maven:3-eclipse-temurin-21 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos de configuração do Maven e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e compila o projeto
COPY src ./src
RUN mvn clean package -DskipTests

# --- Estágio 2: Execução da Aplicação ---
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que a sua aplicação usa
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
