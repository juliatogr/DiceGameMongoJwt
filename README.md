# BE_Java_Sprint5_Task2

Aquest és el teu projecte final, una API 100% dissenyada per tu on aplicaràs tot el que saps fins ara per a crear una aplicació al complet, des de la base de dades a la seguretat. Aplica tot el que saps fins i tot el que no es demana.

## Nivell 1

El joc de daus s’hi juga amb dos daus. En cas que el resultat de la suma dels dos daus sigui 7, la partida és guanyada, si no és perduda. Un jugador/a pot  veure un llistat de totes les tirades que ha fet i el percentatge d’èxit.   

Per poder jugar al joc i realitzar una tirada, un usuari/ària  s’ha de registrar amb un nom no repetit. En crear-se, se li assigna un identificador numèric únic i una data de registre. Si l’usuari/ària així ho desitja, pots no afegir cap nom i es  dirà “ANÒNIM”. Pot haver-hi més d’un jugador “ANÒNIM”.  
Cada jugador/a pot veure un llistat de totes les  tirades que ha fet, amb el valor de cada dau i si s’ha  guanyat o no la partida. A més, pot saber el seu percentatge d’èxit per totes les tirades  que ha fet.    

No es pot eliminar una partida en concret, però sí que es pot eliminar tot el llistat de tirades per un jugador/a.  

El software ha de permetre llistar tots els jugadors/es que hi ha al sistema, el percentatge d’èxit de cada jugador/a i el  percentatge d’èxit mitjà de tots els jugadors/es en el sistema.   

El software ha de respectar els principals patrons de  disseny.  

NOTES 

Has de tindre en compte els  següents detalls de  construcció: 
- URL's:
  - POST: /players: crea un jugador/a. 
  - PUT /players: modifica el nom del jugador/a.
  - POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.  
  - DELETE /players/{id}/games: elimina les tirades del jugador/a.
  - GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.   
  - GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.  
  - GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits. 
  - GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.  
  - GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit. 

- Fase 1
Persistència: utilitza com a base de dades MySQL. 
- Fase 2
Canvia tot el que necessitis i utilitza MongoDB per persistir les dades.
- Fase 3
Afegeix seguretat: inclou autenticació per JWT en  tots els accessos a les URL's del microservei. 

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