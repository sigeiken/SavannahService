 # Backend Developer Interview
 ### Technology used
- Java - Spring Boot framework
-  MySQL Database
-  Jenkins
- Africa’s Talking SMS
- Swagger: API Documentation
### APIs
1. Customer registration 

URL: http://localhost:8080/savannah/api/v1/auth/signup

Method: POST

Sample Request
```
{
    "firstName": "Kenneth",
    "lastName": "Sigei",
    "phone":"254727053832",
    "email":"kennethsigei31@gmail.com",
    "password":"s3cret"
}
```
Sample success response
```
{
    "responseCode": "00",
    "responseMessage": "Success"
}
```

2. Login

URL: http://localhost:8080/savannah/api/v1/auth/login

Method: POST

Sample Request
```
{
    "email":"kennethsigei31@gmail.com",
    "password":"s3cret"
}
```

Sample success response
```
{
    "responseCode": "00",
    "responseMessage": "Success",
    "data": {
        "authenticationToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZW5uZXRoc2lnZWkzMEBnbWFpbC5jb20iLCJpYXQiOjE2MTQ0MTEwODEsImV4cCI6MTYxNDQxMTk4MX0.UqwT_khVyphn4IPuHBDIgWE6I-6mRDrRxZaviYdae_8",
        "refreshToken": "1ea2f4b6-af19-4677-9fbf-85fe1168fea3",
        "expiresAt": 1614411982.419031000,
        "username": "kennethsigei30@gmail.com"
    }
}
```

3. Add order 
   
URL: http://localhost:8080/savannah/api/v1/addOrder

Method: POST

Sample Request
```
{
  "item": "Samsung Galaxy S21",
  "amount": 128000
}
```

Sample success response
```
{
    "responseCode": "00",
    "responseMessage": "Success"
}
```

