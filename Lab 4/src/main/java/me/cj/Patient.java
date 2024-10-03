package me.cj;

import java.time.LocalDate;

public class Patient extends Person {

    private static long nextId = 5000;
    private long patientId;
    private String[] history;
    private boolean locked;

    public Patient(Name name, LocalDate bday, long ssn, String[] history) {
        super(name, bday, ssn);

        this.patientId = nextId++;
        this.history = history;
        this.locked = false;
    }

    public Patient(Name name, LocalDate bday, long ssn) {
        super(name, bday, ssn);

        this.patientId = nextId++;
        this.history = new String[0];
        this.locked = false;
    }

    public long getId() {
        return patientId;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getHistory(long last4) {
        if (locked) return "Patient is locked!";

        int attempts = 0;

        while (attempts < 3) {
            if (last4 == getSsn() % 10000) {
                return String.join(", ", history);
            } else {
                attempts++;
            }
        }

        lock();
        return "Access Denied. Patient Locked.";
    }

    public void lock() {
        this.locked = true;
    }

    public boolean addHistory(String newHistory, long last4) {
        if (last4 == getSsn() % 10000) {
            String[] newHistoryArray = new String[history.length + 1];
            System.arraycopy(history, 0, newHistoryArray, 0, history.length);

            newHistoryArray[history.length] = newHistory;

            this.history = newHistoryArray;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + "\n" +
                "Name: " + getName() + "\n" +
                "Birthday: " + getBirthday() + "\n" +
                "History: " + String.join(", ", history) + "\n" +
                "Locked: " + locked;
    }

}