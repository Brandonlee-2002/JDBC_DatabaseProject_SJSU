package DataAccessObjects;

import models.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // Login doctor using username and password
    public Doctor loginDoctor(String username, String password) {
        String sql = "SELECT * FROM Doctors WHERE Username = ? AND Password = ?";
        Doctor doctor = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor(
                    rs.getInt("DoctorID"),
                    rs.getString("Name"),
                    rs.getString("Specialty"),
                    rs.getString("ContactInfo"),
                    rs.getString("Username"),
                    rs.getString("Password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    // ✅ NEW: Get all doctors from the database
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor doctor = new Doctor(
                    rs.getInt("DoctorID"),
                    rs.getString("Name"),
                    rs.getString("Specialty"),
                    rs.getString("ContactInfo"),
                    rs.getString("Username"),
                    rs.getString("Password")
                );
                doctors.add(doctor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }
}
