package DataAccessObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Doctor;

public class DoctorDAO {

    // Login doctor using username and password
    public Doctor loginDoctor(String username, String password) {
        String sql = "SELECT * FROM Doctors WHERE Username = ? AND Password = ?"; //Prepared statement to login doctor
        Doctor doctor = null;

        try (Connection conn = DBConnection.getConnection(); //Establishes a connection to the database
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Bind method parameters (username and password) to the SQL query
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { //Will get doctor record associated with username and password
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

    public List<Doctor> getAllDoctors() {
    // Create a list to store all doctors retrieved from the database
    List<Doctor> list = new ArrayList<>();
    
    // SQL query to select all records from the Doctors table
    String sql = "SELECT * FROM Doctors";

    // Try-with-resources to automatically close database resources
    try (Connection conn = DBConnection.getConnection(); // Establishes a connection to the database
         Statement stmt = conn.createStatement(); // Creates a statement to execute the SQL query
         ResultSet rs = stmt.executeQuery(sql)) { // Executes the query and stores the result in rs

        // Loop through the result set
        while (rs.next()) {
            // Create a new Doctor object using data from the current row
            Doctor d = new Doctor(
                    rs.getInt("DoctorID"),
                    rs.getString("Name"),
                    rs.getString("Specialty"),
                    rs.getString("ContactInfo"),
                    rs.getString("Username"),
                    rs.getString("Password")
            );
            // Add the Doctor object to the list
            list.add(d);
        }
    } catch (SQLException e) {
        // Print any SQL exceptions that occur
        e.printStackTrace();
    }

    // Return the list of doctors
    return list;
}}
