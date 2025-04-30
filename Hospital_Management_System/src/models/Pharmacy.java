package models;

import java.time.LocalDate;

public class Pharmacy {
	private int medicineID;
	private String name;
	private int stock;
	private float price;
	private LocalDate expiryDate;
	
	public Pharmacy(int medicineID, String name, int stock, float price, LocalDate expiryDate) {
		this.medicineID = medicineID;
		this.name = name;
		this.stock = stock;
		this.price = price;
		this.expiryDate = expiryDate;
	}
	
	//getters and setters
	
	public int getMedicineID() { return medicineID; }
	public String getName() { return name; }
	public int getStock() { return stock; }
	public float getPrice() { return price; }
	public LocalDate getExpiryDate() { return expiryDate; }
	
	public void setMedicineID(int medicineID) { this.medicineID = medicineID; }
	public void setName(String name) { this.name = name; }
	public void setStock(int stock) { this.stock = stock; }
	public void setPrice(float price ) {this.price = price; }
	public void setExpiryDate(LocalDate expiryDate) {this.expiryDate = expiryDate; }
	

}
