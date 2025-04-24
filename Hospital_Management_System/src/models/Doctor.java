package models;

public class Doctor {
    private int doctorID;
    private String name;
    private String specialty;
    private String contactInfo;
    private String schedule;

    public Doctor(int doctorID, String name, String specialty, String contactInfo, String schedule) {
        this.doctorID = doctorID;
        this.name = name;
        this.specialty = specialty;
        this.contactInfo = contactInfo;
        this.schedule = schedule;
    }

    public Doctor(String name, String specialty, String contactInfo, String schedule) {
        this.name = name;
        this.specialty = specialty;
        this.contactInfo = contactInfo;
        this.schedule = schedule;
    }

    // Getters and setters
    public int getDoctorID() { return doctorID; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public String getContactInfo() { return contactInfo; }
    public String getSchedule() { return schedule; }

    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }
    public void setName(String name) { this.name = name; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
}
