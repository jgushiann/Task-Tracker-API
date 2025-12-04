# Task-Tracker-API

The Task Tracker API is a backend service that allows users to manage daily tasks efficiently through RESTful endpoints. Users can create, update, delete, and organize their tasks by priority, category, status, and due date. The project follows a clean three-layer architecture (Controller → Service → Repository) and implements modular, testable, and maintainable code.

It’s built using Spring Boot, with JPA/Hibernate for database communication and PostgreSQL as the data storage layer.

## Core features
- User Management
   - Register and login users
   - Manage user profiles
   - Link tasks to a specific user
- Task Management
   - Create, read, update and delete tasks (CRUD)
   - Tasks have attributes like title, description, category, priority, status, and due date
- Category and Priority
   - Assign tasks to categories (Work, University, Personal)
   - Mark tasks as Low, Medium, High priority
- Search and Filtering
   - Search/Filter tasks by its attributes
- Security and Validation
   - JWT-based authentication and authorization
   - Role-based access control (e.g., admin-only endpoints)
   - Global exception handling for consistent error responses
  
## Architecture
The project uses a three-layer structure:
- Controller Layer: Handles REST requests
- Service Layer: Contains business logic
- Repository Layer: Handles database operations using Spring Data JPA

## Technologies used:
- Java 17
- Spring Boot 3.5.6
- PostgreSQL
- Spring data JPA
- Spring Security
- Maven
- Docker & Docker Compose for containerized deployment
- Bruno for testing endpoints

## Getting started:
1. Clone the Repository
   ```
   git clone https://github.com/jgushiann/Task-Tracker-API.git
   cd Task-Tracker-API/TaskTrackerAPI/TaskTrackerAPI
   ```
2. Set up environment variables
3. Build and run with Docker
4. Testing Endpoints
   - You can test endpoints using Bruno, Postman, or any REST client.
   
  
