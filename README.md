# Spring Security Application

This is a simple Spring Security application showcasing basic authentication, user registration, and CSRF handling with a built-in H2 in-memory database.

## Features
- User registration with encrypted passwords using BCrypt.
- Basic authentication using Spring Security.
- CSRF token generation.
- H2 database integration for user storage.
- Stateless session management.
- REST APIs for managing users and students.

---

## Prerequisites

- Java 17 or later
- Maven 3.6+
- IDE with Spring Boot support (e.g., IntelliJ IDEA, Eclipse)

  
---
# Config Module

This module contains the configuration for Spring Security in the application.

## Class: SecurityConfig

### Features
- Configures authentication using `DaoAuthenticationProvider`.
- Uses `BCryptPasswordEncoder` for password encryption.
- Defines security filter chain for:
  - Disabling CSRF.
  - Enabling basic authentication.
  - Enforcing stateless session management.

### Key Methods
1. **`authenticationProvider()`**:
   - Configures the `DaoAuthenticationProvider` with a custom `UserDetailsService` and `BCryptPasswordEncoder`.

2. **`securityFilterChain(HttpSecurity http)`**:
   - Sets up the HTTP security configurations, including disabling CSRF, enabling basic authentication, and stateless sessions.

### Dependencies
- Spring Security
- BCrypt

# Controller Module

This module contains REST controllers for handling requests related to users and students.

## Classes

### 1. HelloController
#### Endpoints:
- **`GET /hello`**: Returns a "Hello World" message along with the session ID.
- **`GET /about`**: Returns an "About page" message along with the session ID.

---

### 2. StudentController
#### Endpoints:
- **`GET /csrf-token`**: Fetches the CSRF token.
- **`GET /students`**: Returns a list of all students.
- **`POST /students`**: Adds a new student to the in-memory list.

---

### 3. UserController
#### Endpoints:
- **`POST /register`**: Registers a new user by saving them into the database.

---

### Dependencies
- Spring Web

# Models Module

This module contains the data models used in the application.

## Classes

### 1. Student
- **Attributes**:
  - `id`: Unique identifier for the student.
  - `name`: Name of the student.
  - `tech`: Technology the student is learning.

### 2. User
- **Attributes**:
  - `id`: Unique identifier for the user (Primary Key, Auto Increment).
  - `username`: Username of the user (Unique, Non-Nullable).
  - `password`: Password of the user (Encrypted using BCrypt).

---

### 3. UserPrincipal
- Implements `UserDetails` to integrate Spring Security with the `User` entity.
- Provides:
  - User's authorities.
  - Username.
  - Password.
  - Account status flags (all set to `true`).

---

### Dependencies
- Spring Data JPA
- Hibernate

# Repository Module

This module contains the repository interface for database operations.

## Classes

### UserRepo
- Extends `JpaRepository` to perform CRUD operations on the `User` table.
- **Custom Query**:
  - `User findByUsername(String username)`:
    - Fetches a user by their username.

---

### Dependencies
- Spring Data JPA
- H2 Database

# Service Module

This module contains the business logic for the application.

## Classes

### 1. MyUserDetailsService
- Implements `UserDetailsService` to provide user details to Spring Security.
- **Key Method**:
  - `loadUserByUsername(String username)`:
    - Fetches the user from the database using `UserRepo` and wraps it in `UserPrincipal`.

---

### 2. UserService
- Handles user registration and password encryption.
- **Key Method**:
  - `saveUser(User user)`:
    - Encrypts the user's password using `BCryptPasswordEncoder` and saves the user to the database.

---

### Dependencies
- Spring Security
- BCrypt
- Spring Data JPA

# Spring Security Application

The main module to bootstrap the Spring Security application.

## Class: SpringSecurityApplication

- The entry point of the application.
- Annotated with `@SpringBootApplication`.

### Features
- Starts the embedded Tomcat server.
- Initializes the application context.

---

### Dependencies
- Spring Boot

# Resources Module

Contains configuration and SQL scripts for the application.

## Files

### 1. application.properties
- Configures:
  - H2 database.
  - Hibernate auto schema update.
  - H2 Console path.

### 2. schema.sql
- Creates the `users` table:
  ```sql
  CREATE TABLE users (
      id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(50) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL
  );
   ```
### 3. data.sql
- Inserts initial data into the `users` table:
```sql
INSERT INTO users (username, password) VALUES ('admin', 'admin123');
INSERT INTO users (username, password) VALUES ('user', '$2a$12$56DE6dUdAiJ5UXQ9SxuVJObUbmwpyAMpqlqZIpLImg/9LmAkPS15S');
```

# Future Enhancements Module

This module outlines planned or potential improvements to the application for scalability, usability, and feature enrichment.

## Planned Features

### 1. **Role-Based Access Control (RBAC)**
   - Implement roles such as `ADMIN`, `USER`, and `MODERATOR`.
   - Restrict access to specific endpoints based on roles.
   - Example:
     - `/admin/**`: Accessible only by users with the `ADMIN` role.
     - `/user/**`: Accessible by both `USER` and `ADMIN` roles.

---

### 2. **JWT Authentication**
   - Replace Basic Authentication with JSON Web Token (JWT).
   - Benefits:
     - Stateless authentication.
     - Enhanced security for API access.
   - Steps:
     - Generate JWT during login.
     - Validate JWT for secured endpoints.

---

### 3. **User Management Dashboard**
   - Build a UI dashboard for:
     - Viewing all registered users.
     - Editing or deleting users (admin-only functionality).
     - Monitoring login activities and failed login attempts.

---

### 4. **Audit Logging**
   - Track user activities, such as login, logout, and CRUD operations on entities.
   - Store logs in a separate database table or an external monitoring tool.

---

### 5. **External OAuth2 Integration**
   - Allow users to log in using external providers like Google, GitHub, or Facebook.
   - Benefits:
     - Simplified authentication.
     - Wider user adoption.

---

### 6. **Enhanced Security Features**
   - Password policies:
     - Minimum password length.
     - Special character and digit requirements.
   - Account locking after multiple failed login attempts.
   - Email or SMS-based multi-factor authentication (MFA).

---

### 7. **Database Enhancements**
   - Migrate from H2 in-memory database to a persistent database (e.g., MySQL, PostgreSQL) for production use.
   - Implement database versioning with tools like Flyway or Liquibase.

---

### 8. **Pagination and Filtering**
   - Add pagination and filtering for the `/students` and `/users` endpoints.
   - Benefits:
     - Improved performance for large datasets.
     - Enhanced user experience for API consumers.

---

### 9. **API Documentation**
   - Use Swagger/OpenAPI to document all REST endpoints.
   - Benefits:
     - Easier testing and integration for API consumers.
     - Clear endpoint definitions with request/response examples.

---

### 10. **Asynchronous Processing**
   - Use message brokers like RabbitMQ or Kafka for:
     - Asynchronous email notifications.
     - Event-driven architecture for complex workflows.

---

## Contribution Guidelines
- If you want to contribute to the future enhancements, feel free to fork the repository, make changes, and create a pull request.
- For major feature requests or architectural changes, open an issue first to discuss the proposed idea.

## Dependencies for Future Enhancements
- Spring Security OAuth2
- Spring Data JPA for persistent databases
- JWT libraries (e.g., `io.jsonwebtoken` or `nimbus-jose-jwt`)
- Swagger/OpenAPI
- RabbitMQ or Apache Kafka (for messaging)

---
