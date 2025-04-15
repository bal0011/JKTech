# JKTech
Assignment

Prerequisites
1. JDK 17 or above
2. Maven 3.8+
3. MySQL running (adjust application.properties)

Authentication - Login First
POST http://localhost:8081/api/auth/login

JSON Body:
{
  "username": "john",
  "password": "1234"
}
{
  "token": "mock-jwt-token-for-john"
}
Copy this token and use it in Postman or Swagger UI as:
Authorization: Bearer mock-jwt-token-for-john

Setup & Run
1. clone from git 
git clone https://github.com/bal0011/JKTech.git
cd JKTech

2. Configure Database
Update src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/documentdb
spring.datasource.username=root
spring.datasource.password=Root
spring.jpa.hibernate.ddl-auto=update

3. Build & Run 
mvn clean install
mvn spring-boot:run

4. Access Swagger UI
http://localhost:8081/swagger-ui.html

Rest API End Points 
1. POST  http://localhost:8081/api/documents/upload  
   POST    /api/swagger-documents/upload
Form-Data:
file (File)
author (String)
type (String)

2.  Search by Keyword
  GET  http://localhost:8081/api/documents/search?keyword=Getting&page=0&size=5
  GET /api/swagger-documents/search?keyword=spring&page=0&size=10

4. Filter by Metadata
  GET /api/swagger-documents/filter?author=John&type=Guide&page=0&size=10
      http://localhost:8081/api/documents/filter?author=xxx &type=guide &page=0&size=10
Testing
Run mvn test
