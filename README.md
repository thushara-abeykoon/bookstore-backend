# Bookstore Backend

This repository contains the backend for a bookstore application. It is built using Spring Boot and integrates with a MySQL database. The application supports JWT authentication and provides various endpoints for managing authors, books, and users.

## Features

- **JWT Authentication**: Secure login using JWT tokens.
- **CRUD Operations**: Manage authors, books, and users.
- **Database Integration**: Uses MySQL for data persistence.
- **Spring Security**: Configured for securing endpoints.

## Endpoints

### Author

- **Register a new author**: `POST /api/v1/author/register`
- **Get all authors**: `GET /api/v1/author/get-all`
- **Get author by email**: `GET /api/v1/author/get/{email}`
- **Update an existing author**: `PUT /api/v1/author/update/{id}`
- **Delete an author**: `DELETE /api/v1/author/delete/{email}`

### Book

- **Register a new book**: `POST /api/v1/book/register`
- **Get all books**: `GET /api/v1/book/get-all`
- **Get all books by author**: `GET /api/v1/book/get/by-author/{email}`
- **Search books**: `GET /api/v1/book/search/{isbn}`
- **Get one book**: `GET /api/v1/book/get/{isbn}`
- **Update book**: `PUT /api/v1/book/update`
- **Delete book**: `DELETE /api/v1/book/delete/{isbn}`

### User

- **Update user**: `PUT /api/v1/user/update/{id}`
- **Get user by username**: `GET /api/v1/user/get/by-username/{username}`
- **Get user by ID**: `GET /api/v1/user/get/by-id/{id}`
- **Get all users**: `GET /api/v1/user/get-all`
- **Delete user**: `DELETE /api/v1/user/delete/{id}`

### Authentication

- **Login**: `POST /api/v1/auth/login`
- **Register**: `POST /api/v1/auth/register`

## Running the Backend

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL database

### Steps to Run

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/thushara-abeykoon/bookstore-backend.git
    cd bookstore-backend
    ```

2. **Setup MySQL Database**:
   - Ensure MySQL is running and create a database named `bookstore`.
   - Update the `application.properties` file with your MySQL configurations.

3. **Build the Project**:
    ```sh
    ./mvnw clean install
    ```

4. **Run the Application**:
    ```sh
    ./mvnw spring-boot:run
    ```

5. **Accessing the Application**:
    - The backend will be running at `http://localhost:8080`.
    - Use Postman or any API client to interact with the endpoints.


## Database Configuration

Ensure your `application.properties` file contains the following configurations:

```properties
spring.datasource.url=jdbc:mysql://localhost:3308/bookstore
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
