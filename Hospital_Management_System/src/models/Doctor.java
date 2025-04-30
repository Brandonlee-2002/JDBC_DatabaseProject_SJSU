package models;

public class Doctor {
    private int doctorID;
    private String name;
    private String specialty;
    private String contactInfo;
    private String username;
    private String password;

    public Doctor(int doctorID, String name, String specialty, String contactInfo, String username, String password) {
        this.doctorID = doctorID;
        this.name = name;
        this.specialty = specialty;
        this.contactInfo = contactInfo;
        this.username = username;
        this.password = password;
    }

    public int getDoctorID() { return doctorID; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public String getContactInfo() { return contactInfo; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return name + " (" + specialty + ")";
    }
}
