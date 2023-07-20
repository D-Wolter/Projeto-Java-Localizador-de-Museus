FROM maven:3-openjdk-17 AS build-image
# Define o diretório de trabalho para a construção da aplicação
WORKDIR /to-build-app
# Copia os arquivos necessários para o diretório de trabalho
COPY . .
# Executa a instalação das dependências usando o Maven
RUN mvn dependency:go-offline
# Constrói o pacote JAR utilizando o Maven com o goal package
RUN ./mvn clean package
# Segundo estágio: construção da imagem final
FROM eclipse-temurin:17-jre-alpine
# Define o diretório de trabalho para a execução da aplicação
WORKDIR /app
# Copia o arquivo JAR da aplicação do primeiro estágio para o diretório de trabalho
COPY --from=build-image /to-build-app/target/*.jar ./app/app.jar
# Expõe a porta 8080 para acesso externo
EXPOSE 8080
# Define o ponto de entrada (entrypoint) para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]