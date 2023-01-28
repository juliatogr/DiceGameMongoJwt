# DiceGameMongoJwt

## Introduction

The Dice Game is played with two dice. It is won when the sum of the two dice in a roll is equal to 7. Otherwise, the game is lost.

To play and roll it is needed to sign up the user with a unique username and email. There exists the possibility to play as an anonymous user introducing no data when registering. In this case, there is no need to log in the user, they can play without any problem. 

These are the possible actions of the API

| Methods | URLs | Actions |
| :---:   | :--- | :------ |
| POST | /api/auth/signup| creates a user |
| POST | /api/auth/signin | Inits the session of a registered user | 
| PUT | /players | updates the username of a registered user |
| POST | /players/{id}/games/ | the player with the specified id rolls |
| DELETE | /players/{id}/games | deletes the rolls of the player with the specified id |
| GET | /players | list all players with their average success percentage |
| GET | /players/{id}/games | list all games of the player with the specified id |
| GET | /players/ranking | returns the total average success percentage of all users in the system |
| GET | /players/ranking/loser | returns the player with worst average success percentage | 
| GET | /players/ranking/winner | returns the player with best average success percentage |

Obviously, any action depending on an id is not allowed to do by a user which id is different than the given one in the path.

## Security

To assure the program encodes the password of the user and keeps all their information secretly, the API uses an authentication and authorization request and response process with the JSON Web Token (JWT) and Spring Boot Security dependencies.

I followed the [Bezcoder Tutorial](https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/) to implement all the security.

### JWT  Authentication Flow

As given in the [Bezcoder Tutorial](https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/), this is the authentication flow.

![spring-boot-mongodb-jwt-authentication-flow](images/spring-boot-mongodb-jwt-authentication-flow.png)

The process to sign up and sign in follos the next steps:

1. Introduce the URL `localhost:9000/auth/signup` as a POST method with the corresponding user body (username, email, password).

        -  Anonymous user:
![anonymous-signup](images/anonymous-signup.png)

        - Registered user:

![reg-user-signup](images/reg-user-signup.png)

The server checks the information introduced is correct and returns the Message "Registered successfully!") if everything is OK.
        
2. Introduce the URL `localhost:9000/auth/signin` as a POST method with the corresponding login body (username, password). (Do not apply this step if the user is anonymous)

![reg-user-signin](images/reg-user-signin.png)

The server generates the token and gives the corresponding authorities to the user. Then, it returns this info to the user.

3. Copy the given token when login and paste it in the Authorization Header as a Bearer Token (Do not apply this step if the user is anonymous)

![auth-header-token](images/auth-header-token.png)

4. Do every action with the token introduced and the server checks everything is OK and return what is defined by the API if the user is allowed.

![roll-example](images/roll-example.png)
### Security Architecture

The files of the project are organized as shown in the next figure.


![spring-boot-mongodb-jwt-authentication-spring-security-architecture](images/spring-boot-mongodb-jwt-authentication-spring-security-architecture.png)

## Util links

- **JWT web** https://jwt.io/
- **Spring Boot Authorization Tutorial: Secure an API (Java)** https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/
- **Spring Data JPA One To Many Relationship Mapping Example** https://attacomsian.com/blog/spring-data-jpa-one-to-many-mapping
- **Many-To-Many Relationship in JPA** https://www.baeldung.com/jpa-many-to-many
- **Roll The Dice | Javascript Dice Roll | HTML, CSS & Javascript Tutorial** https://www.youtube.com/watch?v=6CMfZkLntX8
- **Spring Boot Many to Many Tutorial with Thymeleaf, Bootstrap and MySQL** https://www.youtube.com/watch?v=AbGxZmfWulU
- **No bean named 'mongoTemplate' available. Spring Boot + MongoDB** https://stackoverflow.com/questions/63386079/no-bean-named-mongotemplate-available-spring-boot-mongodb
- **'Field required a bean of type that could not be found.' error spring restful API using mongodb** https://stackoverflow.com/questions/42907553/field-required-a-bean-of-type-that-could-not-be-found-error-spring-restful-ap
- **Building REST services with Spring** https://spring.io/guides/tutorials/rest/
- **Spring Boot MongoDB** https://www.digitalocean.com/community/tutorials/spring-boot-mongodb
- **Accessing Data with MongoDB** https://spring.io/guides/gs/accessing-data-mongodb/
- **Spring Boot Integration with MongoDB Tutorial** https://www.mongodb.com/compatibility/spring-boot
- **Spring Data MongoDB - Reference Documentation** https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#reference
- **Spring Boot Authorization Tutorial: Secure an API (Java)** https://auth0.com/blog/spring-boot-authorization-tutorial-secure-an-api-java/
- **Securing a Web Application** https://spring.io/guides/gs/securing-web/
- **Spring Security without the WebSecurityConfigurerAdapter** https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
- **Spring Boot Security + JWT Hello World Example** https://www.javainuse.com/spring/boot-jwt
- **Autenticación de APIs basada en tokens con Spring y JWT** https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt
- **Rest API Authentication | Spring OAuth 2.0 Resource Server, JWT, MongoDB, Spring Boot** https://www.youtube.com/watch?v=FoyAvzU5fO0
- **Spring Boot, MongoDB: JWT Authentication with Spring Security** https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/
- **Thymeleaf + Spring Security integration basics** https://www.thymeleaf.org/doc/articles/springsecurity.html
- **Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported for @RequestBody MultiValueMap** https://stackoverflow.com/questions/33796218/content-type-application-x-www-form-urlencodedcharset-utf-8-not-supported-for
https://stackoverflow.com/questions/189559/how-do-i-join-two-lists-in-java  
https://stackoverflow.com/questions/8794016/spring-data-mongodb-for-class-inheritance

https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#reference
https://mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/
https://www.youtube.com/watch?v=pOMns4h5fQg