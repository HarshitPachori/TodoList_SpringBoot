## TodoList App Backend Spring Boot

## Introduction

TodoList Backend is a Spring Boot application that provides API endpoints for managing a todo list. It allows users to register, login, and manage their todo items.

## Prerequisites

Before running the application, make sure you have the following installed on your system:

- Java 11
- Maven
- MySQL database

## Getting Started

1. Clone the repository:
2. Navigate to the project directory:
3. Create a MySQL database named `todolist_db`. You can change the database name in `application.properties` if needed.
4. Start the application using Maven:
5. Your server will start on `http://localhost:8080`

## Configuration

The application uses the default configuration for development. You can modify the database connection settings in `src/main/resources/application.properties`.

## API Documentation

Please refer to the [Postman Collection](https://www.postman.com/technical-astronomer-14365988/workspace/todolist-backend/collection/23880545-0efec280-1a21-46ec-bf47-5a00401e825b?action=share&creator=23880545) for detailed API documentation. Import this collection into Postman to access and test the API endpoints.

For Swagger 3 documentation go to your browser and open `http://localhost:8080/swagger-ui` to view the swagger documentation of the different controllers and API's.

## Usage

### Register a User

To register a new user, send a `POST` request to `/api/register` with the following JSON payload:

{
"username": "testuser",
"email": "test@example.com",
"password": "testpassword"
}

### Login

To authenticate a user and obtain a JWT token, send a `POST` request to `/api/login` with the following JSON payload:

{
"email": "test@example.com",
"password": "testpassword"
}

## `Now for all the other URL's you have to pass jwtToken of the authenticated user in the Authorization header otherwise get ACCESS DENIED ! related error.`

### Create a Todo List

To create a new todo item for an authenticated user, send a `POST` request to `/api/users/{userId}/todolist` with the following JSON payload:

{
    "todoListName": "Food"
}

### Get a Todo List by Id

To create a new todo item for an authenticated user, send a `GET` request to `/api/users/todolist{todoListId}`.

### Get all Todo List by User

To create a new todo item for an authenticated user, send a `GET` request to `/api/users/{userId}/todolist`.

### Delete Todo List by Id

To create a new todo item for an authenticated user, send a `DELETE` request to `/api/users/{userId}/todolist`.


### Create a Todo Item

To create a new todo item for an authenticated user, send a `POST` request to `/api/todos` with the following JSON payload:

{
"title": "Buy groceries",
"description": "Milk, eggs, bread"
}

### Get All Todo Items

To get all todo items for an authenticated user, send a `GET` request to `/api/todos`.

## Deployment

To deploy the application to a production environment, you can follow the standard Spring Boot deployment process on your preferred platform.

## Troubleshooting

<!-- - If you encounter any issues, please check the application logs for error messages. -->

- Make sure the database is running and the connection settings in `application.properties` are correct.

<!-- ## Contributing
Contributions to this project are welcome! If you find any bugs or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. -->
