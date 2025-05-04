package UI;

import models.Doctor;

import javax.swing.*;

public class DoctorDashboard extends JFrame {
    private Doctor doctor;

    public DoctorDashboard(Doctor doctor) {
        this.doctor = doctor;

        setTitle("Doctor Dashboard - " + doctor.getName());
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton viewHistoryBtn = new JButton("View Patient History");
        JButton updateRecordsBtn = new JButton("Update Medical Records");
        JButton prescribeBtn = new JButton("Prescribe Medicine");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(viewHistoryBtn);
        panel.add(updateRecordsBtn);
        panel.add(prescribeBtn);

        add(panel);
    }
}
