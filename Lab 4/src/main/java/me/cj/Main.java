package me.cj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        String url = DatabaseInfo.URL;
        String user = DatabaseInfo.USER;
        String password = DatabaseInfo.PASSWORD;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver successfully loaded.");

            try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {

                System.out.println("Connected to MySQL database");

                String createDatabase = "CREATE DATABASE IF NOT EXISTS medical_db";

                System.out.println("Attempting to create database with query: " + createDatabase);

                int result = statement.executeUpdate(createDatabase);

                System.out.println("Database result: " + result);

                String dbUrl = DatabaseInfo.getMedicalUrl();

                try (Connection databaseConnection = DriverManager.getConnection(dbUrl, user, password);
                    Statement databaseConnectionStatement = databaseConnection.createStatement()) {

                    System.out.println("Connected to 'medical_db'");

                    String createDoctorTable = "CREATE TABLE IF NOT EXISTS Doctor (" +
                            "doctorId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                            "firstName VARCHAR(50), " +
                            "lastName VARCHAR(50), " +
                            "email VARCHAR(100), " +
                            "specialties TEXT, " +
                            "salary DECIMAL(10, 2));";

                    databaseConnectionStatement.execute(createDoctorTable);

                    System.out.println("Doctor table created");

                    String createPatientTable = "CREATE TABLE IF NOT EXISTS Patient (" +
                            "patientId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                            "firstName VARCHAR(50), " +
                            "lastName VARCHAR(50), " +
                            "birthday DATE, " +
                            "ssn BIGINT, " +
                            "history TEXT, " +
                            "locked BOOLEAN DEFAULT false);";

                    databaseConnectionStatement.execute(createPatientTable);

                    System.out.println("Patient table created");

                    String createAppointmentTable = "CREATE TABLE IF NOT EXISTS Appointment (" +
                            "appointmentId BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                            "appointmentDate DATE, " +
                            "appointmentTime TIME, " +
                            "patientId BIGINT, " +
                            "doctorId BIGINT, " +
                            "FOREIGN KEY (patientId) REFERENCES Patient(patientId), " +
                            "FOREIGN KEY (doctorId) REFERENCES Doctor(doctorId));";
                    databaseConnectionStatement.execute(createAppointmentTable);

                    System.out.println("Appointment table created");

                    String insertDoctor = "INSERT INTO Doctor (firstName, lastName, email, specialties, salary) " +
                            "VALUES ('Sam', 'Doc', 'sdoc@med.com', 'Oncology, Surgery', 275000.00);";

                    databaseConnectionStatement.execute(insertDoctor);

                    System.out.println("Added doctor");

                    String insertPatient = "INSERT INTO Patient (firstName, lastName, birthday, ssn, history, locked) " +
                            "VALUES ('Jane', 'Smith', '1999-07-12', 123456789, 'Lupus', false);";

                    databaseConnectionStatement.execute(insertPatient);

                    System.out.println("Added patient");

                    String insertAppointment = "INSERT INTO Appointment (appointmentDate, appointmentTime, patientId, doctorId) " +
                            "VALUES ('2024-10-02', '12:30:00', 1, 1);";

                    databaseConnectionStatement.execute(insertAppointment);

                    System.out.println("Added appointment");
                } catch (SQLException e) {
                    System.out.println("Failed to connect to 'medical_db'");
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                System.out.println("Failed to connect to MySQL");
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found");
            e.printStackTrace();
        }
    }

}