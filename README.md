# APITestAutomation

## Overview

Welcome to **APITestAutomation**, a project dedicated to exploring and mastering API testing using Java and Rest-Assured. This repository is the culmination of my self-learning journey into API automation, showcasing various testing scenarios and best practices.

## Author

👩‍💻 **Carolina Steadham**  


## Table of Contents

- [What is this Repository About?](#what-is-this-repository-about)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Scenarios Covered](#scenarios-covered)
- [Getting Started](#getting-started)
- [Skills Demonstrated](#skills-demonstrated)


## What is this Repository About?

This project serves as a practical guide to API testing using the Rest-Assured framework. It includes example codes ranging from basic tests to comprehensive end-to-end API automation scenarios. The tests are executed against the fake REST APIs provided by [Reqres.in](https://reqres.in/), allowing for safe and controlled testing environments.

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
