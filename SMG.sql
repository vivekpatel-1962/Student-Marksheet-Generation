CREATE DATABASE IF NOT EXISTS student_marks_db;
USE student_marks_db;

-- Create student table
CREATE TABLE IF NOT EXISTS student (
    rollno INT PRIMARY KEY,
    fname VARCHAR(50) NOT NULL,
    lname VARCHAR(50) NOT NULL,
    standard INT NOT NULL,
    section VARCHAR(10) NOT NULL
);

-- Create marks table
CREATE TABLE IF NOT EXISTS marks (
    marksid INT AUTO_INCREMENT PRIMARY KEY,
    rollno INT NOT NULL,
    physics DOUBLE,
    chemistry DOUBLE,
    maths DOUBLE,
    english DOUBLE,
    computer DOUBLE,
    FOREIGN KEY (rollno) REFERENCES student(rollno) ON DELETE CASCADE ON UPDATE CASCADE
);
