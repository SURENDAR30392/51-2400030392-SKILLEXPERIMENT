# Spring Boot JWT Authentication Demo

This project demonstrates JWT-based authentication and role-based authorization using Spring Boot.

## Features
- User entity with username, password, and role (ADMIN/EMPLOYEE)
- `/login` endpoint to generate JWT token
- JWT filter for authentication
- Role-based access to endpoints:
  - `/admin/add` (ADMIN only)
  - `/admin/delete/{id}` (ADMIN only)
  - `/employee/profile` (EMPLOYEE only)
- H2 in-memory database

## How to Run
1. Build the project: `mvn clean install`
2. Run the app: `mvn spring-boot:run`
3. Use Postman to test endpoints:
   - Login: `POST /login` with `{ "username": "admin", "password": "adminpass" }`
   - Use the returned JWT as `Bearer <token>` in the Authorization header for other endpoints.

## Testing
- Test all endpoints with and without JWT tokens.
- Only ADMIN can add/delete employees.
- EMPLOYEE can access their profile.

## GitHub
Push this project to a GitHub repository for version control and sharing.
