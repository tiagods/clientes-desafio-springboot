

# Projeto Clientes


### Considerações

- O projeto deve ser acessado através da url http://localhost:8080
Necessário o banco de dados MySql e a base desafio, o arquivo application.properties foi definido com as seguintes configurações:

spring.datasource.url=jdbc:mysql://localhost/desafio
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update



### Tecnologias Utilizadas 
- Spring Boot
- Thymeleaf
- Rest
- Bootstrap
- MySql
- Não incluso Spring Security




## REST 

A API foi desenvolvida para retornar dados em JSON

### GET

http://localhost:8080/api/clientes			
HTTP STATUS=200

para retornar um array de objetos ou


http://localhost:8080/api/clientes/{id}		
HTTP STATUS=200

para retornar apenas um objeto.


### POST

http://localhost:8080/api/clientes			HTTP STATUS=201



### PUT  
O id do cliente obrigatoriamente deverá ser enviado como parâmetro ao enviar o comando de atualizar

http://localhost:8080/api/clientes/{id}		


### ERROS:

*Caso o parametro ID seja incorreto, uma mensagem será retornada em forma de JSON e HttpStatus 404 como exemplo de retorno abaixo:

```JSON
{
    "titulo": "O cliente nao pode ser encontrado",
    "status": 404,
    "timestamp": 1542743549727,
    "mensagemDesenvolvedor": "http://meudesafio.com.br/erros"
}