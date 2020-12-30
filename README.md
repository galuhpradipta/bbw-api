# BBW Virtual Account API

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

### Requirement

 - Docker
 - Java version 1.8
 - Maven

#### Installation

```sh
$ git clone https://github.com/galuhpradipta/bbw-api
```
#### Build with maven

```sh
$ mvnw clean package -DskipTests
```
`-DskipTests is neccessary because postgres not exising yet`

#### Remove if have existing Jar file and copy newest build into docker directory

```sh
$ docker rmi bbw-api:latest
$ cp target/bbw-api-0.0.1-SNAPSHOT.jar src/main/docker
```

### Run Docker 

```sh
$ docker-compose up
```

## List Endpoint

###  Generate Auth Token
```sh
[Request]
$ curl --location --request POST 'http://localhost:8080/oauth/token' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --header 'Authorization: Basic YmJ3dXNlcjpiYndwYXNzd29yZA==' \
    --data-urlencode 'grant_type=client_credentials'
```

```json
[Response]
{
    "auth_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiYnciLCJleHAiOjE2MDkyOTAwOTd9.i3mR2F88Th_2C-v4LoYIf40iFebvm2bwV0c7zZd4O6o",
    "token_type": "Bearer",
    "expires_in": "3600"
}
```


###  Inquiry Virtual Account
```sh
[Request]
$ curl --location --request POST 'http://localhost:8080/va/inquiry' \
    --header 'Content-Type: application/json' \
    --header 'X-BBW-Timestamp: 2020-12-29T22:23:35.640Z' \
    --header 'X-BBW-Signature: 6b05d6a033f63372a2a520534178865ebeb15e28026d871d44360f5f913b0866' \
    --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiYnciLCJleHAiOjE2MDkyOTAwOTd9.i3mR2F88Th_2C-v4LoYIf40iFebvm2bwV0c7zZd4O6o' \
    --data-raw '{
        "client_id":"001",
    	"reference_number":"000000000001",
    	"virtual_account":"7771234567890"
    }'
```
```json
[Response]
{
    "rc": "000",
    "message": "Success",
    "data": {
        "account_name": "FebriHaryadi"
    }
}
```

###  Payment Virtual Account
```sh
[Request]
$ curl --location --request POST 'http://localhost:8080/va/payment' \
    --header 'Content-Type: application/json' \
    --header 'X-BBW-Timestamp: 2020-12-29T22:23:35.640Z' \
    --header 'X-BBW-Signature: 6b05d6a033f63372a2a520534178865ebeb15e28026d871d44360f5f913b0866' \
    --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJiYnciLCJleHAiOjE2MDkyODU4NTh9.Jal5T2zyQk3-A7t17FoiEgX4hfB4KwSduYEBLFJK31I' \
    --data-raw '{
        "client_id":"001",
    	"reference_number":"000000000002",
    	"virtual_account":"7771234567890",
    	"amount":"250000",
    	"note":"buy a coffee"
    }'
```
```json
[Response]
{
    "rc": "000",
    "message": "Success",
    "data": {
        "transaction_number": "ccb0f414-1fcc-4017-924d-0135941467a4"
    }
}
```




