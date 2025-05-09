package DataAccessObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Pharmacy;

public class PharmacyDAO {
    
    public List<Pharmacy> getAllMedicines() {
        List<Pharmacy> medicines = new ArrayList<>();
        String sql = "SELECT * FROM Pharmacy";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) { //Printing all medicines
                medicines.add(new Pharmacy(
                    rs.getInt("MedicineID"),
                    rs.getString("Name"),
                    rs.getInt("Stock"),
                    rs.getDouble("Price"),
                    rs.getDate("ExpiryDate").toLocalDate()
                ));
            }
        } catch (SQLException e) { //Error handling
            e.printStackTrace();
        }

        return medicines;
    }

    public void updateStock(int medicineId, int newStock) {
        String sql = "UPDATE Pharmacy SET Stock = ? WHERE MedicineID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Updating stock of associated medicineID
            stmt.setInt(1, newStock); 
            stmt.setInt(2, medicineId);
            stmt.executeUpdate();
            System.out.println("Medicine stock updated successfully.");
        } catch (SQLException e) { //Error handling
            e.printStackTrace(); 
        }
    }

    public Pharmacy getMedicineById(int medicineId) {
        String sql = "SELECT * FROM Pharmacy WHERE MedicineID = ?";
        Pharmacy medicine = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, medicineId);
            ResultSet rs = stmt.executeQuery();
            //Selecting medicine by ID
            if (rs.next()) {
                medicine = new Pharmacy(
                    rs.getInt("MedicineID"),
                    rs.getString("Name"),
                    rs.getInt("Stock"),
                    rs.getDouble("Price"),
                    rs.getDate("ExpiryDate").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicine;
    }
} 