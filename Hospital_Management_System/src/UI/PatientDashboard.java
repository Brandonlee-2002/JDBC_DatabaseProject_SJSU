package UI;

import models.Patient;
import javax.swing.*;
import java.awt.*;

public class PatientDashboard extends JFrame {
    private Patient patient;

    public PatientDashboard(Patient patient) {
        this.patient = patient;

        setTitle("Patient Dashboard - " + patient.getName());
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Patient info panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.add(new JLabel("Name: " + patient.getName()));
        infoPanel.add(new JLabel("DOB: " + patient.getDob()));
        infoPanel.add(new JLabel("Gender: " + patient.getGender()));
        infoPanel.add(new JLabel("Contact Info: " + patient.getContactInfo()));
        add(infoPanel, BorderLayout.NORTH);

        // Action buttons
        JPanel actionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton appointmentBtn = new JButton("Make Appointment");
        JButton prescriptionsBtn = new JButton("View Prescriptions and Medications");
        JButton historyBtn = new JButton("View Medical History");
        JButton refillBtn = new JButton("Refill Prescriptions");

        appointmentBtn.addActionListener(e -> new AppointmentForm(patient).setVisible(true));
        prescriptionsBtn.addActionListener(e -> new PrescriptionWindow(patient).setVisible(true));
        historyBtn.addActionListener(e -> new MedicalHistoryWindow(patient).setVisible(true));
        refillBtn.addActionListener(e -> new RefillPrescriptionWindow(patient).setVisible(true));

        actionsPanel.add(appointmentBtn);
        actionsPanel.add(prescriptionsBtn);
        actionsPanel.add(historyBtn);
        actionsPanel.add(refillBtn);

        add(actionsPanel, BorderLayout.CENTER);
    }

    // -------------------------------
    // Internal Window 1: Prescriptions
    // -------------------------------
    private static class PrescriptionWindow extends JFrame {
        public PrescriptionWindow(Patient patient) {
            setTitle("Prescriptions - " + patient.getName());
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            // Placeholder for actual prescriptions
            JTextArea textArea = new JTextArea("Prescriptions and medications will be displayed here.");
            textArea.setEditable(false);
            add(new JScrollPane(textArea));
        }
    }

    // -------------------------------
    // Internal Window 2: Medical History
    // -------------------------------
    private static class MedicalHistoryWindow extends JFrame {
        public MedicalHistoryWindow(Patient patient) {
            setTitle("Medical History - " + patient.getName());
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JTextArea historyArea = new JTextArea("Medical history records will be displayed here.");
            historyArea.setEditable(false);
            add(new JScrollPane(historyArea));
        }
    }

    // -------------------------------
    // Internal Window 3: Refill Prescriptions
    // -------------------------------
    private static class RefillPrescriptionWindow extends JFrame {
        public RefillPrescriptionWindow(Patient patient) {
            setTitle("Refill Prescriptions - " + patient.getName());
            setSize(400, 250);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("Request a refill for a prescription:");
            JTextField prescriptionField = new JTextField();
            JButton submitBtn = new JButton("Request Refill");

            submitBtn.addActionListener(e -> {
                String prescription = prescriptionField.getText().trim();
                if (!prescription.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Refill request for '" + prescription + "' submitted.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid prescription.");
                }
            });

            panel.add(label, BorderLayout.NORTH);
            panel.add(prescriptionField, BorderLayout.CENTER);
            panel.add(submitBtn, BorderLayout.SOUTH);

            add(panel);
        }
    }
}
