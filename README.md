## micro-service
```
Objetivo desse projeto

##Contruir uma API separada por Micro-Services
spring-cloud
eureka-client
gateway
token
criptografia

```
## Requisitos
```
Java 11

```

# Caso deseje utilizar docker mysql
```
docker-compose -f stack.yml up

```

## Para iniciar o projeto siga a sequência
```
Start => DiscoveryApplication
Start => GatewayApplication
Start => CourseApplication

```


## Acesse a porta Eureka e veja a conexão
```
http://localhost:8081/

```

#### Crie um token no recurso abaixo, veja o token criado no Headers
```
Curl GET http://localhost:8080/gateway/auth/login

```
##### Token

```
BearereyJjdHkiOiJKV1QiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..m-bEvP5Ft0AGXv7XWzduFQ.S4jvcR1usQ_rDUj-lJ50FcNN1qXkJ-F0xJi4nkUPNEHwXAT73U951PeAhcHhd4xCs1NN1MYHe_Io_ws0culDnT7tX3lH_Nb4_dsJaRm_sHvo7ESCj6Qn57luqz6qhnJIcHNe1WXCNDSgq8hMsxcwyjR2MwqnjaEJWZYFUyxSxGRCc0i68dQFxAfXZsEMM7_Ha42DgOvsKXhet-TR0sQan4IwGKNaO8L30x_KZhk1JghRTdbY0aGsSFdh5IdYNdxFgJbSfW52F9PFUpNd-bcZ--inavbnzjqzNAEndS89iPyn9uBg18cqrb8mWCIn-cuT8T-z22NUSH1SdbE7X9tFfPyZZf725ENPq3Ha5BLbvTwlQ6MqXbE5EaGYEGNraGT7MdvzswQduLAYs0mvWsKcvZU4ztEHjkWCWo1uwexkLOVlsRepltiSySBwMGHUqtoQWRB3tsrkrAPedKXY5hlWt8C3qpeC_F3SP5CNtrs4N__phedWj9SZ-9YrPeih4lO1lYzGHv9ILnrIqB8ZN-moK-N1D03ftyv-hbDCpxwhHO4E6Cd2a1r37bp18DX4JTcIER5O9kssEW56nWl90ekXbjLZuwbWf5j6nSr_6xfwwiSoEBcoxdDRYlLZwqEj_yWB4AIGJhofImOPJLhrONRM2eyuFRIQEAV-0JzkDxGowH5VBJbc6wzaAmXPeUvdgoXDY8JewpFcNuGSFxnvqMQUyUJquWPNLQff7EtPGPDdHs-hUcJB5-Sltx50yCTW-YY58HHzc8bPN0lXCGEuWnAHtW4ep_m-tmWHrZrFc_Rv94U7_roVIXdGGvthPc_f1I2aqee5JyNOMqpyOBauhVmgBaarWJO-L4zQMN8jnOM2USu4zPsnYiVW5gFNACr9k2YyKRn03VMJKDK5yj57WOjLmf6P1uglVA9ammy00fCML9hsAc8l6uNoSucHzvuscVXFEW81JxVLFTnBnaVuu-oWRoCdiccIQ9z9ZNNIwHI7nlpuTi4Ck-Hev-C-8DSwO6zwFszPQ3NlA7axoxb9PzekKIbVPpM2PpMUWWly3GmopOoL07s4qSIbAXhJgjKjbj8MFWoTRA7q8IV-MzZorZQfGXToJSKsK5zB8q5qvpfJ0S1OEZIBCUKSQU7xaWJB81g93J1njnXZ_jqidr8Fk-XtUV75B2T1UyzE5ngeek2q5xygIWGrLIIXLQFzbzwliX6-m4BLjrwxS4dHWNejy4Esrot-BqPCj-Ii-qwW7uKwB-pgRUXn9ZttCMKAKMD1U4UBZwvzAQmwF4waTuN-WAI4IeJckYthRH_QiyZPQaby3D3X12JrmzIdFeGOQ8prLT-Q0Ni902uRIEIKj1DsUEwsWLD_ssKjwKOoBgYr7wRp1kpNEsNXX-7c6clJmza8K4K73crUWhCF7NFjHnpUxqVtMRKonex2E5slEYq-vzUJ-sVfrM274ggh_EAOjj880s4154WUaNro7Zl1gFkOb1U2XQ.HW5PrdIL-O7VolvPFFt_FQ```

```
##### Insira pelo menos uma entrada na sua base de dados no caso banco mysql  ex: title = Spring Boot Microservices By DevDojo

```
INSERT INTO devdojo.course
(title)
VALUES('Spring Boot Microservices By DevDojo');
```

#### Acessar a URL pelo postman
```
Curl GET http://localhost:8080/gateway/course/v1/admin/course

```

```
{
    "content": [
        {
            "id": 1,
            "title": "Spring Boot Microservices By DevDojo"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageSize": 20,
        "pageNumber": 0,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "number": 0,
    "numberOfElements": 1,
    "size": 20,
    "empty": false
}

```

## Tente acessar sem o /gateway  para ver o erro
```
Curl GET http://localhost:8080/course/v1/admin/course
```


```
<!doctype html>
<html lang="en">
<head>
<title>HTTP Status 404 – Not Found</title>
<style type="text/css">h1
{font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;}
h2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;}
h3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;}
body
{font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;}
b {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;}
p {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}
a {color:black;}
a.name {color:black;} .line
{height:1px;background-color:#525D76;border:none;}
</style>
</head>
<body><h1>
HTTP Status 404 – Not Found</h1><hr class="line" />
<p><b>Type</b> Status Report</p><p><b>Message</b>
Not found</p><p><b>
Description</b>
The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.
</p><hr class="line" /><h3>Apache Tomcat/9.0.16</h3>
</body>
</html>

```

#### Na tabela application_user insira um username ex:
```
INSERT INTO devdojo.application_user
(password, `role`, username)
VALUES('$2a$10$FWK.d5nBQ.XvaNvrnBJoNegRhqoNSRPCiHIAasC7QkNsuDMnlzmye','ADMIN','william');
```

#### Chame o gateway/auth/user/info
```
Curl GET http://localhost:8080/gateway/auth/user/info
```
#### Adicione o token gerado no postman na parte do  Headers Authorization

```
   {
       "id": 1,
       "username": "william",
       "role": "ROLE_ADMIN"
   }

```

#### Docs Swagger2

```
   http://localhost:8080/gateway/auth/swagger-ui.html#/user-info-controller
   http://localhost:8080/gateway/course/swagger-ui.html#/course-controller

```