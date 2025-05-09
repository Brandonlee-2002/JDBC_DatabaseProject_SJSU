package models;

import java.time.LocalDate;

//Creating Billing class with essential methods
public class Billing {
    private int billID;
    private int patientID;
    private double totalAmount;
    private String paymentStatus;
    private LocalDate billingDate;

    public Billing(int billID, int patientID, double totalAmount, String paymentStatus, LocalDate billingDate) {
        this.billID = billID;
        this.patientID = patientID;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.billingDate = billingDate;
    }

    public Billing(int patientID, double totalAmount, String paymentStatus, LocalDate billingDate) {
        this.patientID = patientID;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.billingDate = billingDate;
    }

    // Getters and setters
    public int getBillID() { return billID; }
    public int getPatientID() { return patientID; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public LocalDate getBillingDate() { return billingDate; }

    public void setBillID(int billID) { this.billID = billID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public void setBillingDate(LocalDate billingDate) { this.billingDate = billingDate; }
}
