

This API is created with spring boot. 

To run the application clone or copy the repository.

If you have maven installed in the target server, just start the app with command:

$ mvn spring-boot:run

In case you do not have maven, you can also start the application with following command 

java -jar target/api-1.0-SNAPSHOT.jar

After the application is up and running, you can check the API documentation from Swagger UI:

http://localhost:8080/swagger-ui.html#/

There is 2 rest endpoints:

1) /api/v1/weather/{location} , this returns current weather from given location}
2) /api/v1/weather/forecast/{location}/{days} , this returns hourly weather forecast by hour, Currently this endpoint is secured and it requires
login.  

There is in memory h2 database that has 2 users (user and admin ) password for both users is jwtpass

Service works also as authorization server and client can retrieve the jwt token with [/oauth/token] end point 
and passing either one of the user credentials and weatherApiClient as Client Id and th3whe4ther4p1 as signing key

H2 console should be available in http://localhost:8080/h2/

Weather service that is used is Apixu, their endpoints are http://api.apixu.com/v1/ and in case you run application behind company firewall etc
and need proxy, this can be set into application.properties file:

com.weather.service.apixu.proxy.port=80
com.weather.service.apixu.proxy.url=





