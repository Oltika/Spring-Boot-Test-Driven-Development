# Spring Boot TDD Demo

Lean Spring Boot project that shows how to build a Customer CRUD API using Test-Driven Development. It uses Spring Data JPA with PostgreSQL (H2 for tests), JUnit 5, and Mockito to demonstrate a small, production-style service with validation and clear error handling.

## Project overview

- Spring Boot version: 3.0.4
- Java: 21 (project property)
- Build tool: Maven
- Database: PostgreSQL (with H2 for tests)
- ORM: Spring Data JPA / Hibernate
- Mocking framework: Mockito
- Testing framework: JUnit 5
- REST API: Spring Web MVC

Note: Spring Boot 3.0.x targets Java 17 as its baseline. This project is configured for Java 21 and has been verified with that runtime; keep the Java version aligned across your local JDK, Maven toolchain, and deployment environment.

This project provides a minimal REST/backend service around a Customer entity and includes unit and integration tests.

## What the API does

- CRUD for customers: create, read by id, list all, update, and delete.
- Validates input and returns clear HTTP errors for bad requests, conflicts, and missing records.

## Prerequisites

- Java 21 JDK installed and `JAVA_HOME` set.
- Maven 3.6+ installed.
- Docker (recommended) if you want to run a local PostgreSQL instance using the provided script.
- Optional: A PostgreSQL client (e.g. `psql`) to inspect the database.

## Database configuration

By default the application is configured to connect to a local PostgreSQL instance. See `src/main/resources/application.properties`

Local DB helper scripts and SQL are in the `bin/` folder:

- `bin/postgres-start.sh` - starts a Postgres Docker container on port 5432 with user `postgres` and password `postgres`.
- `bin/schema.sql` - schema statements you can load into the DB.
- `bin/data.sql` and `bin/postgres-data.sql` - example seed data files.

To start a local PostgreSQL container (requires Docker):

```bash
# make script executable once
chmod +x bin/postgres-start.sh
# run the helper script
bin/postgres-start.sh
```

Once Postgres is running, you can apply `bin/schema.sql` and `bin/postgres-data.sql` with `psql` or a DB client, or let your migration process create schema.

Note: Tests use an embedded H2 database (test scope) so unit tests and many integration tests run without an external Postgres instance.

## Build & run

From the project root you can build and run the application with Maven (ensure Java 21 is selected in your toolchain).

Build (package the app):

```bash
mvn -DskipTests package
```

Run the app using Maven:

```bash
mvn spring-boot:run
```

Or run the built jar after packaging:

```bash
java -jar target/testing-system-0.0.1-SNAPSHOT.jar
```

The app will start on the default port (8080) unless overridden via `application.properties` or environment variables.

## Docker

The project includes Docker configuration files for containerized deployment.

### Docker Compose

The `docker-compose.yaml` file provides an easy way to run both the application and PostgreSQL database together. 

To use Docker Compose:

```bash
# Start both services (app and postgres)
docker-compose up

# Start in detached mode (background)
docker-compose up -d

# Stop services
docker-compose down

# Stop and remove volumes (clean slate)
docker-compose down -v

# View logs
docker-compose logs -f app
```

The application will be available at `http://localhost:8080` and PostgreSQL at `localhost:5432` once the containers start.

### Environment Variables (.env)

The `docker-compose.yaml` file uses environment variables from a `.env` file for database configuration. Create a `.env` file in the project root with the following variables:

```bash
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=postgres
```

Docker Compose automatically loads these variables from the `.env` file. You can customize these values according to your needs without modifying the `docker-compose.yaml` file.


## Tests

Run all tests with:

```bash
mvn test
```

Unit tests run on an in-memory H2 database and should run without external services. Integration tests may require additional setup depending on the test.


## Useful files & structure

- `src/main/java` - main application code
    - `data/entity/` - JPA entities
    - `data/repository/` - Spring Data JPA repositories
    - `service/` - service layer classes
    - `web/controller/` - REST controllers
    - `web/error/` - custom exceptions and error handling
    - `model/` - API DTOs
    - `TestingSystemApplication.java` - main Spring Boot application class
- `src/main/resources/application.properties` - main configuration
- `src/test` - unit and integration tests (includes H2 test data in `src/test/resources`)
- `bin/` - database helper SQL and scripts
- `Dockerfile` - Docker configuration for containerized deployment
- `docker-compose.yaml` - Docker Compose configuration for running application and PostgreSQL together

## Troubleshooting

- If you see database connection errors, confirm Postgres is running on `localhost:5432` and credentials match `application.properties`.
- If you prefer a different DB or schema, update `application.properties` accordingly.

---

## Maintenance
Ongoing expansion and maintenance of this project will be provided to the fullest extent possible.
