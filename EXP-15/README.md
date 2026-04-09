# EXP-15: Spring Security with JWT and RBAC

This project demonstrates:

- JWT-based login using `POST /login`
- Role-based access control for `ADMIN` and `EMPLOYEE`
- Protected Spring Security endpoints using a custom JWT filter

## Default Users

- `admin / admin123` with role `ADMIN`
- `employee / employee123` with role `EMPLOYEE`

## Endpoints

- `POST /login`
- `POST /admin/add` for `ADMIN` only
- `DELETE /admin/delete` for `ADMIN` only
- `GET /employee/profile` for `EMPLOYEE` and `ADMIN`

## Run

```bash
mvn spring-boot:run
```

## Postman Testing

1. Login as admin:

```http
POST /login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

2. Copy the `token` from the response.
3. Add the header `Authorization: Bearer <token>` in secured requests.
4. Test:
   - `POST /admin/add` with admin token -> `200 OK`
   - `DELETE /admin/delete` with admin token -> `200 OK`
   - `GET /employee/profile` with employee token -> `200 OK`
   - `POST /admin/add` with employee token -> `403 Forbidden`
   - Any secured endpoint without token -> `401 Unauthorized`
   - Any secured endpoint with invalid token -> `401 Unauthorized`
