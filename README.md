# DiceGameMongoJwt

## Introduction

The Dice Game is played with two dice. It is won when the sum of the two dice in a roll is equal to 7. Otherwise, the game is lost.

To play and roll it is needed to sign up the user with a unique username and email. There exists the possibility to play as an anonymous user introducing no data when registering. In this case, there is no need to log in the user, they can play without any problem. 

These are the possible actions of the API

| Methods | URLs | Actions |
| :---:   | :--- | :------ |
| POST | /auth/signup| creates a user |
| POST | /auth/signin | Inits the session of a registered user |
| GET | /test/all| checks every type of user can access the public content |
| GET | /test/user| checks if the user info can be accessed because it is logged in |
| GET | /test/anonymous | checks if the anonymous info can be accessed because there is no user logged in | 
| GET | /currentuser | returns the current logged in user or the last anonymous user if there is no logged in user | 
| GET | /players | list all players with their average success percentage |
| PUT | /players | updates the username of a registered user |
| GET | /players/{id}/games | list all games of the player with the specified id |
| POST | /players/{id}/games | the player with the specified id rolls |
| DELETE | /players/{id}/games | deletes the rolls of the player with the specified id |
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
### Project Organisation

The mongo model is very simple.

![model](images/model.png)

The fields `email`, `password` and `regDate` can be `null` because the anonymous user do not save this information.

There is no need to save the average success percentages since they are computed at the exact moment they are requested to be shown. There is also extra information util for the user but not needed to be in the database, like the result of the sum of the dice and if the game has been won or not. Therefore, there are DTO classes implemented for the User and Game classes, showing this extra information and also deleting the password, email, registration date and id of the users.

The files of the project are organised as shown in the next figure.

![project-organisation](images/project-organisation.png)

- To distinguish between the registered user and the anonymous user, there is an inheritance from the class User, which is the type of entity saved on the Mongo Repository.

- The `GameController` class works as the controller of the API, i.e., it defines the endpoints of the application and how they work. 

- The `UserRepository` class allows to manage the database in the program.

- The `LoadDatabase` class is just to load some initial data.

- The `UserService` class is a middle layer between the repository and the API. It allows to save and update all the date and convert it to DTO when requested. When the user requires the user or ranking information, it computes all the average success percentages.

All the security is explained in the next section.

### Security Architecture

The general security architecture is the next.

![spring-boot-mongodb-jwt-authentication-spring-security-architecture](images/spring-boot-mongodb-jwt-authentication-spring-security-architecture.png)

All the security is organised in the `security` folder like this.

![security-folder](images/security-folder.PNG)

* `WebSecurityConfig` configures cors, csrf, session management, rules for protected resources.

  * @EnableWebSecurity allows Spring to find and automatically apply the class to the global Web Security.
        
  * @EnableGlobalMethodSecurity provides AOP security on methods. It enables @PreAuthorize, @PostAuthorize. We can secure methods in our APIs with @PreAuthorize annotation easily.
        
  * `securityFilterChain(HttpSecurity http)` tells Spring Security how we configure CORS and CSRF, when we want to require all users to be authenticated or not, which filter (AuthTokenFilter) and when we want it to work (filter before UsernamePasswordAuthenticationFilter), which Exception Handler is chosen (AuthEntryPointJwt).

* `AuthenticationManager` has a DaoAuthenticationProvider (with help of UserDetailsService & PasswordEncoder) to validate UsernamePasswordAuthenticationToken object. If successful, AuthenticationManager returns a fully populated Authentication object (including granted authorities).

* `AuthEntryPointJwt` implements AuthenticationEntryPoint. AuthenticationEntryPoint will catch authentication error. commence() will be triggerd anytime unauthenticated User requests a secured HTTP resource and an AuthenticationException is thrown. HttpServletResponse.SC_UNAUTHORIZED is the 401 Status code. It indicates that the request requires HTTP authentication.

* `OncePerRequestFilter` makes a single execution for each request to our API. It provides a doFilterInternal() method that we will implement parsing & validating JWT, loading User details (using UserDetailsService), checking Authorizaion (using UsernamePasswordAuthenticationToken).

* `AuthTokenFilter`  extends OncePerRequestFilter. Therefore, it is executed once per request. It overrides `doFilterInternal()` to get JWT from the Authorization header, validate it, get UserDetails, generate an Authentication Object with `UsernamePasswordAuthenticationToken` and set the current UserDetails in SecurityContext.

* `JwtUtils` provides methods for generating, parsing, validating JWT. This class has 3 funtions:

        - generate a JWT from username, date, expiration, secret
        - get username from JWT
        - validate a JWT

* `LoginRequest` and `SignupRequest` are used to assure all needed fields when login or signing up are filled correctly.

- `JwtResponse` and `MessageResponse` are used to send HTTP responses with personalized body.

- `UserDetailsImpl` implements UserDetails. UserDetails contains necessary information (such as: username, password, authorities) to build an Authentication object.

- `UserDetailsServiceImpl` implements UserDetailsService. Spring Security will load User details to perform authentication & authorization. So it has UserDetailsService interface that we need to implement. It will be used for getting UserDetails object. UserDetailsService interface has a method to load User by username and returns a UserDetails object that Spring Security can use for authentication and validation.

Controller receives and handles request after it was filtered by OncePerRequestFilter.

- `AuthController` provides APIs for register and login actions.

        - /auth/signup

                - check existing username/email
                - create new User (with ROLE_USER if not specifying role)
                - save User to database using UserRepository

        - /auth/signin

                - authenticate { username, pasword }
                - update SecurityContext using Authentication object
                - generate JWT
                - get UserDetails from Authentication object
                - response contains JWT and UserDetails data

- `TestController` has accessing protected resource methods with role based validations. There are 3 APIs:
  - /api/test/all for public access
  - /api/test/user for users has ROLE_USER
  - /api/test/anonymous for users has ROLE_ANONYMOUS

## Util links

- **Spring Boot, MongoDB: JWT Authentication with Spring Security** https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/
- **Spring Data MongoDb for class inheritance** https://stackoverflow.com/questions/8794016/spring-data-mongodb-for-class-inheritance
- **Building REST services with Spring** https://spring.io/guides/tutorials/rest/
- **Spring Data MongoDB - Reference Documentation** https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#reference
