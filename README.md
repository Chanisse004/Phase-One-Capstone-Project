# College Management System (Phase One Capstone Project)

console-based Java application to manage students, courses, enrollments and grades. This project provides university management features such as registering students, creating courses, enrolling students, assigning grades, computing GPA/tuition, and generating the Dean's list.

## Key features

- Register undergraduate and graduate students (`UndergraduateStudent`, `GraduateStudent`).
- Create courses and manage course capacities.
- Enroll students in courses with capacity and duplicate-enrollment checks.
- Assign grades and compute GPAs.
- Calculate tuition (per-student logic in `Student` subclasses).
- Persist data to text files and load them on startup (`students.txt`, `courses.txt`, `enrollments.txt`).
- Console menu with a test-data loader (menu option `9`) to seed example data.

## Project structure (important files)

- `src/Main.java` — console UI and program entry point.
- `src/service/UniversityManager.java` — core application logic (student/course management).
- `src/service/FileManager.java` — load/save data from/to text files (`students.txt`, `courses.txt`, `enrollments.txt`).
- `src/model/` — `Student`, `UndergraduateStudent`, `GraduateStudent`, `Course`, `Instructor`, `Person` classes.
- `src/exception/` — custom exceptions such as `CourseFullException`, `StudentAlreadyEnrolledException`.
- `students.txt`, `courses.txt`, `enrollments.txt` — data files used by the app (created/updated by `FileManager`).


src/
 ├── model/
 │     ├── Person.java
 │     ├── Student.java
 │     ├── Instructor.java
 │     └── Course.java
 │
 ├── service/
 │     ├── UniversityManager.java
 │     └── FileManager.java
 │
 ├── exception/
 │     └── EnrollmentException.java
 │
 └── Main.java


## Prerequisites

- Java JDK 8 or later installed and `javac`/`java` available on PATH.
- (Optional) An IDE such as IntelliJ IDEA for easier editing and running.


  ## Key Features

- Add students
- Add courses
- Enroll students into courses
- Prevent duplicate enrollments
- Handle errors using custom exceptions
- Store and manage data using Collections


## Usage (console menu)

When you run the app it shows a menu with the following options:

- `1` — Register Student: provide ID, name, email, department, and type (1=Undergrad, 2=Grad).
- `2` — Enroll in Course: enter student ID and course code; after enrollment you can enter a grade.
- `3` — View Student Record: shows profile, GPA, tuition, and enrolled courses with grades.
- `4` — Generate Dean's List: lists students with GPA > 3.5.
- `5` — Save and Exit: writes data to the text files and exits.
- `9` — Add Test Data: populates example students, courses, enrollments, and grades for quick testing.



## Data files

- `students.txt`, `courses.txt`, `enrollments.txt` are used by `FileManager` to persist state.
- The files are located in the same folder where you run the program (project root / inner project folder). The application will create them if they do not already exist.



## Author

Umuhoza Chanisse

Software Engineering Student

