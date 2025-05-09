package models;

import java.time.LocalDate;

//Creating Patient class with essential methods
public class Patient {
    private int patientID;
    private String name;
    private LocalDate dob;
    private String gender;
    private String contactInfo;
    private String username;
    private String password;

    public Patient(int patientID, String name, LocalDate dob, String gender, String contactInfo, String username, String password) {
        this.patientID = patientID;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
    }

    public Patient(String name, LocalDate dob, String gender, String contactInfo, String username, String password) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getPatientID() { return patientID; }
    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public String getGender() { return gender; }
    public String getContactInfo() { return contactInfo; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Setters
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setName(String name) { this.name = name; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setGender(String gender) { this.gender = gender; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

}
