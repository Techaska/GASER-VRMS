# GASER-VRMS

GASER-VRMS is a Java REST API for managing customers, their vehicles, garages, and vehicle service records. It uses Spring Boot, Spring MVC, Spring Data JPA, and MySQL.

## Current functionality

- **Customers**: create, list, retrieve by ID/email, update, delete, search by name, and find by phone number.
- **Vehicles**: create, list, retrieve by ID, update, delete, and search by owner name, vehicle number, or brand.
- **Garages**: create, list, retrieve by ID, update, and delete. Garage records include contact details and opening/closing times.
- **Service records**: create, list, retrieve by ID, update, delete, and list by vehicle ID. Records include service type, date/time, cost, odometer reading, optional next service date, and remarks.
- **Customer–garage association model**: a JPA entity tracks an active association and its creation time, but no API for managing these associations is currently implemented.

Customer requests and responses use DTOs. Vehicle, garage, and service-record endpoints currently accept/return JPA entities directly.

## Technology

- Java 21
- Spring Boot 4.1.2
- Maven Wrapper
- Spring Web MVC, Data JPA, Validation, and Security
- MySQL Connector/J
- Thymeleaf and Lombok dependencies

The repository does not currently contain a web page/template, custom Spring Security configuration, login or registration endpoints, or role-based authorization rules. `UserAccount` and `Role` are persistence model types only at this stage.

## Requirements

- JDK 21
- MySQL server and an empty or application-specific database

## Configuration and run

The application listens on port `8888` by default. Configure the MySQL connection using environment variables; the datasource URL must include the database name and any required connection options.

PowerShell example:

```powershell
$env:MYSQL_URL = "jdbc:mysql://localhost:3306/gaser_vrms"
$env:MYSQL_USERNAME = "your_mysql_user"
$env:MYSQL_PASSWORD = "your_mysql_password"
.\mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
export MYSQL_URL='jdbc:mysql://localhost:3306/gaser_vrms'
export MYSQL_USERNAME='your_mysql_user'
export MYSQL_PASSWORD='your_mysql_password'
./mvnw spring-boot:run
```

The JPA configuration currently sets `spring.jpa.hibernate.ddl-auto=update`, so Hibernate updates the schema at startup. SQL logging is enabled. For production, use explicit schema migrations and disable SQL logging unless needed.

The service base URL is `http://localhost:8888`. No API documentation UI or OpenAPI specification is included yet.

## REST API

All routes are rooted under `/gaser`. JSON request/response bodies are used where applicable.

### Customers — `/gaser/customers`

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/` | Create a customer; returns `201 Created` |
| `GET` | `/` | List customers |
| `GET` | `/{id}` | Get a customer by ID |
| `PUT` | `/{id}` | Replace customer fields |
| `DELETE` | `/{id}` | Delete a customer; returns `204 No Content` |
| `GET` | `/email/{email}` | Find by email |
| `GET` | `/search?name={name}` | Case-insensitive partial name search |
| `GET` | `/phone/{phoneNumber}` | Find by phone number |

Customer create/update JSON:

```json
{
  "fullName": "Asha Patel",
  "email": "asha@example.com",
  "phoneNumber": "9876543210"
}
```

The phone validator currently accepts 10-digit Indian mobile numbers beginning with 6–9. Email is required and validated; customer email is unique.

### Vehicles — `/gaser/vehicles`

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/` | Create a vehicle |
| `GET` | `/` | List vehicles |
| `GET` | `/{id}` | Get a vehicle by ID |
| `PUT` | `/{id}` | Update vehicle details |
| `DELETE` | `/{id}` | Delete a vehicle |
| `GET` | `/search/owner?ownerName={name}` | Search by owner name |
| `GET` | `/search/vehicle-number?vehicleNumber={number}` | Search by vehicle number |
| `GET` | `/search/brand?brand={brand}` | Search by brand |

Vehicle fields include `vehicleNumber`, `brand`, `model`, `fuelType`, `purchaseYear`, and `customer`. The `customer` relationship is expected as an object containing an existing customer `id`, for example `"customer": {"id": 1}`. Supported fuel type values are petrol, diesel, electric, hybrid, CNG, LPG, and other (case-insensitive). Vehicle number is unique.

### Garages — `/gaser/garages`

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/` | Create a garage |
| `GET` | `/` | List garages |
| `GET` | `/{id}` | Get a garage by ID |
| `PUT` | `/{id}` | Update garage name and email |
| `DELETE` | `/{id}` | Delete a garage |

Garage fields include `garageName`, `garagePhoneNumber`, `garageMail`, `openingTime`, and `closingTime`. Times use the JSON time format `HH:mm:ss` (for example, `"09:00:00"`). The model validates required values and opening time before closing time. The update service currently changes only the name and email.

### Service records — `/gaser/services`

| Method | Path | Purpose |
| --- | --- | --- |
| `POST` | `/` | Create a service record |
| `GET` | `/` | List service records |
| `GET` | `/{id}` | Get a service record by ID |
| `PUT` | `/{id}` | Update service details |
| `DELETE` | `/{id}` | Delete a service record |
| `GET` | `/vehicle/{vehicleId}` | List records for a vehicle |

Fields include `serviceDateTime` (ISO local date-time), `serviceType`, `cost`, `odometerReading`, optional `nextServiceDate` (`YYYY-MM-DD`), optional `remarks`, `vehicle`, and `garage`. The create service resolves the referenced vehicle ID; the garage association is required by the entity. The repository contains additional query methods for date ranges and total cost, but no HTTP endpoints expose them.

## Project layout

```text
src/main/java/com/gb/gaser/
  Customer/       Customer entity, repository, service, controller
  Vehicle/        Vehicle entity, repository, service, controller
  Garage/         Garage entity, repository, service, controller
  ServiceRecord/  Service record entity, repository, service, controller
  User/           User account and role model
  custgarg/       Customer–garage association entity
  Dto/            Customer request/response DTOs
  Exceptions/     Not-found exception and REST exception advice
src/main/resources/
  application.properties
src/test/java/    Spring application context smoke test
```

## Build and tests

```bash
./mvnw clean package
./mvnw test
```

Windows users can use `mvnw.cmd` in place of `./mvnw`. The current test suite contains only a context-load smoke test; it requires a reachable MySQL database because the application datasource is configured through `MYSQL_URL`, `MYSQL_USERNAME`, and `MYSQL_PASSWORD`.

## Suggested next steps

1. **Make security explicit before deployment.** Add a `SecurityFilterChain`, authentication, password hashing, and role-based authorization; connect accounts to customers/garages and define account provisioning/approval. Until that exists, the security starter's default behavior may apply, while the role model alone does not enforce permissions.
2. **Add automated tests and a test database.** Cover controller/service validation, CRUD and relationship behavior, duplicate constraints, and error responses. Use an isolated test database such as Testcontainers or an appropriate in-memory configuration so tests do not rely on a developer's MySQL instance.
3. **Stabilize the API contract.** Use request/response DTOs for every resource, validate all create/update inputs, return consistent status codes, and define a structured error response. Verify relationship handling and prevent recursive or lazy entity serialization.
4. **Fix update and delete edge cases.** Vehicle updates currently ignore owner changes and do not apply `@Valid`; garage updates modify only name and email. Add uniqueness checks on garage updates and define safe deletion behavior where related records exist.
5. **Use database migrations and production configuration.** Replace automatic schema updates with Flyway or Liquibase, turn off SQL logging by default, and separate local/test/production settings with safe secret handling.
6. **Complete domain workflows.** Add endpoints and services for customer–garage associations, service history/date-range queries and cost totals, pagination/filtering, and clear ownership rules for who may access each record.
7. **Improve API discoverability and operations.** Add OpenAPI documentation, health/readiness checks, structured logging, and a repeatable local setup (for example, a compose file for MySQL and the app).

## Project status

This repository is an early backend implementation. The listed routes and persistence models describe code present in the repository; they do not imply that authentication, a frontend, production deployment, or end-to-end workflows are complete.
