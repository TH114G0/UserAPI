# API de Gerenciamento de Usuários (CRUD)

Esta é uma API REST para gerenciar Usuários, permitindo operações de **criação**, **leitura**, **atualização** e *
*exclusão** (CRUD). A API foi construída com as melhores práticas de desenvolvimento e pode ser facilmente expandida
para incluir mais funcionalidades.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para criação rápida de aplicações Java.
- **Spring MVC**: Para facilitar a implementação da arquitetura REST.
- **Spring Data JPA**: Para interagir com o banco de dados usando Java Persistence API (JPA).
- **SpringDoc OpenAPI 3**: Geração automática da documentação da API com Swagger.
- **MariaDB**: Banco de dados relacional utilizado para armazenar as tarefas.
- **DBeaver**: Ferramenta para gerenciamento e visualização do banco de dados.

## Práticas Adotadas

- **SOLID**: Princípios de design para um código limpo e manutenível.
- **DRY** (Don't Repeat Yourself): Evitar duplicação de código.
- **YAGNI** (You Aren't Gonna Need It): Focar no que é realmente necessário.
- **KISS** (Keep It Simple, Stupid): Manter a simplicidade e clareza no código.
- **API REST**: Interface de comunicação com recursos acessíveis via HTTP.
- **Consultas com Spring Data JPA**: Eficiência e simplicidade ao acessar dados no banco de dados.
- **Injeção de Dependências**: Facilita a criação e o gerenciamento de objetos.
- **Tratamento de Erros**: Respostas de erro bem definidas para uma boa experiência de usuário.
- **Swagger OpenAPI 3**: Geração automática de documentação interativa da API.

## Como Executar

### 1. Clonar o Repositório

Clone este repositório para o seu ambiente local:

```bash
git clone <URL do repositório>
cd <diretório do projeto>
```

## Construir o Projeto

Para compilar o projeto, execute o comando:

```bash
./mvnw clean package
```

## Executar a Aplicação

Após a construção do projeto, execute a aplicação com o comando:

```bash
java -jar target/todolist-0.0.1-snapshot.jar
```

## Acessar o Swagger

A documentação interativa da API pode ser acessada no seguinte endereço:

```bash
http://localhost:8080/swagger-ui.html
```

## Endpoints da API

Aqui estão os endpoints disponíveis para interação com a API:

- Criar Usuário
  ```bash
  
  Método: POST
  URL: http://localhost:8080/users
  
   {
      "name" : "João",
      "email": "joao@example.com"
   }
  ```
- Listar Usuário
  ```bash
    Método: GET
    URL: http://localhost:8080/users
  ```

- Atualizar Usuário
```bash
    Método: PUT
    URL: http://localhost:8080/users/{id}
```

- Remover Usuário
```bash
    Método: DELETE
    URL: http://localhost:8080/users/{id}
```

## Testando a API 
Para testar os endpoints, você pode utilizar a ferramenta Talend API Tester - Free Edition (extensão do Chrome). Ela permite fazer requisições HTTP de maneira fácil e visualizar as respostas diretamente no navegador.

## Conclusão
Com esta API, você pode gerenciar suas tarefas de forma eficiente e organizada. A implementação segue boas práticas de desenvolvimento, garantindo código limpo e fácil de manter. Se você deseja expandir a funcionalidade ou contribuir para o projeto, sinta-se à vontade para fazer um fork e enviar pull requests.


---

Este formato de README é padronizado e pronto para ser copiado para o seu projeto. Ele contém as seções essenciais, como descrição da API, tecnologias usadas, como executar o projeto, e os endpoints disponíveis.
