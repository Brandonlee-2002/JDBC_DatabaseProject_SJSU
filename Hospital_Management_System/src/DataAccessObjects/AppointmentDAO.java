package DataAccessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Appointment;

public class AppointmentDAO {
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointments (PatientID, DoctorID, AppointmentDate, Reason) VALUES (?, ?, ?, ?)"; //Prepared statement for adding an appointment

        try (Connection conn = DBConnection.getConnection(); //Establishes connection to the database
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Get methods to insert values into appropriate columns
            stmt.setInt(1, appointment.getPatientID());
            stmt.setInt(2, appointment.getDoctorID());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(appointment.getAppointmentDate()));
            stmt.setString(4, appointment.getReason());

            stmt.executeUpdate(); //Perform the insert
        } catch (SQLException e) { //Error handling
            e.printStackTrace();
        }
    }
}
