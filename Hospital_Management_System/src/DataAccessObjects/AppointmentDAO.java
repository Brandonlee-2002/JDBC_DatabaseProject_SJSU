package DataAccessObjects;

import models.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointmentDAO {

    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointments (PatientID, DoctorID, AppointmentDate, Reason) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientID());
            stmt.setInt(2, appointment.getDoctorID());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(appointment.getAppointmentDate()));
            stmt.setString(4, appointment.getReason());

            stmt.executeUpdate();
            System.out.println("Appointment added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
