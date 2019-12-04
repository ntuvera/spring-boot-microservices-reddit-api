![Reddit_clone_backend_API](./assets/FakeRedditLogo.png)
# Reddit-Clone-Microservices
**Front-end**: @ToddS1 & @magfurulabeer || **Back-end microservies**: @stevencharleshuang & @ntuvera
 
 
 ### Microservice Repos:
 | Service       | Github Repo Link                                    |
|---------------|-----------------------------------------------------|
| API Gateway   | https://github.com/ntuvera/fakereddit-api-gateway   |
| Eureka Server | https://github.com/ntuvera/fakereddit-eureka-server |
| Comments      | https://github.com/ntuvera/fakereddit-comments-api  |
| Posts         | https://github.com/ntuvera/fakereddit-posts-api     |
| Users         | https://github.com/ntuvera/fakereddit-users-api     |
 

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
- RabbitMq: Interservice Communication
- slf4j: Logging
- jUnit, Mockito: unit testing and integration test
- Elasticsearch, Logstash, Kibana: Monitoring
- Docker: containerization
- Swagger: Restful API Documentation
- Jenkins: Continuous Integration
- JaCoco: Test Coverage reporting in Jenkins

### General Approach
To start we began by planning and diagramming our ERD and Microservice architecture.  After the initial mock-up we started by writing our Project planning using PivotalTracker with a combination of User Stories and Tasks.  The goal was to map out and replicate the expected endpoints and returns.  After the first day of planning we took a few days to research, the best ways to implement interservice communication.  Given that the backend was a originally built as a monolith, the focus was to split it into microservices based on their main model.  While it is built on a single Postgres DB, by ensuring separation of the services and applying OpenFeign, the database will be able to be split into separate db's down the line.  

The initial database was setup with migrations using Flyway, to greater control over how the data is handled.  By manually handling the relations between models, it gave us granular control and allowed us to later be able to split a singular database into multiple.  The auhtorization is handled via JSON WebTokens which are generated on the user microservice and authorization gatewayed through our api-gateway.  The separation of concerns was further implemented by FeignCalls, by being REST calls to the separate microservices, they are more standalone in nature.  Also if there is a a failure between posts and/or comments, they can be handled more gracefully (and will be in the future).

After confirming matching endpoints and getting similarly formatted returns (follow the original pattern with more optional data).  We then moved forward to testing the front-end against our newly built microservice back-end.  At this point, the focus was on ensuring the front-end worked seamlessly with the new back-end, essentially replacing the orginal monolithic back-end.  Given the front-to-back-end contract, with the correct endpoints/returns, we had to solve any configuration issues to ensure the api calls worked smoothly.  Finally, since we were able to check our API with Postman and reviewing raw HTTP requests we then had to finish handling CORS between the front-end and back-end.

#### update: v2 11/21/2019 - 12/4/2019
The next phase of the project was to incorporate new DevOps tools and techniques we've learned after employing Sprint Boot into Microservices.  We reused our Pivotal tracker from project one as a guide line for which tasks were necessary and then used a combination of Slack and Microsoft OneNote to keep track of our plan and scheduling.  

We started by making each individual microservice directory into its own git repository.  While this helpful to keep changes isolated and make Jenkins CI easier, it was a bit of a hassle in terms of keeping track of changes.  If an individual or a team were working on the microservice by themselves it would've been easier.  However because we were working with service intercommunication and other changes for the microservices themselves, keeping everythign up to date was a hassle when it had to be done in more than one main repository.  

Early on we wanted to apply Swagger Documentation and interservice Communication via RabbitMQ.  These additions and changes were decided to be attempted first because they would require the least changes in our existing code base, just additions.  We did run into configuration issues with Swagger Documentation when trying to aggregate all the RESTful routes information on the API-Gateway, however RabbitMQ message service went more smoothly.  Now that we were able to employ new features, we tackled Conitnuous Integration via Jenkins, which again was made easier by separating the microservices into their own repositories and given their own Jenkins files.  We had planned to work with one master repo and our JenkinsfilA would spin up each service consecutively.  That didn't work out as planned, so the split repo structure made things much easier, albeit a bit more tedious.

A large amount of the holiday time was spent on writing unit tests for what we had working.  We split the work load via posts and comments, and then user controller and user service.  It made the most sense to work on Unit Testing during the Holiday break as we had not planned any new features during that time.  We aimed to make sure we were in a safe build state before Thanksgiving.  We had progress in a majority of the new deliverables, but nothing completely finished.  We determined the best use of time was to either write more tests or work on Swagger documentation notes which wouldn't cause any build issues and anything that may be more breaking would be hanlded in a branch for later if time permitted.


### Major Hurdles and Challenges Faced
One of the first major hurdles we had to overcome was implementing Authentication and Authorization between microservices.  Previously we've only built monolithic applications which only would employ having a single node to hit, so setting up a pre-filter gateway to that made sense.  We initially were going to keep all auth on the user microservice which would end up coupling posts and comments to users more tightly.  But by separating the generating of auth to the user service, which usually was hit first, but then allowing the gateway to do the check, every call would be made separate from the user service after the initial authentication through users.

Another major hurdle we faced was our erd and database.  At initial planning it seemed straight forward, however we used flyway to migrate our tables from the beginning.  This allowed to decouple our tables in theory, but we had to make up for it in the code.  The main challenge was trying to compensate for relationships that were usually provided to us easily by hibernate.  Foreign key ids in hiberate could be used to easily reference other "embedded" objects and would be less code.  However, by generating our own Object Beans and making feign calls we were able to individually create missing entities and append them to the the returns we expected, even add more if we chose to, such as generating the User Object in all Post and Comment returns.

Additionally, a large amount of time was spent attempting to deploy our application to Pivotal Cloud Foundry. One of the biggest obstacles of this project was time spent on rebuilding the services between configurations and code debugs, even with caching implemented. Also, a lot of minor bugs were introduced due to configuration issues or dependency versions. It was a valuable lesson learned to be meticulous about code and configurations.

#### update: v2 11/21/2019 - 12/4/2019

Configurations.  
A majority of challenges we faced for the second half of this project was employing new tools which requried configurations that while we covered in class were handled slightly differently due to the structure of repositories and microservices.  We originally wanted to approach Jenkins Build Stages and steps in this parent repository assuming it was following a list of bash commands.  However, after reasearchinga and strong advisement, we moved to the separated repositories and Jenkinsfiles for builds.

Employing ELK stack for monitoring was pretty straight forward, however the configurations needed to be changed per a project, and we're under the assumption that it should be able to be aimed to multiple outputs per multiple projects.  But in our case it is mapping all the microservices to one local input and reviewed with Kibana.  

Swagger Documentation was also a challenge.  Utilizing Swagger Documentation on the individual services for the most part was pretty simple.  However we ran into a conflict with accessing "swagger-ui.html" and a wildcard route on our controller for comments and post.  It was determined that viewing the swagger-ui.html wasn't a priority, as the information needed was at a different enpoint `/v2/api-docs`.  Even though the needed Json was avaiable for the master Swagger documntation page, once again configuration of getting the swagger-ui page to load on API-Gateway was an issue. We switched between two methods of gathering the input, either by polling or one explicit GET.  We ended up deciding to use the Explicit GET, and changing our dependency version of Swagger.  A few hours later, we still could not get it to load, and it turned out that disabling one annotation `@EnableWebMvc` everything fell into place.  That was painful.

RabbitMQ was also a challenge. We used it for interservice communication to replace a Feign call that peformed a Cascasde Delete of Comments when their related Post was deleted.  The biggest challenge with that was that RabbitMQ expects only String messaages, and although we're passing a int postId, it required casting the value from a int to a string and back to an int.  We originally tried to create a new SenderMethod for custom message type with only a value of int, but that ended up not working.  We also ran into a looping issue where the microservice itself would loop looking at an empty "" message.  This was a combination of RabbitMQ expecting only String messages.


### Pivotal Tracker _(for planning and organizing tasks)_
https://www.pivotaltracker.com/n/projects/2417719

### ERD
<img src="./assets/P3-ERD.png" alt="Reddit_clone_backend_ERD" />

### Microservice Architecture
<img src="./assets/P3-Whiteboarding-00.jpg" alt="Reddit_clone_architecture" />
<img src="./assets/P3-Whiteboarding-01.jpg" alt="Reddit_clone_architecture" />
<img src="./assets/P3-Architecture.png" alt="Reddit_clone_architecture" />

#### update: v2 11/21/2019 - 12/4/2019
<img src="https://res.cloudinary.com/dk1cgfxkn/image/upload/v1575483698/P3_Fake_Reddit_Back_End_Architecture-Updated.png" alt="updated_architecture"/>

### API Endpoints
|          Description         |        Endpoint        | Method |
|:----------------------------:|:----------------------:|:------:|
|            Sign Up           | /signup                |  POST  |
|            Log In            | /login                 |  POST  |
|        Create Comment        | /comment/{postId}      |  POST  |
|   List Comments By Post ID   | /post/{postId}/comment |   GET  |
|   List Comments By User ID   | /user/comment          |   GET  |
| Delete Comment By Comment ID | /comment/{commentId}   | DELETE |
|          Create Post         | /post                  |  POST  |
|          List Posts          | /post/list             |   GET  |
|      List Posts By User      | /user/post             |   GET  |
|    Delete Post By Post ID    | /post/{postId}         | DELETE |
|      Create User Profile     | /profile               |  POST  |
|       Get User Profile       | /profile               |   GET  |
|        Update Profile        | /profile               |  POST  |

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

### Backlog
- Increase Test Coverage on API-Gateway
