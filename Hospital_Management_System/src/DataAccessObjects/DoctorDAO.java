package DataAccessObjects;

import models.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // Add a new doctor
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors (Name, Specialty, ContactInfo, Schedule) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setString(3, doctor.getContactInfo());
            stmt.setString(4, doctor.getSchedule());

            stmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor d = new Doctor(
                    rs.getInt("DoctorID"),
                    rs.getString("Name"),
                    rs.getString("Specialty"),
                    rs.getString("ContactInfo"),
                    rs.getString("Schedule")
                );
                doctors.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    // Get doctor by ID
    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM Doctors WHERE DoctorID = ?";
        Doctor doctor = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                doctor = new Doctor(
                    rs.getInt("DoctorID"),
                    rs.getString("Name"),
                    rs.getString("Specialty"),
                    rs.getString("ContactInfo"),
                    rs.getString("Schedule")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    // Update doctor
    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE Doctors SET Name = ?, Specialty = ?, ContactInfo = ?, Schedule = ? WHERE DoctorID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialty());
            stmt.setString(3, doctor.getContactInfo());
            stmt.setString(4, doctor.getSchedule());
            stmt.setInt(5, doctor.getDoctorID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Doctor updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete doctor
    public void deleteDoctor(int doctorID) {
        String sql = "DELETE FROM Doctors WHERE DoctorID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorID);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Doctor deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
