package UI;


import DataAccessObjects.PatientDAO;
import models.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PatientForm extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, dobField, genderField, contactField;
    private PatientDAO patientDAO = new PatientDAO();

    public PatientForm() {
        setTitle("Patient Management");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI layout
        setLayout(new BorderLayout());

        // Top input form
        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        nameField = new JTextField();
        dobField = new JTextField("YYYY-MM-DD");
        genderField = new JTextField();
        contactField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("DOB:"));
        inputPanel.add(dobField);
        inputPanel.add(new JLabel("Gender:"));
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Contact:"));
        inputPanel.add(contactField);

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> addPatient());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table for patient data
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "DOB", "Gender", "Contact"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadPatients();
    }

    private void loadPatients() {
        tableModel.setRowCount(0); // Clear existing rows
        List<Patient> patients = patientDAO.getAllPatients();
        for (Patient p : patients) {
            tableModel.addRow(new Object[]{
                    p.getPatientID(),
                    p.getName(),
                    p.getDob(),
                    p.getGender(),
                    p.getContactInfo()
            });
        }
    }

    private void addPatient() {
        try {
            String name = nameField.getText();
            LocalDate dob = LocalDate.parse(dobField.getText());
            String gender = genderField.getText();
            String contact = contactField.getText();

            Patient patient = new Patient(name, dob, gender, contact);
            patientDAO.addPatient(patient);
            loadPatients();
            clearFields();
            JOptionPane.showMessageDialog(this, "Patient added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.setText("");
        dobField.setText("YYYY-MM-DD");
        genderField.setText("");
        contactField.setText("");
    }
}
