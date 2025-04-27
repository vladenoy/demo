For running project:
1. run postgres docker image: docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres

2. run project: ./mvnw spring-boot:run   tables wil be created by flyway and added few users.


folder bruno_colection contains collection of request for bruno
