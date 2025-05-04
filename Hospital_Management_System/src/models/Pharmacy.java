package models;

import java.time.LocalDate;

public class Pharmacy {
    private int medicineID;
    private String name;
    private int stock;
    private double price;
    private LocalDate expiryDate;

    public Pharmacy(int medicineID, String name, int stock, double price, LocalDate expiryDate) {
        this.medicineID = medicineID;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    public Pharmacy(String name, int stock, double price, LocalDate expiryDate) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public int getMedicineID() { return medicineID; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }
    public LocalDate getExpiryDate() { return expiryDate; }

    public void setMedicineID(int medicineID) { this.medicineID = medicineID; }
    public void setName(String name) { this.name = name; }
    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}
