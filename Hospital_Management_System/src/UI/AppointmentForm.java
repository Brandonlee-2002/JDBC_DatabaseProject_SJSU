package UI;

import com.toedter.calendar.JDateChooser;
import DataAccessObjects.AppointmentDAO;
import DataAccessObjects.DoctorDAO;
import models.Appointment;
import models.Doctor;
import models.Patient;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class AppointmentForm extends JFrame {
    private JComboBox<Doctor> doctorComboBox;
    private JDateChooser dateChooser;
    private JSpinner hourSpinner, minuteSpinner;
    private JTextField reasonField;
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private Patient patient;

    public AppointmentForm(Patient patient) {
        this.patient = patient;

        setTitle("Make Appointment");
        setSize(450, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        doctorComboBox = new JComboBox<>();
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        hourSpinner = new JSpinner(new SpinnerNumberModel(9, 0, 23, 1));  // 24h format
        minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));

        reasonField = new JTextField();

        add(new JLabel("Select Doctor:"));
        add(doctorComboBox);

        add(new JLabel("Select Date:"));
        add(dateChooser);

        add(new JLabel("Hour (0–23):"));
        add(hourSpinner);

        add(new JLabel("Minutes:"));
        add(minuteSpinner);

        add(new JLabel("Reason:"));
        add(reasonField);

        JButton submitBtn = new JButton("Book Appointment");
        submitBtn.addActionListener(e -> bookAppointment());
        add(submitBtn);

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
            Doctor doctor = (Doctor) doctorComboBox.getSelectedItem();
            Date selectedDate = dateChooser.getDate();

            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select a valid date.");
                return;
            }

            int hour = (int) hourSpinner.getValue();
            int minute = (int) minuteSpinner.getValue();

            LocalDateTime appointmentDateTime = selectedDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .atTime(LocalTime.of(hour, minute));

            String reason = reasonField.getText();

            Appointment appointment = new Appointment(
                    0,
                    patient.getPatientID(),
                    doctor.getDoctorID(),
                    appointmentDateTime,
                    reason
            );

            appointmentDAO.addAppointment(appointment);
            JOptionPane.showMessageDialog(this, "Appointment booked!");           
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error booking appointment. Please check your input.");
            e.printStackTrace();
        }
    }
}
