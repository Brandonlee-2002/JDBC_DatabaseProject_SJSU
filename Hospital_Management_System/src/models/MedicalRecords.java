package models;

import java.time.LocalDate;

//Creating MedicalRecords class with essential methods
public class MedicalRecords {
    private int recordID;
    private int patientID;
    private String diagnosis;
    private String treatment;
    private LocalDate recordDate;

    public MedicalRecords(int recordID, int patientID, String diagnosis, String treatment, LocalDate recordDate) {
        this.recordID = recordID;
        this.patientID = patientID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.recordDate = recordDate;
    }

    public MedicalRecords(int patientID, String diagnosis, String treatment, LocalDate recordDate) {
        this.patientID = patientID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.recordDate = recordDate;
    }

    // Getters and setters
    public int getRecordID() { return recordID; }
    public int getPatientID() { return patientID; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatment() { return treatment; }
    public LocalDate getRecordDate() { return recordDate; }

    public void setRecordID(int recordID) { this.recordID = recordID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
}
