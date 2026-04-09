# Full Stack Authentication Demo

This repository contains a simple authentication project with the required structure:

- `frontend/` - React application using Vite
- `backend/` - Spring Boot application using H2 database

## Features

- User registration
- User login
- Protected Home page
- Profile page that fetches full user details from the backend
- Logout with local storage cleanup
- Basic responsive styling

## Run the backend

1. Open a terminal in `backend/`
2. Run `mvn spring-boot:run`
3. Backend starts on `http://localhost:8080`

## Run the frontend

1. Open a terminal in `frontend/`
2. Run `npm install`
3. Run `npm run dev`
4. Frontend starts on `http://localhost:5173`

## API Endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/profile/{id}`
- `GET /api/auth/profile?username={username}`

## Notes

- Logged-in user data is stored in `localStorage`
- The backend uses an in-memory H2 database for demo purposes
- Passwords are stored as plain text in this practice project and should be hashed in real applications
