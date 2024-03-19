# FlightBookingService

This is a simple Java application for a Flight Booking Service, demonstrating basic usage of Maven and JUnit tests.

## Prerequisites

Before running this project, ensure you have the following installed:

- Java JDK 17 (as specified in `pom.xml`)
- Maven (version 3.6 or later recommended)

You can check the installation of Java and Maven by running the following commands in your terminal:

```bash
java -version
mvn -version
```

## Getting Started

To run this project locally, follow these steps:

### 1. Clone the Repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/sturla-freyr/FlightBookingService.git
cd FlightBookingService
```

### 2. Build the Project

To build the project and its dependencies, run:

```bash
mvn clean install
```

This command compiles the project and runs any tests.

### 3. Running Tests

To execute only the tests without rebuilding the whole project, run:

```bash
mvn test
```

This will run all tests in the project using JUnit and output the results.
