Order Service - Microservice for Order Management
==================================================

Overview:
---------
This is a Spring Boot microservice responsible for handling orders in an e-commerce or inventory system.
It supports full CRUD operations and communicates with an Inventory Service using a Feign client.

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
Nipun Wijethunge
CTSE Microservices Assignment - Order Service
