# EXP-13: Deployment of Full-Stack Application

This exercise contains a production-ready full-stack deployment flow for a React frontend and a Spring Boot backend.

## Project Structure

- `frontend/` - React application built with Vite
- `backend/` - Spring Boot REST API
- `deploy/nginx.conf` - sample Nginx configuration for serving the React build and proxying the API
- `deploy/apache-vhost.conf` - sample Apache virtual host configuration for the same setup

## Deployment Approach

This project supports two deployment styles:

1. Single JAR deployment
   The React production build is copied into `backend/src/main/resources/static`, then Spring Boot serves both the frontend and backend from one packaged JAR.

2. Separate web server deployment
   The React `dist` folder can be hosted by Nginx or Apache, while requests to `/students` are proxied to the Spring Boot backend.

## Environment Variables

### Frontend

The frontend reads the API base URL from `VITE_API_BASE_URL`.

- Development: `frontend/.env.development`
- Production: `frontend/.env.production`

Current behavior:

- development uses `http://localhost:8080`
- production uses an empty value so the browser calls the backend on the same host and port as the deployed frontend

### Backend

The backend reads the following environment variables from `backend/src/main/resources/application.properties`:

- `DB_URL`
- `DB_DRIVER`
- `DB_USERNAME`
- `DB_PASSWORD`
- `HIBERNATE_DIALECT`
- `SERVER_PORT`
- `APP_CORS_ALLOWED_ORIGINS`
- `H2_CONSOLE_ENABLED`

If MySQL values are not supplied, the application falls back to a local H2 file database for quick deployment verification.

## Build and Package

### 1. Install frontend dependencies

```powershell
cd frontend
npm.cmd install
```

### 2. Generate the React production build

```powershell
npm.cmd run build
```

This writes the production assets directly into:

```text
backend/src/main/resources/static
```

### 3. Package the Spring Boot backend as a JAR

```powershell
cd ..\backend
mvnw.cmd clean package
```

If `mvnw.cmd` is unavailable in your environment, use:

```powershell
mvn clean package
```

## Run the Packaged Application

```powershell
cd backend
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

Default URLs after startup:

- Frontend: `http://localhost:8080/`
- API: `http://localhost:8080/students`

## Optional Production Database Example

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/student_management?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Kolkata"
$env:DB_DRIVER="com.mysql.cj.jdbc.Driver"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your-password"
$env:HIBERNATE_DIALECT="org.hibernate.dialect.MySQLDialect"
$env:APP_CORS_ALLOWED_ORIGINS="http://localhost"
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

## Verification Checklist

After deployment, verify:

1. `GET /students` returns `200 OK`
2. The frontend loads from `/`
3. Adding a student sends `POST /students`
4. Editing a student sends `PUT /students/{id}`
5. Deleting a student sends `DELETE /students/{id}`
6. The UI reflects the backend changes without page reload

## Browser Testing

Open `http://localhost:8080/` in a browser and confirm:

- the student list loads
- create, update, and delete operations succeed
- no CORS error appears in browser developer tools

## Hosting Notes

- For a simple deployment, prefer the single Spring Boot JAR approach.
- For a reverse-proxy setup, use the sample files in `deploy/`.
