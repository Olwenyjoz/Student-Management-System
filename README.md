# Student Management System

Java Swing desktop application with MySQL storage and PDF reporting.

## Requirements

- JDK 26 (Java compiler and runtime on PATH)
- MySQL Server; the Windows scripts currently target MySQL 9.6, service `MySQL96`, port 3306
- Apache NetBeans with Java SE support, or Windows PowerShell for the scripts

The `lib` folder contains MySQL Connector/J 9.7.0 and iText 5.5.13.3.

## Windows setup

1. Start MySQL. For the installed Windows service, use an Administrator PowerShell window:
   ```powershell
   Start-Service MySQL96
   ```
2. Open PowerShell in this project folder and run:
   ```powershell
   powershell -ExecutionPolicy Bypass -File .\setup-database.ps1
   ```
   Enter your MySQL administrator username (Enter selects `root`) and password.
   The script creates missing tables and a dedicated application database account.
   Local credentials are saved to `database.properties`, which Git ignores.
3. Start the application:
   ```powershell
   powershell -ExecutionPolicy Bypass -File .\run.ps1
   ```

For a different MySQL installation, adapt the script paths and connection settings.
You can also execute `database.sql` yourself and copy `database.properties.example`
to `database.properties`, supplying an account with access to the database.

## Apache NetBeans

Open this folder as an existing project. The main class is
`studentmanagementsystem.Login`. Run the project with F6 after database setup.
All application windows support resizing, maximizing, and restoring.

## First application account

Application accounts are separate from MySQL administrator accounts. On a new
database, create your first account by running the `SignUp.java` file in NetBeans
(Shift+F6). Select `Admin` to manage application users, then use the new credentials
on the login screen.

## Forgotten MySQL root password

The optional `reset-mysql-password.ps1` script uses MySQL's init-file method.
Run it in Administrator PowerShell and choose a new password locally. It temporarily
stops MySQL96, resets `root@localhost`, and verifies login after restarting the service.
This changes the administrator password for other tools using that account as well.

## Local use

The original application stores account passwords as plain text and allows role
selection during sign-up. Use sample data locally; authentication needs hardening
before shared or production use. Dependency licenses apply to the bundled JARs.
