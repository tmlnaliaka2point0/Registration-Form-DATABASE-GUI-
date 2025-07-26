# User Registration Form (Java Swing with MySQL)

## Short Summary

This project is a simple Java Swing desktop application that provides a graphical user interface (GUI) for registering users. It allows users to input their ID, Name, Gender, Date of Birth (DOB), Address, and Contact information. The entered data is then stored in a MySQL database. The application also features a table that displays all registered users, dynamically updating as new users are added. Basic form validation ensures that all required fields are filled before submission.

## Features

* **User Registration:** Allows new users to enter their details through a user-friendly form.
* **Data Persistence:** Stores registration data securely in a MySQL database.
* **Dynamic Table Display:** Shows a live list of all registered users, loaded from the database.
* **Form Validation:** Checks if all necessary fields are completed before allowing submission.
* **Reset Functionality:** Clears all input fields for easy new entries.
* **Exit Application:** Provides a way to gracefully close the application.

## Technologies Used

* **Java Swing:** For building the graphical user interface.
* **JDBC (Java Database Connectivity):** For connecting to and interacting with the MySQL database.
* **MySQL Database:** To store user registration data.
* **`com.toedter.calendar.JDateChooser`:** A third-party Swing component for easy date selection.

## Setup and Installation

To run this application, you'll need to set up a few things:

### 1. Prerequisites

* **Java Development Kit (JDK) 8 or higher:** Ensure Java is installed and configured on your system.
* **MySQL Server:** You need a running MySQL instance.
* **MySQL JDBC Driver:** The connector that allows Java to communicate with MySQL. You can download it from the official MySQL website (look for "MySQL Connector/J").
* **JDateChooser Library:** You'll need the `jcalendar` JAR file. Download it from a reliable source (e.g., Maven Central or its GitHub repository).

### 2. Database Setup

1.  **Create the Database:**
    Open your MySQL client (e.g., MySQL Workbench, command line) and create a database named `registration_db`:

    ```sql
    CREATE DATABASE registration_db;
    USE registration_db;
    ```

2.  **Create the Users Table:**
    Create a table named `users` within the `registration_db` database:

    ```sql
    CREATE TABLE users (
        id INT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        gender VARCHAR(10),
        dob DATE,
        address TEXT,
        contact VARCHAR(20)
    );
    ```

### 3. Project Setup (Using an IDE like NetBeans or IntelliJ IDEA)

1.  **Create a New Java Project:**
    Create a new Java Application project in your IDE.

2.  **Add Libraries:**
    * Right-click on your project in the IDE and go to `Properties` (or `Build Path` in Eclipse).
    * Navigate to `Libraries`.
    * Add the downloaded **MySQL JDBC Driver JAR** file.
    * Add the downloaded **`jcalendar` JAR** file (for `JDateChooser`).

3.  **Place the Source Code:**
    Copy the provided Java source code (`RegistrationForm.java`) into the appropriate package in your project (e.g., `src/main/java/com/yourpackage/RegistrationForm.java`).

### 4. Configure Database Connection

The database connection details are hardcoded in the `getConnection()` method:

```java
public Connection getConnection() {
    Connection con = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "");
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Connection Failed: " + e.getMessage());
    }
    return con;
}
