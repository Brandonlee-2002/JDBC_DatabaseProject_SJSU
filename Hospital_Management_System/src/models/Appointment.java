package models;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private LocalDateTime appointmentDate;
    private String reason;

    public Appointment(int appointmentID, int patientID, int doctorID, LocalDateTime appointmentDate, String reason) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
    }

    // Getters
    public int getAppointmentID() { return appointmentID; }
    public int getPatientID() { return patientID; }
    public int getDoctorID() { return doctorID; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public String getReason() { return reason; }
}
