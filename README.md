# JWT Authentication and Role Authorization

This project implements JWT-based authentication for a Spring Boot backend with role-based access control.

## Features

- `POST /login` generates a JWT token for valid credentials.
- `POST /admin/add` is accessible only to users with the `ADMIN` role.
- `DELETE /admin/delete/{employeeId}` is accessible only to users with the `ADMIN` role.
- `GET /employee/profile` is accessible only to users with the `EMPLOYEE` role.
- H2 in-memory database is used to store users.
- Seeded users are created automatically on startup.

## Seed Users

- `admin` / `admin123` -> `ADMIN`
- `employee` / `employee123` -> `EMPLOYEE`

## Run the Project

```bash
mvn clean test
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## Postman Testing

### Login

`POST http://localhost:8080/login`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

### Test secured endpoints

- `POST /admin/add` with `Authorization: Bearer <admin-token>` returns `200 OK`
- `DELETE /admin/delete/101` with `Authorization: Bearer <admin-token>` returns `200 OK`
- `GET /employee/profile` with `Authorization: Bearer <employee-token>` returns `200 OK`

### Negative cases

- No token on `/admin/add` -> `401 Unauthorized`
- Invalid token on `/employee/profile` -> `401 Unauthorized`
- EMPLOYEE token on `/admin/add` -> `403 Forbidden`
- ADMIN token on `/employee/profile` -> `403 Forbidden`
- Invalid password on `/login` -> `401 Unauthorized`

## H2 Console

The H2 console is enabled at `http://localhost:8080/h2-console`.

- JDBC URL: `jdbc:h2:mem:jwtauthdb`
- Username: `sa`
- Password: leave blank

## GitHub

The backend is ready to be committed and pushed to a single GitHub repository. The push itself was not performed here because no remote repository URL or GitHub credentials were available in this workspace.
