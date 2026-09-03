Hotel Management System

A Java-based Hotel Management System developed using Java, Maven, Hibernate/JPA, and PostgreSQL.

Technologies Used

- Java
- Maven
- Hibernate/JPA
- PostgreSQL
- IntelliJ IDEA

Features

- Create and manage rooms
- Get room details by ID
- Update room status
- Create and manage customers
- Get customer details by ID
- Create hotel bookings
- Get booking details by ID
- Cancel bookings

Project Architecture

The project follows a layered architecture:

Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
PostgreSQL Database

Main Packages

- "com.hotel.entity" – Entity classes
- "com.hotel.repository" – Database/repository operations
- "com.hotel.service" – Business logic
- "com.hotel.controller" – Application controllers

Database

PostgreSQL is used as the database, with Hibernate/JPA handling persistence and database operations.

How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Configure PostgreSQL.
4. Update the database configuration in "persistence.xml".
5. Reload Maven dependencies.
6. Run the application.

Author

Shaik Mahammed Rafi

GitHub: "smdrafi765" (https://github.com/smdrafi765)
