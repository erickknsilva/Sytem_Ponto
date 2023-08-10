<h1 align="center">
  Sistema de Bater Ponto
</h1>

API para registrar os pontos do funcionarios, encaminhando um email com as informações hora de entrada, almoço/retorno e saida 
se ele vor do tipo "mensalista". Agora se o funcionario vor do tipo "horista", o email deve conter a hora de entrada, quantidade
de horas trabalhas, salario do dia e o salario do mês até o momento. 
Objetivo é deixa os funcionarios cientes e confortavel, tendo o acesso do registro do seu ponto a qualquer momento, podendo consultar
sua jornada diária, semanal e mensal a hora que quiser. 


## Práticas adotadas

- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Tecnologias
 
- JavaScript
- HTML/CSS
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Mysql](https://dev.mysql.com/downloads/)


## Como Executar

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie]((https://www.postman.com/)),
Tambem foi utilizado uma aplicação Front-end consumindo os ENDPOINT da API disponibilizada, usando o Fetch API do JavaScript
e utilizando HTML/CSS para fornecer uma interface para o usuario.
Se desejar utilizar uma interface abra a pasta front-SystemPonto, no Visual Stuido Code, baixe a extensao Live serve e abra uma
guia HTML utilizando o "Live Server", é necessario estar conectado na internet, por causa que está sendo utilizado a biblioteca do 
[Fontawesome](https://fontawesome.com/start) e o [SweetAlert2](https://sweetalert2.github.io/), necessitando de internet para funcionar.

- ponto-controller
```

POST
/pontos
```
- funcionario-controller
```
GET
/funcionarios

POST
/funcionarios

POST
/funcionarios/atualizar/{matricula}

GET
/funcionarios/{page}/{size}

GET
/funcionarios/{matricula}

DELETE
/funcionarios/excluir/{matricula}
```

- departamento-controller

```
POST
/departamentos/salvar

POST
/departamentos/atualizar/{id}

GET
/departamentos

GET
/departamentos/{page}/{size}

GET
/departamentos/{id}

DELETE
/departamentos/excluir/{id}
```

