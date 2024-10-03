package me.cj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalTime;

public class Test {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Emily", "Chris", "Katie", "David", "Laura", "James", "Sarah"};

    private static final String[] LAST_NAMES = {"Smith", "Doe", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis", "Rodriguez"};

    private static final String[] SPECIALTIES = {"Cardiology", "Dermatology", "Neurology", "Pediatrics", "Surgery", "Psychiatry", "Radiology", "Ophthalmology", "Orthopedics", "Anesthesiology"};

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        if (!isDatabaseAvailable()) {
            System.out.println("Database not found. Running MedicalDatabase to create it...");

            String[] input = {};

            Main.main(input);
        }

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Name name = new Name(getRandomFirstName(), getRandomLastName());
            long ssn = generateRandomSSN();
            LocalDate birthday = generateRandomLocalDate();

            Person person = new Person(name, birthday, ssn);

            people.add(person);
        }

        for (Person person : people) {
            System.out.println(person);
        }

        List<Doctor> doctors = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Name name = new Name(getRandomFirstName(), getRandomLastName());
            String[] specialties = getRandomSpecialties();
            double salary = generateRandomSalary();

            Doctor doctor = new Doctor(name, specialties, salary);

            doctors.add(doctor);
        }

        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }

        List<Patient> patients = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Name name = new Name(getRandomFirstName(), getRandomLastName());
            long ssn = generateRandomSSN();
            LocalDate birthday = generateRandomLocalDate();

            Patient patient = new Patient(name, birthday, ssn, new String[0]);

            patients.add(patient);
        }

        for (Patient patient : patients) {
            System.out.println(patient);
        }

        List<Appointment> appointments = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            LocalDate date = generateRandomLocalDate();
            LocalTime time = generateRandomTime();
            long patientId = 5000 + RANDOM.nextInt(100);
            long doctorId = 1000 + RANDOM.nextInt(100);

            Appointment appointment = new Appointment(date, time, patientId, doctorId);

            appointments.add(appointment);
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private static boolean isDatabaseAvailable() {
        String url = DatabaseInfo.getMedicalUrl();
        String user = DatabaseInfo.USER;
        String password = DatabaseInfo.PASSWORD;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            return true;
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
            return false;
        }
    }

    private static String[] getRandomSpecialties() {
        int numSpecialties = RANDOM.nextInt(3) + 1;
        String[] selectedSpecialties = new String[numSpecialties];

        for (int i = 0; i < numSpecialties; i++) {
            String specialty;
            do {
                specialty = SPECIALTIES[RANDOM.nextInt(SPECIALTIES.length)];
            } while (contains(selectedSpecialties, specialty));

            selectedSpecialties[i] = specialty;
        }

        return selectedSpecialties;
    }

    private static boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s != null && s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private static double generateRandomSalary() {
        return 80000 + RANDOM.nextDouble() * 20000;
    }

    private static String getRandomFirstName() {
        return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
    }

    private static String getRandomLastName() {
        return LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
    }

    private static long generateRandomSSN() {
        return 100000000L + (long) (RANDOM.nextDouble() * 900000000L);
    }

    private static LocalDate generateRandomLocalDate() {
        int year = RANDOM.nextInt(100) + 1920;
        int month = RANDOM.nextInt(12) + 1;
        int day = RANDOM.nextInt(28) + 1;

        return LocalDate.of(year, month, day);
    }

    private static LocalTime generateRandomTime() {
        int hour = RANDOM.nextInt(24);
        int minute = RANDOM.nextInt(60);

        return LocalTime.of(hour, minute);
    }


}
