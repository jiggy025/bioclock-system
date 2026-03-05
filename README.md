Here is an **updated README** including your **exact environment versions** (Java, Hibernate, NetBeans). I also cleaned the structure a bit so it looks more like a **real open-source project README** while still clearly saying it's **work in progress**.

You can paste this into `README.md`.

---

# Bioclock System

![Java](https://img.shields.io/badge/Java-7-orange)
![Hibernate](https://img.shields.io/badge/Hibernate-5.1.17.Final-green)
![Status](https://img.shields.io/badge/Status-Work%20in%20Progress-yellow)

A **biometric device management system** built as a desktop application using **Java Swing**.
The system allows administrators to manage **biometric devices and employees** from a centralized interface.

This project is mainly a **learning and development project** focused on building a structured Java application using **MVC architecture, RMI communication, and Hibernate ORM**.

---

## Preview

Dashboard showing available biometric devices and management controls.

---

## Features

### Current Features

* Device dashboard
* Biometric device listing
* Add employee functionality
* Device assignment to employees
* Clean modernized Java Swing UI
* MVC-based architecture

### Planned Features

* Employee search
* Attendance logs
* Biometric enrollment management
* Device status monitoring
* User roles and permissions
* Reports and exports

---

## Technology Stack

### Frontend

* **Java Swing** (custom UI components)

### Backend

* **Java RMI** for client-server communication

### Database

* **MySQL**
* **Hibernate ORM**

### Architecture

* MVC Pattern
* DTO Pattern
* Repository Layer

---

## Development Environment

This project was developed using the following versions:

| Tool         | Version          |
| ------------ | ---------------- |
| Java         | **Java 7**       |
| Hibernate    | **5.1.17.Final** |
| NetBeans IDE | **8.1**          |
| Database     | **MySQL**        |

---

## Project Structure

```
bioclock-system
│
├── client
│   UI components, panels, and views
│
├── server
│   RMI services and business logic
│
├── common
│   Shared DTOs, interfaces, and models
│
└── database
    Entities and Hibernate configuration
```

---

## Installation

Clone the repository:

```bash
git clone https://github.com/jiggy025/bioclock-system.git
```

Open the project in:

* NetBeans 8.1
* IntelliJ IDEA
* Eclipse

Configure the following before running:

1. MySQL database connection
2. Hibernate configuration
3. Start the RMI server

Run order:

```
1. Start Server
2. Launch Client Application
```

---

## Project Status

🚧 **Work in Progress**

This system is still actively being developed.
Features, UI, and architecture may change as development continues.

---

## Goals of the Project

* Build a **modern Java Swing desktop system**
* Practice **client-server architecture using Java RMI**
* Implement **Hibernate ORM with MySQL**
* Create a **clean modular Java application structure**

---

## Author

**Jiggy Palconit**

GitHub
[https://github.com/jiggy025](https://github.com/jiggy025)

---

## Future Improvements

* Better UI styling and consistency
* Device communication integration
* REST API alternative to RMI
* Improved reporting features
* Dockerized deployment

These make your GitHub look **like a real production system**, not just a school project.
