package UI;

import DataAccessObjects.MedicalRecordDAO;
import DataAccessObjects.PrescriptionDAO;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import models.Doctor;
import models.MedicalRecords;
import models.Prescription;

public class DoctorDashboard extends JFrame {
    private Doctor doctor;
    private MedicalRecordDAO recordDAO = new MedicalRecordDAO();
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    //UI code
    public DoctorDashboard(Doctor doctor) {
        this.doctor = doctor;

        setTitle("Doctor Dashboard - " + doctor.getName());
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Dimension buttonSize = new Dimension(200, 40);

        JButton viewHistoryBtn = new JButton("View Patient History");
        JButton updateRecordsBtn = new JButton("Update Medical Records");
        JButton prescribeBtn = new JButton("Prescribe Medicine");
        JButton viewPrescriptionsBtn = new JButton("View Prescriptions");

        viewHistoryBtn.setMaximumSize(buttonSize);
        updateRecordsBtn.setMaximumSize(buttonSize);
        prescribeBtn.setMaximumSize(buttonSize);
        viewPrescriptionsBtn.setMaximumSize(buttonSize);

        viewHistoryBtn.addActionListener(e -> new ViewPatientHistoryWindow().setVisible(true));
        updateRecordsBtn.addActionListener(e -> new UpdateMedicalRecordWindow().setVisible(true));
        prescribeBtn.addActionListener(e -> new PrescriptionForm(doctor.getDoctorID()).setVisible(true));
        viewPrescriptionsBtn.addActionListener(e -> new ViewPrescriptionsWindow().setVisible(true));

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

    private class ViewPatientHistoryWindow extends JFrame {
        public ViewPatientHistoryWindow() {
            setTitle("View Patient History");
            setSize(450, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());
            JTextField patientIdField = new JTextField();
            JButton loadBtn = new JButton("Load History");
            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);

            loadBtn.addActionListener(e -> { //Get patient records
                try {
                    int patientId = Integer.parseInt(patientIdField.getText());
                    List<MedicalRecords> records = recordDAO.getRecordsByPatientId(patientId);
                    StringBuilder sb = new StringBuilder();
                    for (MedicalRecords record : records) {
                        sb.append("Date: ").append(record.getRecordDate())
                          .append("\nDiagnosis: ").append(record.getDiagnosis())
                          .append("\nTreatment: ").append(record.getTreatment())
                          .append("\n-----------------------------\n");
                    }
                    resultArea.setText(sb.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Patient ID.");
                }
            });

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.add(new JLabel("Enter Patient ID: "), BorderLayout.WEST);
            inputPanel.add(patientIdField, BorderLayout.CENTER);
            inputPanel.add(loadBtn, BorderLayout.EAST);

            panel.add(inputPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

            add(panel);
        }
    }

    private class UpdateMedicalRecordWindow extends JFrame {
        public UpdateMedicalRecordWindow() {
            setTitle("Update Medical Record");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(5, 2, 10, 10));

            JTextField patientIdField = new JTextField();
            JTextField diagnosisField = new JTextField();
            JTextField treatmentField = new JTextField();

            JButton saveBtn = new JButton("Save Record");
            saveBtn.addActionListener(e -> {
                try {
                    int patientId = Integer.parseInt(patientIdField.getText());
                    String diagnosis = diagnosisField.getText();
                    String treatment = treatmentField.getText();
                    recordDAO.addRecord(new MedicalRecords(0, patientId, diagnosis, treatment, LocalDate.now()));
                    JOptionPane.showMessageDialog(this, "Record saved.");
                    this.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving record.");
                }
            });

            add(new JLabel("Patient ID:"));
            add(patientIdField);
            add(new JLabel("Diagnosis:"));
            add(diagnosisField);
            add(new JLabel("Treatment:"));
            add(treatmentField);
            add(new JLabel(""));
            add(saveBtn);
        }
    }

    private class PrescriptionForm extends JFrame {
        public PrescriptionForm(int doctorID) {
            setTitle("Prescribe Medicine");
            setSize(400, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(8, 2, 10, 10));

            JTextField patientIdField = new JTextField();
            JTextField medicineIdField = new JTextField();
            JTextField dosageField = new JTextField();
            JTextField frequencyField = new JTextField();
            JTextField durationField = new JTextField();
            JTextField statusField = new JTextField();

            //Adding a new prescription
            JButton prescribeBtn = new JButton("Prescribe");
            prescribeBtn.addActionListener(e -> {
                try {
                    int patientId = Integer.parseInt(patientIdField.getText());
                    int medicineId = Integer.parseInt(medicineIdField.getText());
                    String dosage = dosageField.getText();
                    String frequency = frequencyField.getText();
                    String duration = durationField.getText();
                    String status = statusField.getText();

                    Prescription prescription = new Prescription(patientId, doctorID, medicineId, dosage, frequency, duration, LocalDate.now(), status);
                    prescriptionDAO.addPrescription(prescription);

                    JOptionPane.showMessageDialog(this, "Prescription saved.");
                    this.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving prescription.");
                }
            });

            add(new JLabel("Patient ID:"));
            add(patientIdField);
            add(new JLabel("Medicine ID:"));
            add(medicineIdField);
            add(new JLabel("Dosage:"));
            add(dosageField);
            add(new JLabel("Frequency:"));
            add(frequencyField);
            add(new JLabel("Duration:"));
            add(durationField);
            add(new JLabel("Status:"));
            add(statusField);
            add(new JLabel(""));
            add(prescribeBtn);
        }
    }

    private class ViewPrescriptionsWindow extends JFrame {
        public ViewPrescriptionsWindow() {
            setTitle("View Prescriptions");
            setSize(450, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new BorderLayout());
            JTextField patientIdField = new JTextField();
            JButton loadBtn = new JButton("Load Prescriptions");
            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);

            loadBtn.addActionListener(e -> {
                try {
                    int patientId = Integer.parseInt(patientIdField.getText());
                    List<Prescription> list = prescriptionDAO.getPrescriptionsByPatientId(patientId);
                    StringBuilder sb = new StringBuilder();
                    for (Prescription p : list) {
                        sb.append("Date: ").append(p.getPrescriptionDate())
                          .append("\nMedicine ID: ").append(p.getMedicineID())
                          .append("\nDosage: ").append(p.getDosage())
                          .append("\nFrequency: ").append(p.getFrequency())
                          .append("\nDuration: ").append(p.getDuration())
                          .append("\nStatus: ").append(p.getStatus())
                          .append("\n-----------------------------\n");
                    }
                    resultArea.setText(sb.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid Patient ID.");
                }
            });

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.add(new JLabel("Enter Patient ID: "), BorderLayout.WEST);
            inputPanel.add(patientIdField, BorderLayout.CENTER);
            inputPanel.add(loadBtn, BorderLayout.EAST);

            panel.add(inputPanel, BorderLayout.NORTH);
            panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

            add(panel);
        }
    }
}
