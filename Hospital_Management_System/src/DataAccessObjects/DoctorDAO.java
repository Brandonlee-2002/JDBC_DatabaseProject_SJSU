package DataAccessObjects;

import models.Doctor;
import java.sql.*;

public class DoctorDAO {

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
}
