
# APITestAutomation

## Overview

Welcome to **APITestAutomation**, a project dedicated to exploring and mastering API testing using Java and Rest-Assured. This repository is showcasing various testing scenarios and best practices.

## Author

👩‍💻 **Carolina Steadham**  


## Table of Contents

- [What is this Repository About?](#what-is-this-repository-about)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Scenarios Covered](#scenarios-covered)
- [Skills Demonstrated](#skills-demonstrated)


## What is this Repository About?

This project serves as a practical guide to API testing using the Rest-Assured framework. It includes example codes ranging from basic tests to comprehensive end-to-end API automation scenarios. By leveraging the Rest-Assured framework for building and sending HTTP requests and TestNG for managing test execution, the repository showcases testing against public APIs, such as Reqres.in, while also integrating authenticated requests against Postman-Echo.com. It applies essential best practices by externalizing test data in JSON files, enhancing reusability and security.

## Technologies Used

- **Java**: Core programming language for test development.
- **Rest-Assured**: Simplifies the testing of REST services in Java.
- **TestNG**: Testing framework for structuring and running tests.
- **Hamcrest Matchers**: Provides a library of matcher objects for making assertions.
- **ExtentReports**: Generates detailed and visually appealing HTML reports.
- **Maven**: Manages project dependencies and build lifecycle.
- **JSON**: Format for request and response payloads.
- **HTML**: Used in report generation.

## Project Structure

```
APITestAutomation/
├── .idea/                 # IDE configuration files
├── reports/               # Generated test reports
├── resources/             # Test data and configuration files
├── src/                   # Source code for tests
│   └── test/
│       └── java/
│           └── ...        # Test classes and packages
├── config.properties      # Configuration settings
├── pom.xml                # Maven project file
└── README.md              # Project documentation
```

## Scenarios Covered

- **HTTP Methods**:
  - GET
  - POST
  - PUT
  - PATCH
  - DELETE
- **Response Validation**:
  - Status Code Verification
  - Response Body Verification
  - JSON Schema Validation
- **Data Handling**:
  - Extracting values from Response Body
  - Creating POJOs for request payloads
- **Assertions**:
  - Utilizing Hamcrest Matchers for robust assertions
  - TestNG assertions
- **Authentication & Authorization**: Demonstrating various authentication mechanisms to test protected endpoints, including:
  - Basic Authentication
  - Digest Authentication
  - Authorization with Bearer Tokens (by extracting a token from one request to use in another)
- **Test Data Management**: Using external data sources (like JSON files) to separate test data from the test logic, enabling more flexible and data-driven test scenarios.
- **Response Validation**: Thorough validation of API responses to ensure correctness and data integrity, including:
  - Status Code Verification (e.g., 200, 201, 400, 404)
  - Response Body Verification (checking for specific values, presence of keys)
  - JSON Schema Validation (ensuring the response structure is correct)
- **End-to-End Scenarios**: Building complex test flows that involve chaining multiple API calls, such as retrieving a token from one endpoint and using it to authorize a subsequent request.
- **Negative Testing**: Validating that the API handles invalid inputs gracefully and returns appropriate error codes, ensuring the system is resilient.
- **Ergast API for Formula 1**: Testing and validating data from the Ergast Developer API for historical Formula 1 race data.
- **Logging and Reporting**:
  - Implementing TestNG Listeners
  - Generating ExtentReports for test execution

## Skills Demonstrated

- **API Testing**: Crafting and executing tests for RESTful APIs.
- **Java Programming**: Utilizing Java for test development.
- **Framework Utilization**: Implementing Rest-Assured, TestNG, and ExtentReports.
- **Test Automation**: Automating test scenarios for efficient regression testing.
- **Data Validation**: Ensuring data integrity through JSON schema validation and assertions.


---

*This project is a testament to continuous learning and the pursuit of excellence.*
