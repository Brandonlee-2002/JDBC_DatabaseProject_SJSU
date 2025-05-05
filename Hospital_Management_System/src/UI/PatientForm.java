package UI;

import DataAccessObjects.PatientDAO;
import models.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private PatientDAO patientDAO = new PatientDAO();

    public PatientForm() {
        setTitle("Patient Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Password:"));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginPatient());
        add(loginButton);
    }

    private void loginPatient() {
        try {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            Patient currentPatient = patientDAO.loginPatient(username, password);

            if (currentPatient != null) {
                new PatientDashboard(currentPatient).setVisible(true);
                this.dispose(); // Close login form
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during login.");
        }
    }
}
