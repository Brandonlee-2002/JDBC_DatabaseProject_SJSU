package DataAccessObjects;

import models.MedicalRecords;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public List<MedicalRecords> getRecordsByPatientId(int patientId) {
        List<MedicalRecords> records = new ArrayList<>();
        String sql = "SELECT * FROM MedicalRecords WHERE PatientID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                records.add(new MedicalRecords(
                    rs.getInt("RecordID"),
                    patientId,
                    rs.getString("Diagnosis"),
                    rs.getString("Treatment"),
                    rs.getDate("RecordDate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public void addRecord(int patientId, String diagnosis, String treatment) {
        String sql = "INSERT INTO MedicalRecords (PatientID, Diagnosis, Treatment, RecordDate) VALUES (?, ?, ?, CURDATE())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            stmt.setString(2, diagnosis);
            stmt.setString(3, treatment);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
