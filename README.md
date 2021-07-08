# Custumer Service

### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/raulmanor/tokio-test.git`
2. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`

# Autenticação 

2.`Abrir o Postman e fazer uma requisição do tipo  POST na url http://localhost:8080/autenticacao passando um json com nome e senha `

    {
		"nome": "adm",
		"senha": "1234"
	}

`o resultado dessa requisição vai gerar um token do tipo Bearer`

`na aba de "Authorization" do postman escolher o tipo Bearer Token e colar o token da requisição anterior  `


# Exemplos de requisições e respostas

REST API para o teste da Tokio Marine

## Obter lista de Clientes



`GET /customers?pageSize=6&pageNo=0&sortBy=name`

`GET /customers`
 
	

## Criar um novo Cliente


`POST /customers`


	{
		"name": "NomeDoNovoCliente",
		"email": "clientenovo@email.com"
	}


## Obter um cliente específico


`GET /customers/:id`


## Atualizar um cliente



`PUT /customers/:id`


## Delete um cliente



`DELETE /customers/:id`


## Criar um endereço para um cliente



`POST /customers/:id/addresses`

	{
		"cep": "04674225",
	}
	{
		"cep": "74993769",
	}
	{
		"cep": "75104080",
	}
	{
		"cep": "22640904",
	}

