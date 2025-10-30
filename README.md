# Task-Tracker-API

The Task Tracker API is a backend service that allows users to manage daily tasks efficiently through RESTful endpoints. Users can create, update, delete, and organize their tasks by priority, category, and due date. The project follows a three-layer architecture (Controller -> Service -> Repository) and implements clean, modular, and testable code.

It’s built using Spring Boot, with JPA/Hibernate for database communication and PostgreSQL as the data storage layer.

## Core features
- User Management
   - Register, Login, Manage profile
   - Link each task to a user
- Task Management
   - Create, read, update and delete tasks (CRUD operations)
   - Add attributes like title, description, category, priority, status and due date
- Category and Priority
   - Assign tasks to categories (Work, University, Personal)
   - Mark tasks as Low, Medium, High priority
- Search and Filtering
   - Search tasks by keyword, due date and title
   - Sort tasks by due date or priority
- Validation and Error Handling
   - implement global exception handling
  
## Architecture
Three-layer structure
- Controller Layer: Handles REST requests
- Service Layer: Contains business logic
- Repository Layer: Handles database operations via JPA

## Technologies used:
- Java 17
- Spring Boot 3.5.6
- PostgreSQL
- Spring data JPA
- Maven
- Bruno for testing endpoints
  
