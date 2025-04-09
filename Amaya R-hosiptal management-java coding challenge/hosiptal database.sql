CREATE DATABASE Hospital;

use hospital;

-- Create the Patient table
CREATE TABLE Patient (
    patientId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender VARCHAR(10),
    contactNumber VARCHAR(15),
    address VARCHAR(255)
);

-- Create the Doctor table
CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    specialization VARCHAR(100),
    contactNumber VARCHAR(15)
);

-- Create the Appointment table
CREATE TABLE Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    description VARCHAR(255),
    FOREIGN KEY (patientId) REFERENCES Patient(patientId),
    FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId)
);

INSERT INTO Patient VALUES
(1, 'John', 'Doe', '1985-04-12', 'Male', '1234567890', '123 Elm St'),
(2, 'Jane', 'Smith', '1990-08-23', 'Female', '2345678901', '456 Oak St'),
(3, 'Alice', 'Johnson', '1982-12-11', 'Female', '3456789012', '789 Pine St'),
(4, 'Bob', 'Brown', '1978-07-04', 'Male', '4567890123', '321 Maple Ave'),
(5, 'Carol', 'Davis', '1995-03-17', 'Female', '5678901234', '654 Birch Rd'),
(6, 'Dan', 'Miller', '1987-10-29', 'Male', '6789012345', '987 Cedar Blvd'),
(7, 'Eve', 'Wilson', '1992-06-21', 'Female', '7890123456', '741 Spruce Ln'),
(8, 'Frank', 'Moore', '1983-09-10', 'Male', '8901234567', '852 Redwood Dr'),
(9, 'Grace', 'Taylor', '1989-02-05', 'Female', '9012345678', '963 Willow Ct'),
(10, 'Henry', 'Anderson', '1975-01-15', 'Male', '0123456789', '159 Ash Way');

select * from Patient;

INSERT INTO Doctor VALUES
(1, 'Sarah', 'Lee', 'Cardiologist', '1231231234'),
(2, 'Mike', 'Chen', 'Dermatologist', '2342342345'),
(3, 'Anna', 'White', 'Neurologist', '3453453456'),
(4, 'James', 'Wong', 'Pediatrician', '4564564567'),
(5, 'Emily', 'Clark', 'Oncologist', '5675675678'),
(6, 'David', 'Lewis', 'Orthopedic', '6786786789'),
(7, 'Linda', 'King', 'Psychiatrist', '7897897890'),
(8, 'Tom', 'Scott', 'ENT Specialist', '8908908901'),
(9, 'Nina', 'Hill', 'General Surgeon', '9019019012'),
(10, 'Rick', 'Hall', 'Gastroenterologist', '0120120123');

select * from Doctor;

INSERT INTO Appointment VALUES
(1, 1, 3, '2025-04-10', 'Routine check-up'),
(2, 2, 5, '2025-04-11', 'Cancer follow-up'),
(3, 3, 1, '2025-04-12', 'Heart health consultation'),
(4, 4, 4, '2025-04-13', 'Child health review'),
(5, 5, 6, '2025-04-14', 'Knee pain'),
(6, 6, 2, '2025-04-15', 'Skin rash'),
(7, 7, 7, '2025-04-16', 'Mental health therapy'),
(8, 8, 9, '2025-04-17', 'Surgery review'),
(9, 9, 8, '2025-04-18', 'Ear infection'),
(10, 10, 10, '2025-04-19', 'Stomach issues');

select * from Appointment;



