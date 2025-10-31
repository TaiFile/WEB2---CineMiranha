# 🎬 POOA Cinema API

Este projeto é parte do trabalho da disciplina de **Programação Orientada a Objetos Avançada (POOA)**.

## 📘 Descrição

API REST para gerenciamento de compra de ingressos em cinemas, desenvolvida com foco em estudos de arquitetura e boas práticas de desenvolvimento.

## 🧱 Arquitetura

* Arquitetura Hexagonal (Ports & Adapters)
* Princípios SOLID
* Padrões de projeto: Singleton, Injeção de Dependência e Inversão de Dependência
* Uso extensivo de interfaces para desacoplamento

## 🚀 Tecnologias Utilizadas

* Java 21
* Apache Maven
* Docker & Docker Compose (para o banco de dados)

## 🛠️ Como Executar o Projeto

### Pré-requisitos

* Java 21 instalado
* Apache Maven instalado
* Docker instalado

### Passos para execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/eduMalagutti/POOA-cinema-api.git
   cd POOA-cinema-api
   ```

2. Inicie o banco de dados com Docker Compose:

   ```bash
   docker-compose up -d
   ```

3. Compile e execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API em:

   ```
   http://localhost:8080
   ```



## 📂 Estrutura do Projeto

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── seu_pacote/
│   │   │           ├── adapters/
│   │   │           ├── application/
│   │   │           ├── domain/
│   │   │           └── infrastructure/
│   │   └── resources/
│   │       └── application.properties
├── docker-compose.yml
├── pom.xml
└── README.md
```



## 🧪 Testes

Para executar os testes automatizados:

```bash
mvn test
```
