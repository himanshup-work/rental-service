# Auth Service

The Auth Service is a microservice responsible for handling user authentication and registration in the Rental Service application. It provides secure access through JWT (JSON Web Tokens) and manages user roles and permissions.

## Technologies Used

*   **Java 17**: Core programming language.
*   **Spring Boot 3.x**: Framework for building the microservice.
*   **Spring Security**: For authentication and authorization.
*   **JWT (io.jsonwebtoken)**: For secure stateless authentication.
*   **PostgreSQL**: Relational database for storing user and role data.
*   **Spring Data JPA**: For database interaction.
*   **Lombok**: To reduce boilerplate code.
*   **Maven**: Project management and build tool.

## Project Structure

*   `com.auth_service.config`: Security and JWT configuration.
*   `com.auth_service.controllers`: REST endpoints for login and registration.
*   `com.auth_service.services`: Business logic for authentication.
*   `com.auth_service.repositories`: Database access for users and roles.
*   `com.auth_service.entities`: JPA entities for User and Role.
*   `com.auth_service.dto`: Data Transfer Objects for requests and responses.
*   `com.auth_service.utils`: Utility classes like `JwtUtil`.

## Getting Started

### Prerequisites

*   JDK 17 or higher.
*   Maven 3.x.
*   PostgreSQL database instance.

### Configuration

Update the database and JWT settings in `src/main/resources/application.yaml`:

```yaml
jwt:
  secret: your-secure-secret-key-at-least-256-bits
  expiration: 600000 # 10 minutes

spring:
  dataSource:
    url: jdbc:postgresql://localhost:5432/rentalappservice
    username: your-username
    password: your-password
```

### Execution Steps

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd auth-service
    ```

2.  **Clean and Build the project**:
    ```bash
    ./mvnw clean install
    ```

3.  **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

The service will start on port `8081` by default.

## API Endpoints

*   `POST /auth/register`: Register a new user.
*   `POST /auth/login`: Authenticate and receive a JWT token.
