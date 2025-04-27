package UI;


import DataAccessObjects.PatientDAO;
import models.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorDashboard extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private PatientDAO patientDAO = new PatientDAO();


    public DoctorDashboard() {
        setTitle("Doctor Dashboard - Patient List");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Patient Overview", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "DOB", "Gender", "Contact"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadPatients();
    }

    private void loadPatients() {
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
}
