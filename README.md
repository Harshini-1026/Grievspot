# GrievSpot - Grievance Management System

This is a Java console application designed to manage and streamline the grievance redressal process within a social environment. It allows users (Complainants) to log in, submit grievances, and view their submitted issues. Admin-side features can be extended as needed. Grievances are stored persistently and notifications are triggered upon submission.

This project serves as a practical implementation for understanding file handling, object-oriented programming, and basic user authentication in Java. Modify and build upon it to fit more complex needs.

---

## Getting Started

To run this program, ensure Java is installed on your system. Download or clone this repository and navigate to the directory containing the `.java` files. Then, compile and run the program with:

```bash
javac Main.java
java Main
```

Make sure all related `.java` files are in the same folder as `Main.java`.

---

## Usage

Upon running the application, the system will prompt you for login credentials. Once logged in, a menu is displayed with options:

- **Login:** Enter your username and password to access the system.
- **Submit Grievance:** Provide department, category, and description to lodge a grievance.
- **View My Grievances:** View all grievances you’ve submitted, along with their current status and assigned officer.
- **Logout:** Exit your session.
- **Exit:** Terminate the program.

---

## Features

- Persistent storage of grievances via `grievances.txt`
- Basic user authentication
- Menu-driven interaction
- Notification system for submission updates
- Data serialization via custom CSV formatting
- Simple object-oriented design with role-based access

---
