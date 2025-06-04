# Restaurant Management System - Full Stack Application

This project is a comprehensive restaurant management system built with Spring Boot for the backend and Angular for the frontend.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Features

### Backend Features
- User authentication and authorization
- Menu management (CRUD operations)
- Order processing system
- Table/reservation management
- Staff management
- Reporting and analytics

### Frontend Features
- Responsive design with Tailwind CSS
- Interactive dashboard
- Real-time order tracking
- Customer management interface
- Inventory management
- Admin panel

## Technologies Used

### Backend
- **Spring Boot** 3.x
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **Hibernate** - ORM framework
- **Oracle Database** - Primary database system
- **Maven** - Dependency management
- **Lombok** - Reducing boilerplate code

### Frontend
- **Angular** 15+
- **TypeScript**
- **Tailwind CSS** - Styling framework
- **RxJS** - Reactive programming
- **Angular Material** - UI components

### Others
- **RESTful APIs** - Communication between frontend and backend
- **JWT** - Authentication tokens
- **Swagger** - API documentation

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java JDK 17+
- Node.js 16+
- Angular CLI 15+
- Oracle Database 19c or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, VS Code, or Eclipse)

## Installation

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/AbdurahmanHussien/Restaurant-application.git
   cd restaurant-management-system/backend
Configure your Oracle database connection in application.properties:

properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL
spring.datasource.username=your_username
spring.datasource.password=your_password
Build the project:

bash
mvn clean install
Frontend Setup
Navigate to the frontend directory:

bash
cd ../fron-tend
Install dependencies:

bash
npm install
Configuration
Backend Configuration
Update application.properties with your specific configurations:

Database credentials

JWT secret key

Server port

CORS settings

Frontend Configuration
Update environment.ts with your backend API URL:

typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
Running the Application
Start the Backend
bash
cd backend
mvn spring-boot:run
Start the Frontend
bash
cd frontend
ng serve
The application will be available at:

Backend: http://localhost:8080

Frontend: http://localhost:4200

API Documentation
API documentation is available via Swagger UI when the backend is running:

http://localhost:8080/swagger-ui.html


# Project Structure
restaurant-management-system/
├── back-end/
│   ├── src/
│   │   ├── main/java/com/restaurant/
│   │   │   ├── config/          # Spring configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── exception/       # Exception handling
│   │   │   ├── model/           # Entity classes
│   │   │   ├── repository/      # JPA repositories
│   │   │   ├── security/        # Security configuration
│   │   │   └── service/         # Business logic
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/          # Static resources
│   └── pom.xml                  # Maven dependencies
└── front-end/
    ├── src/
    │   ├── app/
    │   │   ├── components/      # Angular components
    │   │   ├── models/          # TypeScript interfaces
    │   │   ├── services/        # Angular services
    │   │   ├── guards/          # Route guards
    │   │   └── shared/          # Shared modules
    │   │   └── interceptors/     # interceptor modules
    │   ├── assets/              # Static assets
    │   └── environments/        # Environment configurations
    └── package.json             # Frontend dependencies

## Screenshots

### Home Page
![Home Page](/others/screenshots/home.png)
*Modern responsive homepage with featured menu items and promotions*

### Login Page
![Login Page](/others/screenshots/login.png)
*Secure login interface with form validation*

### Signup Page
![Signup Page](/others/screenshots/sign.png)
*User registration form with password strength meter*

Contributing
Contributions are welcome! Please follow these steps:

Fork the project

Create your feature branch (git checkout -b feature/AmazingFeature)

Commit your changes (git commit -m 'Add some AmazingFeature')

Push to the branch (git push origin feature/AmazingFeature)

Open a Pull Request


