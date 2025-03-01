# My Spring Boot Application

## Features
- **WHAT SHOULD THE APP DO**
- **Spring Security with PostgreSQL**: Secure authentication using Basic Auth.
- **Dockerized Deployment**: Run the app and PostgreSQL in Docker using Docker Compose
- **GitHub Actions CI**: Automated testing and Docker image build on push

## 🛠️ Local Setup Instructions

### Prerequisites
Ensure you have the following installed:
- **Java 23**
- **Docker & Docker Compose**
- **Gradle**
- **Git**

### Run Locally

#### 1️⃣ Clone the Repository

#### 2️⃣ Build the Project
```bash
./gradlew clean build
```

#### 3️⃣ Run the Application with Docker
```bash
docker-compose up --build
```
This will start **PostgreSQL** and your Spring Boot app in the same network.

#### 4️⃣ Access the API
- **Base URL**: `http://localhost:8080`
- **Authenticate with Basic Auth** using credentials stored in the database. (admin/password by default)

#### 5️⃣ Running Tests
```bash
./gradlew test
```

## 🛠️ CI/CD Workflow
- On **push to main**, GitHub Actions:
  - Runs tests
  - Builds the application
  - Builds a Docker image

