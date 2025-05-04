package DataAccessObjects;

import models.Prescription;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    
    public void addPrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescriptions (PatientID, DoctorID, MedicineID, Dosage, Frequency, Duration, PrescriptionDate, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prescription.getPatientID());
            stmt.setInt(2, prescription.getDoctorID());
            stmt.setInt(3, prescription.getMedicineID());
            stmt.setString(4, prescription.getDosage());
            stmt.setString(5, prescription.getFrequency());
            stmt.setString(6, prescription.getDuration());
            stmt.setDate(7, java.sql.Date.valueOf(prescription.getPrescriptionDate()));
            stmt.setString(8, prescription.getStatus());

            stmt.executeUpdate();
            System.out.println("Prescription added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prescription> getPrescriptionsByPatientId(int patientId) {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM Prescriptions WHERE PatientID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prescriptions.add(new Prescription(
                    rs.getInt("PrescriptionID"),
                    patientId,
                    rs.getInt("DoctorID"),
                    rs.getInt("MedicineID"),
                    rs.getString("Dosage"),
                    rs.getString("Frequency"),
                    rs.getString("Duration"),
                    rs.getDate("PrescriptionDate").toLocalDate(),
                    rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prescriptions;
    }

    public List<Prescription> getPrescriptionsByDoctorId(int doctorId) {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM Prescriptions WHERE DoctorID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                prescriptions.add(new Prescription(
                    rs.getInt("PrescriptionID"),
                    rs.getInt("PatientID"),
                    doctorId,
                    rs.getInt("MedicineID"),
                    rs.getString("Dosage"),
                    rs.getString("Frequency"),
                    rs.getString("Duration"),
                    rs.getDate("PrescriptionDate").toLocalDate(),
                    rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prescriptions;
    }

    public void updatePrescriptionStatus(int prescriptionId, String status) {
        String sql = "UPDATE Prescriptions SET Status = ? WHERE PrescriptionID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, prescriptionId);
            stmt.executeUpdate();
            System.out.println("Prescription status updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 