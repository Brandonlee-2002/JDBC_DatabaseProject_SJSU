package models;

import java.time.LocalDate;

public class Patient {
    private int patientID;
    private String name;
    private LocalDate dob;
    private String gender;
    private String contactInfo;

    public Patient(int patientID, String name, LocalDate dob, String gender, String contactInfo) {
        this.patientID = patientID;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    public Patient(String name, LocalDate dob, String gender, String contactInfo) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

    // Getters and setters
    public int getPatientID() { return patientID; }
    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public String getGender() { return gender; }
    public String getContactInfo() { return contactInfo; }

    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setName(String name) { this.name = name; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setGender(String gender) { this.gender = gender; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}
