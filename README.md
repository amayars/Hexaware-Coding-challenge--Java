# Hexaware-Coding-challenge--Java

# 🏥 Hospital Management System

A simple Java-based Hospital Management System using JDBC and MySQL. It follows a multi-layered architecture with DAO, service, utility, and main modules. This system helps manage appointments for doctors and patients efficiently.

# 🚀 Features
# 📚 Service Layer (IHospitalService)
-> getAppointmentById(int appointmentId): Returns an Appointment.

-> getAppointmentsForPatient(int patientId): Returns a list of Appointments.

-> getAppointmentsForDoctor(int doctorId): Returns a list of Appointments.

-> scheduleAppointment(Appointment appointment): Schedules a new appointment.

-> updateAppointment(Appointment appointment): Updates an existing appointment.

-> cancelAppointment(int appointmentId): Cancels the appointment.

# 🛠️ DAO Layer (HospitalServiceImpl)
-> Implements the service interface and communicates with the database using JDBC.

# 🔧 Utilities
-> CoonectionHelper: Manages the database connection.

-> Resources : Reads DB connection details from a .properties file.

# ❗ Custom Exceptions
-> PatientNumberNotFoundException: Thrown when an invalid patient number is used.

# 🧪 Main Module
-> MainModule.java: Entry point of the application. Triggers all service methods and handles exceptions

# 💡 Technologies Used
Java (JDK 8+)
JDBC
MySQL
Maven 
Lombok (for model classes)

# 🧑‍💻 Author
Amaya R
