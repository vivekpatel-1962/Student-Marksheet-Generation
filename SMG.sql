CREATE DATABASE IF NOT EXISTS student_marks_db;
USE student_marks_db;

-- Create student table
CREATE TABLE student (
 rollno int(11) NOT NULL AUTO_INCREMENT,
 firstname varchar(20) NOT NULL,
 lastname varchar(10) NOT NULL,
 standard int(11) NOT NULL,
 section varchar(10) NOT NULL,
 PRIMARY KEY (rollno)
)


    
-- Create marks table
CREATE TABLE marks (
 marksid int(11) NOT NULL AUTO_INCREMENT,
 physics double NOT NULL,
 chemistry double NOT NULL,
 maths double NOT NULL,
 english double NOT NULL,
 computer double NOT NULL,
 rollno int(11) NOT NULL,
 PRIMARY KEY (marksid),
 KEY roll_fkey (rollno),
 CONSTRAINT roll_fkey FOREIGN KEY (rollno) REFERENCES student (rollno)
)


-- trigger
DELIMITER //

CREATE TRIGGER before_student_delete
BEFORE DELETE ON student
FOR EACH ROW
BEGIN
    DELETE FROM marks WHERE rollno = OLD.rollno;
END; //

DELIMITER ;
