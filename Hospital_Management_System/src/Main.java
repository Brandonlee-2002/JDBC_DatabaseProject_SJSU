import DataAccessObjects.PatientDAO;
import UI.MainMenu;
import UI.PatientForm;
import models.Patient;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO();

        // 1. Add a new patient
        Patient newPatient = new Patient("Test User", LocalDate.of(1999, 5, 15), "Non-Binary", "test.user@example.com");
        patientDAO.addPatient(newPatient);

        // 2. Get and print all patients
        System.out.println("All Patients:");
        List<Patient> allPatients = patientDAO.getAllPatients();
        for (Patient p : allPatients) {
            System.out.printf("ID: %d, Name: %s, DOB: %s, Gender: %s, Contact: %s%n",
                    p.getPatientID(), p.getName(), p.getDob(), p.getGender(), p.getContactInfo());
        }

        // 3. Get patient by ID (we’ll use the last one in the list)
        int lastPatientId = allPatients.get(allPatients.size() - 1).getPatientID();
        Patient fetched = patientDAO.getPatientById(lastPatientId);
        System.out.println("\nFetched by ID:");
        System.out.printf("Name: %s, DOB: %s%n", fetched.getName(), fetched.getDob());

        // 4. Update patient
        fetched.setName("Updated User");
        fetched.setContactInfo("updated.contact@example.com");
        patientDAO.updatePatient(fetched);
        System.out.println("\nAfter update:");
        Patient updated = patientDAO.getPatientById(fetched.getPatientID());
        System.out.printf("Name: %s, Contact: %s%n", updated.getName(), updated.getContactInfo());

        // 5. Delete the patient
        patientDAO.deletePatient(updated.getPatientID());
        System.out.println("\nDeleted test patient with ID: " + updated.getPatientID());
        
        new MainMenu().setVisible(true);
        
        /*javax.swing.SwingUtilities.invokeLater(() -> {
            new PatientForm().setVisible(true);
        });
        */
    }
}
