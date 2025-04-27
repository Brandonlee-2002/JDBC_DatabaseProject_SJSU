package DataAccessObjects;

import models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

	// Add a new patient
	public void addPatient(Patient patient) {
	    String sql = "INSERT INTO Patients (Name, DOB, Gender, ContactInfo, Username, Password) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, patient.getName());
	        stmt.setDate(2, java.sql.Date.valueOf(patient.getDob()));
	        stmt.setString(3, patient.getGender());
	        stmt.setString(4, patient.getContactInfo());
	        stmt.setString(5, patient.getUsername());  // 👈 username field
	        stmt.setString(6, patient.getPassword());  // 👈 password field

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
                    rs.getString("ContactInfo"),
                    rs.getString("Password"),
                    rs.getString("Username")
                );
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    // Login patient by ID and Password
    public Patient loginPatient(String username, String password) {
        String sql = "SELECT * FROM Patients WHERE Username = ? AND Password = ?";
        Patient patient = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient(
                    rs.getInt("PatientID"),
                    rs.getString("Name"),
                    rs.getDate("DOB").toLocalDate(),
                    rs.getString("Gender"),
                    rs.getString("ContactInfo"),
                    rs.getString("Username"),
                    rs.getString("Password")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    // Update patient (mainly for updating contact info or password)
    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patients SET Name = ?, DOB = ?, Gender = ?, ContactInfo = ?, Password = ? WHERE PatientID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setDate(2, java.sql.Date.valueOf(patient.getDob()));
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContactInfo());
            stmt.setString(5, patient.getPassword());
            stmt.setInt(6, patient.getPatientID());

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
