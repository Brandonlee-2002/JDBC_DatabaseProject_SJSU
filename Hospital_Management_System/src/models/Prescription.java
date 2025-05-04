package models;

import java.time.LocalDate;

public class Prescription {
    private int prescriptionID;
    private int patientID;
    private int doctorID;
    private int medicineID;
    private String dosage;
    private String frequency;
    private String duration;
    private LocalDate prescriptionDate;
    private String status;

    public Prescription(int prescriptionID, int patientID, int doctorID, int medicineID, 
                       String dosage, String frequency, String duration, 
                       LocalDate prescriptionDate, String status) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicineID = medicineID;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.prescriptionDate = prescriptionDate;
        this.status = status;
    }

    public Prescription(int patientID, int doctorID, int medicineID, 
                       String dosage, String frequency, String duration, 
                       LocalDate prescriptionDate, String status) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicineID = medicineID;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.prescriptionDate = prescriptionDate;
        this.status = status;
    }

    // Getters
    public int getPrescriptionID() { return prescriptionID; }
    public int getPatientID() { return patientID; }
    public int getDoctorID() { return doctorID; }
    public int getMedicineID() { return medicineID; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public String getDuration() { return duration; }
    public LocalDate getPrescriptionDate() { return prescriptionDate; }
    public String getStatus() { return status; }

    // Setters
    public void setPrescriptionID(int prescriptionID) { this.prescriptionID = prescriptionID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setDoctorID(int doctorID) { this.doctorID = doctorID; }
    public void setMedicineID(int medicineID) { this.medicineID = medicineID; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setPrescriptionDate(LocalDate prescriptionDate) { this.prescriptionDate = prescriptionDate; }
    public void setStatus(String status) { this.status = status; }
} 