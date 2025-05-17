Order Service - Microservice for Order Management
==================================================

Overview:
---------
This is a Spring Boot microservice responsible for handling orders in an e-commerce or inventory system.
It supports full CRUD operations and communicates with an Inventory Service using a Feign client.

Technologies Used:
------------------
- Java 17
- Spring Boot
- Spring Web
- Spring Data MongoDB
- OpenFeign (for Inventory communication)
- MongoDB
- Docker

How to Build:
-------------
1. Make sure you have Maven installed.
2. Run the following command to build the project:
   mvn clean package -DskipTests

How to Run (Locally):
---------------------
1. Start MongoDB locally or connect to a cloud MongoDB instance.
2. Run the Spring Boot application:
   java -jar target/order-service-0.0.1-SNAPSHOT.jar

Default port: 8081 (update if needed in `application.properties`)

How to Run (Using Docker):
--------------------------
1. Build the Docker image:
   docker build -t order-service .

2. Run the container:
   docker run -p 8081:8081 order-service

REST Endpoints:
---------------
Base URL: http://localhost:8081/api/orders

- POST   /api/orders         -> Create a new order
- GET    /api/orders         -> Get all orders
- GET    /api/orders/{id}    -> Get order by ID
- PUT    /api/orders/{id}    -> Update order
- DELETE /api/orders/{id}    -> Delete order

Note:
-----
- Make sure Inventory Service is running and accessible.
- Ensure MongoDB is connected and the DB user has proper permissions.

Author:
-------
CTSE Microservices Assignment - Order Service
