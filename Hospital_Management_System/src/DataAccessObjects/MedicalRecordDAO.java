package DataAccessObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.MedicalRecords;

public class MedicalRecordDAO {

    // Retrieves a list of medical records for a specific patient by their ID
    public List<MedicalRecords> getRecordsByPatientId(int patientId) {
        // Create a list to store the retrieved medical records
        List<MedicalRecords> records = new ArrayList<>();
        
        // SQL query with a placeholder to select records for a specific patient
        String sql = "SELECT * FROM MedicalRecords WHERE PatientID = ?";

        // Try-with-resources to manage database connection and statement
        try (Connection conn = DBConnection.getConnection(); // Establishes a connection to the database
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepares the SQL query

            // Bind the patient ID to the first placeholder in the SQL query
            stmt.setInt(1, patientId);

            // Execute the query and store the result set
            ResultSet rs = stmt.executeQuery();

            // Iterate over the result set and create MedicalRecords objects
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
            // Print any SQL exceptions that occur
            e.printStackTrace();
        }

        // Return the list of medical records
        return records;
    }

    // Adds a new medical record to the database
    public void addRecord(MedicalRecords record) {
        // SQL insert query with placeholders for the record fields
        String sql = "INSERT INTO MedicalRecords (PatientID, Diagnosis, Treatment, RecordDate) VALUES (?, ?, ?, ?)";

        // Try-with-resources to manage database connection and statement
        try (Connection conn = DBConnection.getConnection(); // Establishes a connection to the database
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepares the SQL insert statement

            // Bind the values from the MedicalRecords object to the SQL statement
            stmt.setInt(1, record.getPatientID());
            stmt.setString(2, record.getDiagnosis());
            stmt.setString(3, record.getTreatment());
            stmt.setDate(4, java.sql.Date.valueOf(record.getRecordDate()));

            // Execute the insert operation
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Print any SQL exceptions that occur
            e.printStackTrace();
        }
    }
}
