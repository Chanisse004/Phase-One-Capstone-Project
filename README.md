# College Management System

Professional Java application for managing student records, course enrollment, grading, and administrative reports for educational institutions.

## Overview

This project is a console-based Java application that models core university operations: student registration, course management, enrollments, grade tracking, GPA calculation, tuition computation, and report generation (e.g., Dean's list). The project was developed as a lightweight educational management system and stores data in simple text files.

Current status: the original `src/` directory is missing from this workspace. Compiled classes were found under `out/production/College Management System`. Below you'll find instructions to recover source files, a quick-start to run a minimal build, and development notes.

## Features

- Register undergraduate and graduate students
- Create and manage courses (capacity, credits)
- Enroll students with duplicate-enrollment protection
- Assign grades and compute GPAs
- Generate Dean's list (GPA threshold)
- Persist data to text files (students.txt, courses.txt, enrollments.txt)

## System requirements

- Java JDK 8 or newer
- Windows / macOS / Linux
- Minimal disk and memory requirements

## Quick recovery note (missing src)

The project's source folder (`src/`) is currently missing. Compiled classes were detected at `out/production/College Management System`. You have two practical recovery options:

1. Decompile compiled classes (recommended for full recovery):
   - Use a decompiler such as CFR (recommended), JD-CLI, or JADX to create Java source files from the `.class` files under `out/production/College Management System`.
   - Example (CFR):
     1. Download `cfr.jar` from https://www.benf.org/other/cfr/
     2. From the project root:
        - `java -jar cfr.jar --outputdir recovered\src "out\production\College Management System"`
   - Inspect and commit the recovered sources into `src/`.

2. Recreate a skeleton `src` tree manually:
   - Create package folders and reimplement source code by hand (slower, but safe).
   - Use IntelliJ Local History or VCS (git) to restore deleted files if available.

Important: Back up the `out/` directory before writing recovered sources.

## Quick-start (minimal skeleton)

A minimal `src/` skeleton is provided to allow building and running a smoke test. To compile the minimal skeleton (created for you automatically):

1. From the project root (PowerShell):

```powershell
# create output directory and compile all Java files under the repo
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
New-Item -ItemType Directory -Path out\rebuild -Force
javac -d out\rebuild $files
```

2. Run the minimal app (example):

```powershell
java -cp out\rebuild Main
```

This will run a small placeholder main that confirms the project structure is valid. The skeleton is intentionally minimal and does not include full business logic.

## Recommended recovery workflow

1. Backup the repository and `out/production`.
2. Attempt automated decompilation (CFR) into `recovered/src`.
3. Review, fix, and test decompiled sources.
4. Commit recovered sources to version control.

## Development notes

- The original project structure (expected):

```
College Management System/
  src/
    Main.java
    model/
    service/
    exception/
  students.txt
  courses.txt
  enrollments.txt
```

- The application uses plain text files for persistence (`students.txt`, `courses.txt`, `enrollments.txt`).
- Decompilation can produce syntactically correct source but may lose comments, original variable names, and sometimes needs manual fixes (especially around generics, lambdas, inner classes).

## Next steps I can take for you

- Run an automated decompilation of `out/production/College Management System` into `recovered/src` using CFR and place recovered files into `src/` after your confirmation.
- Or keep the minimal skeleton and incrementally reimplement functionality.

If you want me to proceed with automated decompilation now, tell me which approach you prefer: CFR (recommended) or JD-CLI. If you prefer manual restore via IntelliJ Local History or git, I can walk you through those steps.

---

(README updated automatically by an assistant to include recovery guidance and a minimal build path.)
