package UI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Hospital Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Login as", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 20, 20));

        JButton patientBtn = new JButton("Patient");
        JButton doctorBtn = new JButton("Doctor");
        JButton adminBtn = new JButton("Admin");

        patientBtn.addActionListener(e -> login("Patient"));
        doctorBtn.addActionListener(e -> login("Doctor"));
        adminBtn.addActionListener(e -> login("Admin"));

        buttonPanel.add(patientBtn);
        buttonPanel.add(doctorBtn);
        buttonPanel.add(adminBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void login(String role) {
        String username = JOptionPane.showInputDialog(this, "Enter " + role + " Username:");
        String password = JOptionPane.showInputDialog(this, "Enter Password:");

        if (validateLogin(role, username, password)) {
            JOptionPane.showMessageDialog(this, role + " login successful!");
            openPortal(role);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials for " + role + "!");
        }
    }

    private boolean validateLogin(String role, String username, String password) {
        switch (role) {
            case "Patient":
                return username.equals("patient1") && password.equals("pass123");
            case "Doctor":
                return username.equals("doctor1") && password.equals("doc456");
            case "Admin":
                return username.equals("admin") && password.equals("admin123");
            default:
                return false;
        }
    }

    private void openPortal(String role) {
        switch (role) {
            case "Patient":
                new PatientForm().setVisible(true);
                break;
            case "Doctor":
                new DoctorDashboard().setVisible(true);
                break;
            case "Admin":
                JOptionPane.showMessageDialog(this, "Admin Dashboard under construction.");
                break;
        }
        this.dispose(); // close MainMenu
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
