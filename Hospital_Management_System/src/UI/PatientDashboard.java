package UI;

import models.Patient;
import UI.AppointmentForm;


import javax.swing.*;
import java.awt.*;

public class PatientDashboard extends JFrame {
    private Patient patient;

    public PatientDashboard(Patient patient) {
        this.patient = patient;

        setTitle("Welcome, " + patient.getName());
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Patient info
        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.add(new JLabel("Name: " + patient.getName()));
        infoPanel.add(new JLabel("DOB: " + patient.getDob()));
        infoPanel.add(new JLabel("Gender: " + patient.getGender()));
        infoPanel.add(new JLabel("Contact: " + patient.getContactInfo()));
        add(infoPanel, BorderLayout.NORTH);

        // Action buttons
        JPanel actionPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton appointmentBtn = new JButton("Make Appointment");
        JButton prescriptionsBtn = new JButton("View Prescriptions and Medications");
        JButton historyBtn = new JButton("View Medical History");
        JButton refillBtn = new JButton("Refill Prescriptions");

        appointmentBtn.addActionListener(e -> {
            new AppointmentForm(patient).setVisible(true);
        });


        prescriptionsBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Prescription viewer coming soon.");
        });

        historyBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Medical history viewer coming soon.");
        });

        refillBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Prescription refill coming soon.");
        });

        actionPanel.add(appointmentBtn);
        actionPanel.add(prescriptionsBtn);
        actionPanel.add(historyBtn);
        actionPanel.add(refillBtn);

        add(actionPanel, BorderLayout.CENTER);
        
        appointmentBtn.addActionListener(e -> {
            new AppointmentForm(patient).setVisible(true);
        });

    }
}
