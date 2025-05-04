package UI;

import models.Doctor;
import javax.swing.*;
import java.awt.*;

public class DoctorDashboard extends JFrame {
    private Doctor doctor;

    public DoctorDashboard(Doctor doctor) {
        this.doctor = doctor;

        setTitle("Doctor Dashboard - " + doctor.getName());
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add some padding
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons with consistent size
        Dimension buttonSize = new Dimension(200, 40);
        
        JButton viewHistoryBtn = new JButton("View Patient History");
        JButton updateRecordsBtn = new JButton("Update Medical Records");
        JButton prescribeBtn = new JButton("Prescribe Medicine");
        JButton viewPrescriptionsBtn = new JButton("View Prescriptions");

        // Set button sizes
        viewHistoryBtn.setMaximumSize(buttonSize);
        updateRecordsBtn.setMaximumSize(buttonSize);
        prescribeBtn.setMaximumSize(buttonSize);
        viewPrescriptionsBtn.setMaximumSize(buttonSize);

        // Add action listeners
        prescribeBtn.addActionListener(e -> {
            new PrescriptionForm(doctor.getDoctorID()).setVisible(true);
        });

        viewPrescriptionsBtn.addActionListener(e -> {
            // TODO: Implement view prescriptions functionality
            JOptionPane.showMessageDialog(this, "View prescriptions functionality coming soon!");
        });

        // Add buttons to panel with spacing
        panel.add(Box.createVerticalStrut(10));
        panel.add(viewHistoryBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(updateRecordsBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(prescribeBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(viewPrescriptionsBtn);

        add(panel);
    }
}
