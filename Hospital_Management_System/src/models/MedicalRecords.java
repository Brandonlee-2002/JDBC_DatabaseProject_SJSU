package models;

import java.time.LocalDate;

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

    // Getters
    public int getRecordID() {
        return recordID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    // Setters (if needed)
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}
