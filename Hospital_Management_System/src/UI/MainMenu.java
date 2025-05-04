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

        // Open patient login form directly
        patientBtn.addActionListener(e -> {
            new PatientForm().setVisible(true);
            this.dispose();
        });
        
        // Open Doctor Login Form directly
        doctorBtn.addActionListener(e -> {
            new DoctorLoginForm().setVisible(true);
            this.dispose();
        });


        adminBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Admin login coming soon.");
        });

        buttonPanel.add(patientBtn);
        buttonPanel.add(doctorBtn);
        buttonPanel.add(adminBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
