-- Inferred from the application's INSERT, UPDATE, and SELECT statements.
-- Existing databases and tables are preserved.
CREATE DATABASE IF NOT EXISTS student_management_system
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_management_system;

CREATE TABLE IF NOT EXISTS users (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'User'
);

CREATE TABLE IF NOT EXISTS students (
    registration_number VARCHAR(100) NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    gender VARCHAR(30) NOT NULL,
    course VARCHAR(255) NOT NULL,
    year_level VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    feebalance DECIMAL(12,2) NOT NULL DEFAULT 0,
    status VARCHAR(30) NOT NULL DEFAULT 'Active',
    photo TEXT NULL
);
