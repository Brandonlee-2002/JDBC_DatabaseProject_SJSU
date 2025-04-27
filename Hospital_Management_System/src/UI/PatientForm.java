package UI;

import DataAccessObjects.PatientDAO;
import models.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientForm extends JFrame {
    private JTextField usernameField, nameField, dobField, genderField, contactField;
    private JPasswordField passwordField;
    private PatientDAO patientDAO = new PatientDAO();
    private Patient currentPatient;

    public PatientForm() {
        setTitle("Patient Portal");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Patient Information", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        nameField = new JTextField();
        dobField = new JTextField();
        genderField = new JTextField();
        contactField = new JTextField();

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        nameField.setEditable(false);

        formPanel.add(new JLabel("DOB:"));
        formPanel.add(dobField);
        dobField.setEditable(false);

        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderField);
        genderField.setEditable(false);

        formPanel.add(new JLabel("Contact Info:"));
        formPanel.add(contactField);

        JButton loadButton = new JButton("Login");
        JButton saveButton = new JButton("Update Contact");

        loadButton.addActionListener(e -> loginPatient());
        saveButton.addActionListener(e -> updateContact());

        formPanel.add(loadButton);
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void loginPatient() {
        try {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            currentPatient = patientDAO.loginPatient(username, password);

            if (currentPatient != null) {
                nameField.setText(currentPatient.getName());
                dobField.setText(currentPatient.getDob().toString());
                genderField.setText(currentPatient.getGender());
                contactField.setText(currentPatient.getContactInfo());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during login!");
        }
    }

    private void updateContact() {
        if (currentPatient == null) {
            JOptionPane.showMessageDialog(this, "Please login first!");
            return;
        }

        try {
            currentPatient.setContactInfo(contactField.getText());
            patientDAO.updatePatient(currentPatient);
            JOptionPane.showMessageDialog(this, "Contact updated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to update contact.");
        }
    }
}
