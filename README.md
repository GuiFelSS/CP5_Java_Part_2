# CP5 - Brinquedos Esportivos (Parte 2)

Este projeto é a segunda parte do Checkpoint 5, focado na implementação de segurança (Spring Security) para uma aplicação de loja de brinquedos esportivos desenvolvida em Java com Spring Boot.

## Descrição

A aplicação gerencia brinquedos esportivos, permitindo operações CRUD (Criar, Ler, Atualizar, Deletar) e inclui um sistema de autenticação e autorização utilizando Spring Security. A interface é construída com Thymeleaf.

## Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3.5.6**
*   **Spring Security**
*   **Spring Data JPA**
*   **Thymeleaf**
*   **Maven**
*   **Oracle Database** (via JDBC)
*   **Lombok**

## Pré-requisitos

Para executar este projeto, você precisará ter instalado:

*   JDK 21 ou superior
*   Maven
*   Um banco de dados Oracle acessível (ou configurar para outro banco de dados compatível com Spring Data JPA)

## Como Executar Localmente

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/GuiFelSS/CP5_Java_Part_2.git
    cd cp5-Brinquedos-esportivos
    ```

2.  **Configure o banco de dados:**

    Certifique-se de que seu banco de dados Oracle esteja acessível e crie um usuário e esquema, se necessário. As configurações do banco de dados são lidas de variáveis de ambiente ou do arquivo `application.properties`.

    Para execução local, você pode criar um arquivo `application-local.properties` (ou similar) ou definir as variáveis de ambiente diretamente no seu sistema:

    ```properties
    spring.datasource.url=jdbc:oracle:thin:@<SEU_HOST_ORACLE>:<PORTA>:<SID>
    spring.datasource.username=<SEU_USUARIO>
    spring.datasource.password=<SUA_SENHA>
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
    spring.jpa.properties.hibernate.format_sql=true
    ```

3.  **Construa o projeto:**

    ```bash
    mvn clean install
    ```

4.  **Execute a aplicação:**

    ```bash
    java -jar target/cp5-Brinquedos-esportivos-0.0.1-SNAPSHOT.jar
    ```

    A aplicação estará disponível em `http://localhost:8080`.

## Como Fazer Deploy no Render

Este projeto está configurado para facilitar o deploy no Render utilizando Docker.

### 1. Dockerfile

Um `Dockerfile` foi adicionado à raiz do projeto para criar uma imagem Docker da aplicação:

```dockerfile
FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### 2. Configuração do `application.properties`

O arquivo `src/main/resources/application.properties` foi modificado para utilizar variáveis de ambiente para as credenciais do banco de dados, o que é uma prática de segurança recomendada para ambientes de produção:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.jpa.properties.hibernate.format_sql=true
```

### 3. Passos para Deploy no Render

1.  **Faça o upload do seu projeto** para um repositório Git (GitHub, GitLab, Bitbucket).
2.  No painel do Render, **crie um novo `Web Service`**.
3.  **Conecte seu repositório Git** ao Render.
4.  **Configure as variáveis de ambiente** no Render para o seu serviço. Você precisará definir as seguintes variáveis, que serão usadas pelo Spring Boot para conectar ao seu banco de dados Oracle:
    *   `DATABASE_URL`: URL de conexão com seu banco de dados Oracle (ex: `jdbc:oracle:thin:@seu_host_oracle:1521:seu_sid`)
    *   `DATABASE_USERNAME`: Nome de usuário do banco de dados
    *   `DATABASE_PASSWORD`: Senha do banco de dados
5.  **Build Command (Comando de Construção):** O Render deve detectar automaticamente que é um projeto Maven. Se não, defina-o como:
    ```bash
    mvn clean install -DskipTests
    ```
6.  **Start Command (Comando de Inicialização):** O Render usará o `ENTRYPOINT` do Dockerfile. Se você não estiver usando Docker, o comando seria:
    ```bash
    java -jar target/*.jar
    ```
7.  **Deploy:** Inicie o deploy. O Render construirá a imagem Docker e executará sua aplicação.

## Estrutura do Projeto

```
.├── .mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── fiap
│   │   │               └── cp5_Brinquedos_esportivos
│   │   │                   ├── Cp5BrinquedosEsportivosApplication.java
│   │   │                   ├── config
│   │   │                   │   └── SecurityConfig.java
│   │   │                   ├── controller
│   │   │                   │   ├── AuthController.java
│   │   │                   │   └── BrinquedoController.java
│   │   │                   ├── model
│   │   │                   │   ├── Brinquedo.java
│   │   │                   │   └── Usuario.java
│   │   │                   ├── repository
│   │   │                   │   ├── BrinquedoRepository.java
│   │   │                   │   └── UsuarioRepository.java
│   │   │                   └── service
│   │   │                       └── UsuarioService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── templates
│   │           ├── brinquedos.html
│   │           ├── form-brinquedo.html
│   │           ├── index.html
│   │           ├── login.html
│   │           └── registrar.html
│   └── test
│       └── java
│           └── br
│               └── com
│                   └── fiap
│                       └── cp5_Brinquedos_esportivos
│                           └── Cp5BrinquedosEsportivosApplicationTests.java
├── Dockerfile
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Por favor, siga as boas práticas de desenvolvimento e crie pull requests para quaisquer melhorias ou correções de bugs.
