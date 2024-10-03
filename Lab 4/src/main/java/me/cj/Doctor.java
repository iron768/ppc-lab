package me.cj;

public class Doctor extends Medical {

    private static long id = 1000;
    private final long doctorId;
    private String[] specialty;
    private String email;

    public Doctor(Name name, String[] specialty, double salary) {
        super(name, salary);

        doctorId = id++;

        this.specialty = specialty;
        this.email = name.toString().toLowerCase() + "@hospital.com";
    }

    public long getId() {
        return doctorId;
    }

    public String[] getSpecialty() {
        return specialty;
    }

    public String getEmail() {
        return email;
    }

    public void addSpecialty(String s) {
        String[] newSpecialty = new String[specialty.length + 1];
        System.arraycopy(specialty, 0, newSpecialty, 0, specialty.length);

        newSpecialty[specialty.length] = s;
        specialty = newSpecialty;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + doctorId + "\n" +
                "Name: " + getName() + "\n" +
                "Specialty: " + String.join(", ", specialty) + "\n" +
                "Email: " + email + "\n" +
                "Salary: " + getSalary();
    }

}
