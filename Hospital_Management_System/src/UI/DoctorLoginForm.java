package UI;

import DataAccessObjects.DoctorDAO;
import models.Doctor;

import javax.swing.*;

public class DoctorLoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DoctorDAO doctorDAO = new DoctorDAO();

    public DoctorLoginForm() {
        setTitle("Doctor Login");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> handleLogin());
        add(loginButton);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Doctor doctor = doctorDAO.loginDoctor(username, password);

        if (doctor != null) {
            JOptionPane.showMessageDialog(this, "Welcome, " + doctor.getName());
            new DoctorDashboard(doctor).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }
}
