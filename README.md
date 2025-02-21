# Intern Intelligence Movie API

![Java](https://img.shields.io/badge/Java-21-blue?logo=java) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.3-green?logo=spring-boot) ![MongoDB](https://img.shields.io/badge/MongoDB-6.0-brightgreen?logo=mongodb) ![License](https://img.shields.io/badge/License-MIT-yellow)

## 🎬 Overview

**Intern Intelligence Movie API** is an advanced RESTful API built with **Spring Boot** that manages a movie collection. It supports full CRUD operations, allowing users to create, read, update, and delete movies, as well as filter by genre and search by director. MongoDB is used for efficient data storage, and Swagger UI is integrated for clear API documentation.

## 🚀 Features

- 🎥 Create, Read, Update, and Delete Movies
- 🔍 Search movies by director
- 🎭 Filter movies by genre
- 📊 MongoDB integration for data persistence
- 📖 Swagger UI for interactive API documentation
- ✅ Robust testing using JUnit and Mockito

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.2.3**
- **MongoDB**
- **Swagger/OpenAPI**
- **JUnit 5**
- **Mockito**
- **Gradle**

## 📂 Project Structure

```
intern-intelligence-movie-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/internintelligence/movieapi/
│   │   │       ├── config/          # Swagger configuration
│   │   │       ├── controller/      # REST controllers
│   │   │       ├── dto/             # Data Transfer Objects
│   │   │       ├── model/           # MongoDB document models
│   │   │       ├── repository/      # MongoDB repositories
│   │   │       ├── service/         # Business logic services
│   │   │       └── MovieApiApplication.java  # Main application class
│   │   └── resources/
│   │       ├── application.properties  # App configuration
│
├── build.gradle  # Project dependencies and build configuration
└── README.md      # Project documentation
```

## ⚙️ Installation & Running

### Prerequisites
- Java 21 installed
- MongoDB running locally or connected via URI
- Git

### Clone the Repository
```bash
git clone https://github.com/Maharram-Heydarov/intern-intelligence-movie-api.git
cd intern-intelligence-movie-api
```

### Run the Application
Using Gradle:
```bash
./gradlew bootRun
```
Or using IntelliJ IDEA:
- Open the project.
- Run the `MovieApiApplication` class.

### MongoDB Configuration
Update `src/main/resources/application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/moviedb
```

## 📖 API Documentation

After running the application, access Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```

## 🔍 Example API Endpoints

| Method | Endpoint                   | Description              |
|-------|----------------------------|--------------------------|
| POST  | `/api/movies`              | Create a new movie       |
| GET   | `/api/movies`              | Get all movies           |
| GET   | `/api/movies/{id}`         | Get movie by ID          |
| PUT   | `/api/movies/{id}`         | Update movie by ID       |
| DELETE| `/api/movies/{id}`         | Delete movie by ID       |
| GET   | `/api/movies/genre/{genre}`| Get movies by genre      |
| GET   | `/api/movies/director`     | Search movies by director|

## ✅ Running Tests

Run all tests using Gradle:
```bash
./gradlew test
```

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 🙌 Acknowledgments

Developed as part of the **Intern Intelligence** internship program.

---

⭐ If you like this project, give it a star on [GitHub](https://github.com/Maharram-Heydarov/intern-intelligence-movie-api)!

