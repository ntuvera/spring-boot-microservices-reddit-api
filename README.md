![Reddit_clone_backend_API](./assets/FakeRedditLogo.png)
# Reddit-Clone-Microservices
**Front-end**: @ToddS1 & @magfurulabeer || **Back-end microservies**: @stevencharleshuang & @ntuvera


### Technologies Used:
- Spring Boot: Framework for microservices
- Spring Boot Web
- Spring Boot Security
- Spring Cloud Starter Netflix Eureka Client/Server
- Spring Cloud Starter Netflix Ribbon/Hystrix/Zuul Proxy
- OpenFeign:  Microservice Communication
- JSON WebToken: Specific method for stateless Security
- JPA & Hibernate :Relational Mapping and JDBC handler
- Flyway DB: Migrations
- Postgresql: database used
- Maven: Dependency Manager

### General Approach
To start we began by planning and diagramming our ERD and Microservice architecture.  After the initial mock-up we started by writing our Project planning using PivotalTracker with a combination of User Stories and Tasks.  The goal was to map out and replicate the expected endpoints and returns.  After the first day of planning wek took a few days to research, the best ways to implement interservice communication.  Given that the backend was a originally built as a monolith, the focus was to split it into microservices based on their main model.  While it is built on a single Postgres DB, by ensuring separation of the services and applying OpenFeign, the database will be able to be split into separate db's down the line.  

The initial database was setup with migrations using Flyway, to greater control over how the data is handled.  By manually handling the relations between models, it gave us granular control and allowed us to later be able to split a singular database into multiple.  The auhtorization is handled via JSON WebTokens which are generated on the user microservice and authorization gatewayed through our api-gateway.  The separation of concerns was further implemented by FeignCalls, by being REST calls to the separate microservices, they are more standalone in nature.  Also if there is a an failure between posts and/or comments, they can be handled more gracefully (and will be in the future).

After confirming matching endpoints and getting similarly formatted returns (follow the original pattern with more optional data).  We then moved forward to testing the front-end against our newly built microservice back-end.  At this point, the focus was on ensuring the front-end worked seamlessly with the new back-end, essentially replacing the orginal monolithic back-end.  Given the front-to-back-end contract, with the correct endpoints/returns, we had to solve any configuration issues to ensure the api calls worked smoothly.  Finally, since we were able to check our API with Postman adn reviewing raw HTTP requests we then had to finish  andling CORs between the Front-end and back-end.

### Major Hurdles

### Pivotal Tracker _(for planning and organizing tasks)_
https://www.pivotaltracker.com/n/projects/2417719

### ERD Diagram
![Reddit_clone_backend_ERD]()

### Microservice Architecture
![Reddit_clone_architecture]()

### How to Run Project

Starting the API and microservices
1. Pre-requisite: have `Docker`, `Maven` installed
2. `git clone git@github.com:ntuvera/spring-boot-microservices.git`
3. `cd spring-boot-microservices`
4. `docker-compose up` (and wait for the build, it may take a while)
5. Check to see that the api is up by going to `http://localhost:8671`

Launching the front-end(Using JetBrains IntelliJ)
1. `git clone git@github.com:ToddS1/Reddit-Clone-with-Backend-P2.git`
2. In IntelliJi *File>Open ...>* locate folder created by clone and click Open
3. (Assuming you have a Tomcat Apache server) Under *Run> Edit Configurations* 
4. Click the `+` and select Tomcat Server > local.  Set your Application to your local install of Apache Tomcat.  
5. Under the `Deployment` tab, under _Deploy at server startup_, click the `+`and select artifact.
6. Select `reddit-clone-api:war exploded`
7. Optional Step: change application context to `/` to be able to access the server directly from http://localhost:8080 (instead of localhost:8080/reddit_clone_api_war_exploded)
8. Click Ok.  
9. Under the Run Menu, click `Run Tomcat version number`
10. Access the API either through postman endpoints or through the index.html page in the `P1_source` folder

