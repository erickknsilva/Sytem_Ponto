<h1 align="center">
  Sistema de Bater Ponto
</h1>

API para registrar os pontos do funcionarios, encaminhando um email com as informações hora de entrada, almoço/retorno e saida 
se ele vor do tipo "mensalista". Agora se o funcionario vor do tipo "horista", o email deve conter a hora de entrada, quantidade
de horas trabalhas, salario do dia e o salario do mês até o momento. 
Objetivo é deixa os funcionarios cientes e confortavel, tendo o acesso do registro do seu ponto a qualquer momento, podendo acom-
panhar sua jornada diária, semanal e mensal.



## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- JavaScript
- HTML/CSS
  
## Práticas adotadas

- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3

## Como Executar

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [httpie]([https://httpie.io](https://www.postman.com/),
Tambem foi utilizado uma aplicação Front-end consumindo os ENDPOINT da API disponibilizada, usando o Fetch API do JavaScript
e utilizando HTML/CSS para fornecer uma interface para o usuario.




