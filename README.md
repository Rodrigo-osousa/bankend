
## Título e Imagem de Capa
<p align="center">
  <img src="https://user-images.githubusercontent.com/79177544/159943471-ab55c6eb-2eb3-441d-9f06-fbaaf69d67ca.jpg" />
</p>

## Índice 

* [Título e Imagem de capa](#Título-e-Imagem-de-capa)
* [Índice](#índice)
* [Descrição do Projeto](#descrição-do-projeto)
* [Status do Projeto](#status-do-Projeto)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Pessoa Desenvolvedora do Projeto](#pessoas-desenvolvedoras)
* [Conclusão](#conclusão)

## Descrição do Projeto
<p> Este projeto se trata de banco digital desenvolvido com o intuito de aplicar os estudos de algumas tecnologias estudadas.
Nele podemos cadastrar um cliente e uma conta, onde são salvas no banco de dados relacional.
</p>

<p>Um cliente possui os seguintes atributos:</p>

```
{
  "address": "string",
  "documentNumber": "string",
  "id": 0,
  "name": "string"
}
```
 <p> Uma conta é composto de:</p>
 
```
{
  "accountNumber": "string",
  "agency": 0,
  "balance": 0,
  "credit": 0,
  "documentNumber": "string",
  "inactive": true
}
```



## Status do projeto
<p>
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

## Funcionalidades e Demonstração da Aplicação
<p>Como se trata de uma aplicação CRUD, podemos criar, apagar, modificar ou deletar um cliente ou uma conta.</p>
<p>
  Com o Swagger podemos visualizar estas funções de forma dinâmica:</p>
  
  ![Aplicação Swagger](https://user-images.githubusercontent.com/79177544/160010999-d40ef0f0-4b0a-4e71-8e75-b7725c955664.gif)

## Acesso ao Projeto
***  **Iniciando a aplicação sem Docker Compose**  ***
  
- Faça o Download da aplicação e a descompacte;
- Abra a aplicação com a IDEA de sua preferência;
- Aguarde o Download dos plugis necessários;
- Inicie a Aplicação;
- Após a Inicialização acesso o link http://localhost:8080/swagger-ui.html#/
  
 ***  **Iniciando a aplicação com o Docker Compose**   ***
  
 - Neste caso precisamos modificar uma configuração no aplication.properties do projeto:
 - Substititua o ```spring.datasource.url=jdbc:postgresql://localhost:5432/bankend``` por ```spring.datasource.url=jdbc:postgresql://postgres-compose:5432/bankend```;
 - A substituição é necessária pois o PostgreSQL estará rodando no Docker e não na máquina local;
 - Na pasta raiz do projeto abra o terminal e execute o comando ```mvn clean package```;
 - Com a Build montada execute ```docker compose up```;
 - Após a Inicialização acesso o link http://localhost:8080/swagger-ui.html#/

## Tecnologias utilizadas

<img align="center" alt="Rodrigo-Java" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg"> - Java 11;

<img align="center" alt="Rodrigo-Docker" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original-wordmark.svg"> - Docker;

<img align="center" alt="Rodrigo-Spring" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg"> - Spring Boot;

<img align="center" alt="Rodrigo-Post" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg"> - PostgreSQL; 
 
## Pessoa Desenvolvedora do Projeto
| [<img src="https://avatars.githubusercontent.com/u/79177544?s=96&v=4" width=115><br><sub>Rodrigo Oliveira Sousa</sub>](https://www.linkedin.com/in/rodrigo-oliveira-sousa/) | 
| :---: |
 
 
 ## Conclusão
 
 Esta aplicação tem apenas a função de realizar cadastro de clientes e contas, a aplicação [BankEndDebits](https://github.com/Rodrigo-osousa/bankEndDebits) tem acesso aos dados dos clientes e das contas e realizar realizar transações finaceiras.
  
  
  
