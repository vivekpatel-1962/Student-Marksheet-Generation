-- Create the database
CREATE DATABASE IF NOT EXISTS student_marksheet;
USE student_marksheet;

-- Create the students table
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    roll_number VARCHAR(20) NOT NULL UNIQUE,
    english_marks INT CHECK (english_marks BETWEEN 0 AND 100),
    math_marks INT CHECK (math_marks BETWEEN 0 AND 100),
    science_marks INT CHECK (science_marks BETWEEN 0 AND 100),
    total_marks INT,
    grade VARCHAR(2),
    result VARCHAR(10)
);




