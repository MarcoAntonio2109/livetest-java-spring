FROM openjdk:21-jdk

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado para o container
ARG JAR_FILE=build/libs/app.jar
COPY ${JAR_FILE} app.jar

# Expõe a porta usada pela aplicação (ajuste conforme necessário)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]