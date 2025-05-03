# Project Overview

Lấy model trong modelTemp ra xách vào đúng ffolder rồi code. ModelTemp chỉ để tạo database thôi.

This project is built using the following technologies:

- **PostgreSQL 16**
- **Java 21**
- **Spring Boot 3.2**

## How to Run the Project

1. **Build the project**:

   ```bash
   npm install
   mvn clean install
   ```

2. **Run the Spring Boot application**:

   ```bash
   mvn spring-boot:run
   ```

## Project Structure

The project consists of **11 modules**:

- **Booking**
- **Bot**
- **Delivery**
- **EatIn**
- **Kitchen**
- **Menu**
- **Report**
- **Schedule**
- **TakeOut**
- **User** (Basic structure completed)

**Shared Library**:
- **Library**: Contains commonly used classes and utilities shared across modules.

### Module Structure (Example: User Module)

Each module follows the same structure. Below is the breakdown of the module structure:

#### `api`
- **dto**: Contains DTO (Data Transfer Object) classes. These classes are used to transfer data between modules.
- **event**: Contains Event classes. These are used to transfer data asynchronously between modules.
- **mapper**: Contains Mapper classes. These classes are responsible for converting between DTOs and Entities.
- **service**: Contains Service classes. These are responsible for handling the business logic of the module. Other modules communicate with this module through Service interfaces.

#### `common`
- Temporarily contains status codes and messages (e.g., `Api___Message.java`).

#### `controller`
- Contains Controller classes. These classes handle requests from the client.

#### `model`
- Contains Entity classes. These classes are used to store data in the database.

#### `repository`
- Contains Repository classes. These classes are responsible for querying the database.

#### `service`
- Contains Service implementation classes. These classes provide the actual implementation for the Service interfaces.

### Communication Between Modules

- **Asynchronous Communication**: Modules communicate via Events.
- **Synchronous Communication**: Modules communicate via Services.

### API Declaration and Dependencies

When using the `@ApplicationModule` annotation in your module, don't forget to declare the allowed dependencies in `package-info.java`. Example:

```java
@org.springframework.modulith.ApplicationModule(
    allowedDependencies = "booking :: *, bot :: *"
)
package com.menuplus.backend.bot;
```

This ensures proper communication and dependencies between the modules.

---

## Library Structure

The **Library** module contains shared classes used across all modules. It follows the structure below:

- **dto**: Contains basic DTO classes, such as `ListRequest`, which are commonly inherited and used in multiple modules.
- **enumeration**: Contains Enum classes. These define fixed values, such as status codes or predefined constants. New enums should be added here.
- **common**: Contains commonly used classes such as `Response`, `PageResponse`, etc.
- **util**: Contains utility classes like `DateUtil`, `StringUtil`, and other helper classes.
- **configuration**: Contains configuration classes for application setup.
- **security**: Contains security-related classes and configurations.
- **exception**: Contains custom exception classes for error handling.

## Contribution Guidelines

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

---

Let me know if you need any further modifications or clarifications!