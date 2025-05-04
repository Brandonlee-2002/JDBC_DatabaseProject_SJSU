package UI;

import DataAccessObjects.PrescriptionDAO;
import DataAccessObjects.PatientDAO;
import DataAccessObjects.PharmacyDAO;
import models.Prescription;
import models.Patient;
import models.Pharmacy;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PrescriptionForm extends JFrame {
    private JComboBox<Patient> patientComboBox;
    private JComboBox<Pharmacy> medicineComboBox;
    private JTextField dosageField;
    private JTextField frequencyField;
    private JTextField durationField;
    private PrescriptionDAO prescriptionDAO;
    private PatientDAO patientDAO;
    private PharmacyDAO pharmacyDAO;
    private int doctorId;

    public PrescriptionForm(int doctorId) {
        this.doctorId = doctorId;
        this.prescriptionDAO = new PrescriptionDAO();
        this.patientDAO = new PatientDAO();
        this.pharmacyDAO = new PharmacyDAO();

        setTitle("Prescribe Medication");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Initialize components
        patientComboBox = new JComboBox<>();
        medicineComboBox = new JComboBox<>();
        dosageField = new JTextField();
        frequencyField = new JTextField();
        durationField = new JTextField();

        // Add components to frame
        add(new JLabel("Select Patient:"));
        add(patientComboBox);

        add(new JLabel("Select Medicine:"));
        add(medicineComboBox);

        add(new JLabel("Dosage:"));
        add(dosageField);

        add(new JLabel("Frequency:"));
        add(frequencyField);

        add(new JLabel("Duration:"));
        add(durationField);

        JButton prescribeButton = new JButton("Prescribe");
        prescribeButton.addActionListener(e -> prescribeMedication());
        add(prescribeButton);

        // Load data
        loadPatients();
        loadMedicines();
    }

    private void loadPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        for (Patient patient : patients) {
            patientComboBox.addItem(patient);
        }
    }

    private void loadMedicines() {
        List<Pharmacy> medicines = pharmacyDAO.getAllMedicines();
        for (Pharmacy medicine : medicines) {
            medicineComboBox.addItem(medicine);
        }
    }

    private void prescribeMedication() {
        try {
            Patient selectedPatient = (Patient) patientComboBox.getSelectedItem();
            Pharmacy selectedMedicine = (Pharmacy) medicineComboBox.getSelectedItem();

            if (selectedPatient == null || selectedMedicine == null) {
                JOptionPane.showMessageDialog(this, "Please select both patient and medicine.");
                return;
            }

            String dosage = dosageField.getText();
            String frequency = frequencyField.getText();
            String duration = durationField.getText();

            if (dosage.isEmpty() || frequency.isEmpty() || duration.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            Prescription prescription = new Prescription(
                selectedPatient.getPatientID(),
                doctorId,
                selectedMedicine.getMedicineID(),
                dosage,
                frequency,
                duration,
                LocalDate.now(),
                "Active"
            );

            prescriptionDAO.addPrescription(prescription);
            JOptionPane.showMessageDialog(this, "Prescription added successfully!");
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating prescription: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 