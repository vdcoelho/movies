# Solution

Spring Boot Application was built to provide the features described in the Technical Challenge.  
I chose the H2 database to make it simpler, but it's ready to change to another relational database, having already been tested with MySql8.  
The project was organized in packages by functional role (e.g. controllers, services..).  
The docker image will be built using OpenJDK11 with O.S. Alpine.

>**Technologies used:**
>- Java 11
>- Maven
>- Spring Boot Web
>- Spring JPA
>- Spring Security
>- Flyway
>- Lombok
>- OpenCSV
>- Spring Validation
>- Docker
>- Angular
  
# How to Run?
To run this project follow the directions below:
> #### Requirements
> - Java 11
> - Maven
> - Docker **

#### 1- Clone or download the project.
#### 2- Open the terminal in the project's root folder.
#### 3- `mvn clean install` to build the project.
#### 4- `docker build -t backbase/movie-api:latest .` to create a docker image.
#### 5- `docker run -p 8080:8080 --name Movie-api -d backbase/movie-api:latest` to run the container.
#### 6- That`s all! Proceed to [how_to_test.md](how_to_test.md) =)

---
** or after step 3, `java -jar target/movies-0.0.1-SNAPSHOT.jar` to run without docker.