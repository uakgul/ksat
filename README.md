# Task
- JDK >= 18
- Need MYSQL database connection
- Dockerhub: [https://hub.docker.com/repository/docker/ugurakgul/second-image/](https://hub.docker.com/r/ugurakgul/ksat-image)


## API
### Create credit 
URL: POST /credits

Body:
``
{
"userId": 33,
"amount": 300.00,
"installmentCount": 3
}``


### Create credit
URL: POST /credits

Body:
``
{
"userId": 33,
"amount": 300.00,
"installmentCount": 3
}``

### List user credit
URL: GET /credits/{userId}

### Filter user credit
URL: GET /credits/paged/{userId}

Request Params: page size creditStatus createDate

### Pay installment
URL: POST /installments/pay

Body:
``{
"installmentId": 11,
"amount": 33.33
}
``


