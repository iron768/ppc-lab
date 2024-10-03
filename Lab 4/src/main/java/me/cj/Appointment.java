package me.cj;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private LocalDate date;
    private LocalTime time;
    private long patientId;
    private long doctorId;

    public Appointment(LocalDate date, LocalTime time, long patientId, long doctorId) {
        this.date = date;
        this.time = time;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Appointment Date: " + date + "\n" +
                "Appointment Time: " + time + "\n" +
                "Patient ID: " + patientId + "\n" +
                "Doctor ID: " + doctorId;
    }

}