# Full-Stack CRUD Application using React and Spring Boot

This repository contains a student management system with the required structure:

- `backend/` - Spring Boot REST API
- `frontend/` - React application

## Deployment Overview

This project is prepared for production deployment with:

- React environment variables via `VITE_API_BASE_URL`
- Spring Boot environment variables for database and port configuration
- React production assets emitted directly into `backend/src/main/resources/static`
- A single deployable Spring Boot JAR that serves both the frontend and backend
- A default embedded H2 database for local verification when MySQL credentials are not supplied

## Backend

The Spring Boot backend exposes:

- `POST /students`
- `GET /students`
- `PUT /students/{id}`
- `DELETE /students/{id}`

It uses:

- `@RestController`
- Service layer
- Repository layer
- `ResponseEntity`
- MySQL database

Run the backend:

```bash
cd backend
mvn spring-boot:run
```

The backend starts on `http://localhost:8080`.

Database configuration in [application.properties](c:/Users/marth/OneDrive/Desktop/Fullstack-Skill/EXP-12/backend/src/main/resources/application.properties):

- `DB_URL`
- `DB_DRIVER`
- `DB_USERNAME`
- `DB_PASSWORD`
- `HIBERNATE_DIALECT`
- `SERVER_PORT`
- `APP_CORS_ALLOWED_ORIGINS`

For production MySQL, set values such as:

```bash
set DB_URL=jdbc:mysql://localhost:3306/sec-51?createDatabaseIfNotExist=true^&useSSL=false^&allowPublicKeyRetrieval=true^&serverTimezone=Asia/Kolkata
set DB_DRIVER=com.mysql.cj.jdbc.Driver
set DB_USERNAME=admin
set DB_PASSWORD=your-password
set HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect
```

If those variables are not supplied, the JAR starts with the embedded H2 database for local deployment verification.

## Frontend

The React frontend includes:

- `AddStudent` form
- `StudentList` table
- axios integration for CRUD operations
- immediate UI updates after add, update, and delete

Run the frontend:

```bash
cd frontend
npm install
npm run dev
```

If PowerShell blocks `npm`, use:

```bash
npm.cmd install
npm.cmd run dev
```

The frontend starts on `http://localhost:5173`.

## Production Build and Packaging

Build the React app:

```bash
cd frontend
npm.cmd run build
```

This writes the production files into the Spring Boot static folder.

Package the backend JAR:

```bash
cd ../backend
mvnw.cmd clean package
```

Run the packaged application with the default embedded database:

```bash
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

After startup:

- Frontend: `http://localhost:8080/`
- API: `http://localhost:8080/students`
