Brokerage Firm Backend API
This is a Spring Boot-based backend application for managing stock orders, assets, and customer transactions for a brokerage firm. 
The application includes creating, listing, and deleting stock orders, managing customer assets like TRY (Turkish Lira), and deposit/withdrawal operations.

Features
Create Stock Orders: Employees can create new stock orders for customers (buy or sell).
List Orders: Employees can list stock orders for a specific customer with filters.
Cancel Stock Orders: Employees can cancel pending stock orders.
Deposit/Withdraw Money: Employees can deposit or withdraw money (TRY) for customers.
List Customer Assets: Employees can list customer-owned assets (including TRY).
User Authentication: Admin and customer authentication with JWT token-based security.
Bonus Features:
Customer login for accessing their own data.
Admin users can match pending orders.
Technologies Used
Java 17
Spring Boot 3.x
Spring Security with JWT Authentication
JPA (Java Persistence API)
H2 In-memory Database
Swagger 3 for API Documentation
JUnit 5 and Mockito for Unit Testing
Prerequisites
To run the project locally, ensure you have the following installed:

JDK 17 or higher
Gradle or Maven (Gradle is used here)
A Java IDE like IntelliJ IDEA or Eclipse
Getting Started
1. Clone the repository
bash
Copy code
git clone https://github.com/your-repo/brokerage-api.git
cd brokerage-api
2. Build the project
Use Gradle to build the project:

bash
Copy code
./gradlew build
3. Run the application
You can run the application using the following command:

bash
Copy code
./gradlew bootRun
The application will start on http://localhost:8080.

4. Accessing Swagger UI
To interact with the APIs using Swagger UI, navigate to:

bash
Copy code
http://localhost:8080/swagger-ui/index.html
5. Default Credentials
The application uses JWT token-based security. To access the endpoints, you need to authenticate.

Default admin credentials are:

Username: admin
Password: admin123
After authentication, you will receive a JWT token. You can use this token for subsequent API requests by including it in the Authorization header as follows:

makefile
Copy code
Authorization: Bearer <your-jwt-token>
API Endpoints
Here are some of the key endpoints provided by the application:

Authentication
POST /api/auth/login
Request:
json
Copy code
{
  "username": "admin",
  "password": "admin123"
}
Response:
json
Copy code
{
  "token": "your-jwt-token"
}
Orders
POST /api/orders
Create a new stock order.

GET /api/orders
List orders for a customer (with optional filters).

DELETE /api/orders/{id}
Cancel a pending order.

Customer Management
POST /api/customers
Add a new customer.

GET /api/customers
List all customers.

GET /api/customers/{id}
Get details of a specific customer.

Deposit/Withdraw
POST /api/deposits
Deposit TRY into a customer account.

POST /api/withdrawals
Withdraw TRY from a customer account.

Assets
GET /api/assets
List customer assets, including TRY.
Database Configuration
By default, the application uses the H2 in-memory database for development and testing purposes. You can access the H2 database console at:

bash
Copy code
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave empty)
To use a persistent database, modify the application.properties file:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/brokerage
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
Unit Testing
To run the unit tests, you can execute:

bash
Copy code
./gradlew test
The tests are written using JUnit 5 and Mockito. They cover the service layer and repository layer of the application.

Future Improvements
Improve order matching logic to handle more complex scenarios.
Implement pagination for large datasets.
Enhance exception handling with custom error responses.
License
This project is licensed under the MIT License.

![image](https://github.com/user-attachments/assets/569fe026-7889-46b0-b484-d75a763632af)

![image](https://github.com/user-attachments/assets/0ee42ea9-304d-4201-a8be-d4de085bcd09)
