Student Registration System – Java Swing GUI 
 
Project Overview 
 
This is a desktop-based Student Registration System developed using Java and the Swing GUI framework. It enables users to register students under three educational paths: 
 
- Bachelor (e.g., ITS, Software Engineering) 
- Master 
- Extension Programs  
 
The system supports Sign Up/Login functionality and stores all data in an SQLite database. 
 
This project demonstrates strong Object-Oriented Programming (OOP) design, separating functionality across meaningful packages and utilizing Java’s GUI and JDBC technologies. 
 
Setup and Execution Instructions 
 
Prerequisites: 
- Java JDK 8 or higher 
- SQLite JDBC Driver (added to your classpath) 
- Java-supported IDE (VS Code, NetBeans, IntelliJ, etc.) 
 
How to Run: 
 
1. Clone or download the project. 
2. Open it in your IDE. 
3. Ensure the SQLite JDBC `.jar` is in the `lib/` directory and correctly referenced in your class path. 
4. Run the entry point (`main/Main.java`) or launch via `ui/HomePage.java`. 
 
On first launch: 
- The system checks for and creates the database (`students.db`) if missing. 
- Tables are auto-generated if they do not exist. 
 
Project Structure: 
 
Student_Registration_system/ 
│ 
├── assets/ # Includes static resources like logo images 
├── dao/ # Handles database logic (CRUD, connection, data insertions) 
├── model/ # Abstract class (Student) and its subclasses (Bachelor, Master, Extension) 
├── ui/ # Java Swing-based forms (Registration, Login, Sign Up, Home Page) 
├── util/ # Helpers (File Logger, LoggerUtil) 
├── lib/ # SQLite JDBC JAR  
├── Main/ # Application entry point (Main.java) 
├── database/ # Stores the students.db SQLite database 
└── README.md # This file 
 
Key Java Concepts Used: 
 
- Encapsulation (private fields, getters/setters) 
- Inheritance (subclasses like BachelorStudent extending Student) 
- Abstraction (abstract class Student defining required methods) 
- Polymorphism (`instanceof` usage in DAO) 
- Java Swing GUI (JFrame, JPanel, layout managers) 
- JDBC and SQLite (database persistence) 
- Event handling (responding to user actions) 
- File logging (recording activity logs) 
- Input validation (checking user data before saving) 
- Modular design (code organized in packages) 
 
Team Contributions 
 
- Debora Dereje – Implemented the Master's Registration Form and associated logic. 
- Hildana Birhanu – Developed the Extension Program Registration Form layout and logic. 
- Hoise Dereje – Created the Bachelor's Registration Form, including its database integration. 
- Yeabsira Addis– Designed the Login and Signup System with input validation and session control. 
 
> This project applied core Java concepts in a real-world-style application using GUI and database integration in a team setting. 
 
 
Project Requirements Checklist: 
 
✔ Abstract class for shared behavior (`Student`)  
✔ At least 3 subclasses for different student types  
✔ Use of polymorphism (`instanceof` in DAO)  
✔ GUI interfaces using Java Swing  
✔ SQLite database for storing records  
✔ Proper package structure (model, dao, ui, util, main, lib, assets)  
✔ File logging & validation where applicable  
✔ Team collaboration with distinct contributions  
 
Contact: 
 
For questions, improvements, or collaboration ideas, please reach out to the development team. 
