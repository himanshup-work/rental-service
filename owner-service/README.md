# Owner Service

The Owner Service is a core microservice within the Rental System, responsible for managing owner profiles and the properties they list for rent.

## Features

- **Owner Profile Management**: View and update owner business details.
- **Property Operations**: API endpoints are maintained as a facade for the `property-service` (Integration Pending).
- **Security**: JWT-based authentication and role-based access control (OWNER, ADMIN).
- **Observability**: Health checks, metrics, and logging via Spring Boot Actuator.

## Tech Stack

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (In-memory for development)
- **Lombok**
- **Maven**

## Prerequisites

- JDK 17 or higher
- Maven 3.8+ (or use the provided `./mvnw`)

## Getting Started

### Clone the repository
```bash
git clone <repository-url>
cd owner-service
```

### Build the application
```bash
./mvnw clean install
```

### Run the application
```bash
./mvnw spring-boot:run
```
The service will start on `http://localhost:8083` (configured port).

## Configuration

The application can be configured via `src/main/resources/application.properties`.

Key configurations:
- `spring.datasource.url`: Database connection URL.
- `management.endpoints.web.exposure.include`: Actuator endpoints to expose.
- `logging.level.com.owner_service`: Logging level for the service.

## API Documentation

### Owner Endpoints
Requires `OWNER` role.

- `GET /owner/me`: Get current owner profile.
- `PUT /owner/me`: Update current owner profile.

### Property Endpoints
Requires `OWNER` role.

- `GET /owner/properties`: Get all properties for current owner.
- `GET /owner/properties/{id}`: Get property by ID.
- `POST /owner/properties`: Create a new property.
- `PUT /owner/properties/{id}`: Update a property.
- `DELETE /owner/properties/{id}`: Delete a property.

### Monitoring
- `GET /actuator/health`: Service health status.
- `GET /actuator/info`: Application info.

## Error Handling

Standardized API responses are returned for errors:
```json
{
  "message": "Resource not found",
  "data": null,
  "statusCode": "NOT_FOUND"
}
```

## Maintenance

To run tests:
```bash
./mvnw test
```
