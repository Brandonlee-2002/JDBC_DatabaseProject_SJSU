package DataAccessObjects;

import models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Add a new patient
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patients (Name, DOB, Gender, ContactInfo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setDate(2, java.sql.Date.valueOf(patient.getDob()));
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContactInfo());

            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patients";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient p = new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("Name"),
                    rs.getDate("DOB").toLocalDate(),
                    rs.getString("Gender"),
                    rs.getString("ContactInfo")
                );
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    // Get patient by ID
    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM Patients WHERE PatientID = ?";
        Patient patient = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("Name"),
                    rs.getDate("DOB").toLocalDate(),
                    rs.getString("Gender"),
                    rs.getString("ContactInfo")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    // Update patient
    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patients SET Name = ?, DOB = ?, Gender = ?, ContactInfo = ? WHERE PatientID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setDate(2, java.sql.Date.valueOf(patient.getDob()));
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContactInfo());
            stmt.setInt(5, patient.getPatientID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete patient
    public void deletePatient(int patientID) {
        String sql = "DELETE FROM Patients WHERE PatientID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientID);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Patient deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
