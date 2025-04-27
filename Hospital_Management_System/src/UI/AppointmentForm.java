package UI;

import DataAccessObjects.AppointmentDAO;
import DataAccessObjects.DoctorDAO;
import models.Appointment;
import models.Doctor;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentForm extends JFrame {
    private JComboBox<Doctor> doctorComboBox;
    private JTextField appointmentDateTimeField, reasonField;
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private int patientId; // 🧍 Patient ID who is making the appointment

    public AppointmentForm(int patientId) {
        this.patientId = patientId;
        setTitle("Make an Appointment");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        doctorComboBox = new JComboBox<>();
        appointmentDateTimeField = new JTextField("YYYY-MM-DD HH:MM");
        reasonField = new JTextField();

        add(new JLabel("Select Doctor:"));
        add(doctorComboBox);

        add(new JLabel("Appointment DateTime:"));
        add(appointmentDateTimeField);

        add(new JLabel("Reason:"));
        add(reasonField);

        JButton submitButton = new JButton("Book Appointment");
        submitButton.addActionListener(e -> bookAppointment());
        add(submitButton);

        loadDoctors();
    }

    private void loadDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        for (Doctor d : doctors) {
            doctorComboBox.addItem(d);
        }
    }

    private void bookAppointment() {
        try {
            Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();
            if (selectedDoctor == null) {
                JOptionPane.showMessageDialog(this, "Please select a doctor.");
                return;
            }

            String dateTimeInput = appointmentDateTimeField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTimeInput, formatter);

            String reason = reasonField.getText();

            Appointment appointment = new Appointment(
                    0, // Auto increment ID
                    patientId,
                    selectedDoctor.getDoctorID(),
                    appointmentDateTime,
                    reason
            );

            appointmentDAO.addAppointment(appointment);

            JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
            this.dispose(); // Close form after booking
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error booking appointment. Check your inputs.");
            e.printStackTrace();
        }
    }
}
