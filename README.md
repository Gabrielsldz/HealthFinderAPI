# HealthFinderAPI

## Descrição

O *HealthFinderAPI* é uma API desenvolvida em Java utilizando Spring Boot. Ela funciona como um wrapper para facilitar a busca de dados de estabelecimentos de saúde a partir de uma API pública do governo brasileiro. Com ela, você pode filtrar suas buscas por CEP e municípios, proporcionando um acesso rápido e eficiente a informações de estabelecimentos de saúde.

A aplicação também possui funcionalidades automáticas de atualização, onde substitui campos desatualizados, exclui estabelecimentos antigos e inclui novos, mantendo os dados sempre atualizados.

## Funcionalidades

- Busca de estabelecimentos de saúde por CEP ou município.
- Atualização automática dos dados dos estabelecimentos.
- Exclusão automática de estabelecimentos desatualizados e inclusão de novos.
- Integração com banco de dados MySQL.
- Documentação disponível via Swagger.

## Requisitos

- Java 11+
- Maven
- MySQL
- Docker (opcional, se preferir rodar o projeto e o banco de dados com Docker)

## Instalação

### 1. Clonando o repositório

Clone o projeto para a sua máquina local:

```bash
git clone https://github.com/Gabrielsldz/HealthFinderAPI.git
```

### 2. Configurando o banco de dados

No arquivo `application.properties`, configure a URL do seu banco de dados MySQL. Certifique-se de que o banco de dados esteja rodando no local especificado:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crud2
```

Altere `localhost:3306` para o path correto do seu banco de dados, caso necessário.

### 3. Instalando dependências

A aplicação usa Maven como gerenciador de pacotes. Para instalar todas as dependências listadas no arquivo `pom.xml`, navegue até a pasta raiz do projeto e execute o comando:

```bash
mvn clean install
```

### 4. Rodando a aplicação

Após instalar as dependências, você pode rodar a aplicação com o comando:

```bash
mvn spring-boot:run
```

Isso iniciará a aplicação no `localhost:8080`.

### 5. Usando Docker (opcional)

Se preferir rodar a aplicação e o banco de dados com Docker, siga estes passos:

1. Certifique-se de que o Docker está instalado e rodando na sua máquina.
2. Gere o package da aplicação com Maven:

```bash
mvn package
```

3. Em seguida, execute o `docker-compose` para rodar tanto a aplicação quanto o banco de dados:

```bash
docker-compose up --build
```

Isso irá configurar e rodar a aplicação e o banco de dados MySQL via Docker.

## Documentação

A documentação completa da API pode ser acessada diretamente no Swagger, que estará disponível após rodar a aplicação em:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

