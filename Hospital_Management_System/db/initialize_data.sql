-- initialize_data.sql
-- Populate Hospital Management System tables with dummy data

USE hospital_db;

-- Patients
INSERT INTO Patients (Name, DOB, Gender, ContactInfo) VALUES
('Alice Smith', '1985-07-15', 'Female', 'alice.smith@example.com'),
('Bob Johnson', '1972-11-23', 'Male', 'bob.johnson@example.com'),
('Clara Lee', '1990-04-12', 'Female', 'clara.lee@example.com'),
('David Kim', '1983-03-19', 'Male', 'david.kim@example.com'),
('Ella Brown', '2000-10-01', 'Female', 'ella.brown@example.com'),
('Frank White', '1978-08-30', 'Male', 'frank.white@example.com'),
('Grace Chen', '1995-05-25', 'Female', 'grace.chen@example.com'),
('Henry Adams', '1988-02-14', 'Male', 'henry.adams@example.com'),
('Irene Clark', '1965-12-05', 'Female', 'irene.clark@example.com'),
('Jack Turner', '1993-06-08', 'Male', 'jack.turner@example.com'),
('Karen Miller', '1981-01-17', 'Female', 'karen.miller@example.com'),
('Liam Wright', '1999-07-20', 'Male', 'liam.wright@example.com'),
('Monica Green', '1974-09-29', 'Female', 'monica.green@example.com'),
('Nathan Scott', '1986-10-10', 'Male', 'nathan.scott@example.com'),
('Olivia Parker', '2002-03-03', 'Female', 'olivia.parker@example.com');

-- Doctors
INSERT INTO Doctors (Name, Specialty, ContactInfo) VALUES
('Dr. Alan Grant', 'Cardiology', 'alan.grant@hospital.com'),
('Dr. Bella Swan', 'Dermatology', 'bella.swan@hospital.com'),
('Dr. Charles Xavier', 'Neurology', 'charles.x@hospital.com'),
('Dr. Diana Prince', 'Pediatrics', 'diana.prince@hospital.com'),
('Dr. Ethan Hunt', 'Orthopedics', 'ethan.hunt@hospital.com'),
('Dr. Fiona Shaw', 'Gynecology', 'fiona.shaw@hospital.com'),
('Dr. George Miller', 'Radiology', 'george.miller@hospital.com'),
('Dr. Hannah Lee', 'ENT', 'hannah.lee@hospital.com'),
('Dr. Ian Malcolm', 'Oncology', 'ian.malcolm@hospital.com'),
('Dr. Julia Kent', 'Urology', 'julia.kent@hospital.com'),
('Dr. Kevin Adams', 'Gastroenterology', 'kevin.adams@hospital.com'),
('Dr. Laura Palmer', 'Pulmonology', 'laura.palmer@hospital.com'),
('Dr. Michael Ford', 'General Medicine', 'michael.ford@hospital.com'),
('Dr. Nora Bishop', 'Endocrinology', 'nora.bishop@hospital.com'),
('Dr. Oscar Brooks', 'Psychiatry', 'oscar.brooks@hospital.com');

-- Appointments
INSERT INTO Appointments (PatientID, DoctorID, AppointmentDate, Reason) VALUES
(1, 1, '2025-04-10 09:00:00', 'Regular heart checkup'),
(2, 2, '2025-04-11 10:30:00', 'Skin rash consultation'),
(3, 3, '2025-04-12 14:00:00', 'Recurring headaches'),
(4, 4, '2025-04-13 13:00:00', 'Child vaccination'),
(5, 5, '2025-04-14 15:00:00', 'Knee pain'),
(6, 6, '2025-04-10 11:00:00', 'Routine OB-GYN check'),
(7, 7, '2025-04-15 12:30:00', 'MRI follow-up'),
(8, 8, '2025-04-16 09:45:00', 'Hearing loss'),
(9, 9, '2025-04-17 16:00:00', 'Cancer screening'),
(10, 10, '2025-04-18 08:30:00', 'Bladder issue'),
(11, 11, '2025-04-19 14:30:00', 'Stomach pain'),
(12, 12, '2025-04-20 10:00:00', 'Breathing trouble'),
(13, 13, '2025-04-21 11:45:00', 'Fever and body ache'),
(14, 14, '2025-04-22 09:15:00', 'Thyroid check'),
(15, 15, '2025-04-23 13:15:00', 'Sleep disorder consultation');

-- MedicalRecords
INSERT INTO MedicalRecords (PatientID, Diagnosis, Treatment, RecordDate) VALUES
(1, 'Hypertension', 'Medication + diet', '2025-04-10'),
(2, 'Eczema', 'Topical steroids', '2025-04-11'),
(3, 'Migraines', 'Pain relievers + rest', '2025-04-12'),
(4, 'Vaccination', 'DTaP dose 2', '2025-04-13'),
(5, 'Arthritis', 'Physical therapy', '2025-04-14'),
(6, 'Prenatal visit', 'Routine exam', '2025-04-10'),
(7, 'Brain scan follow-up', 'MRI analysis', '2025-04-15'),
(8, 'Otitis media', 'Antibiotics', '2025-04-16'),
(9, 'Breast cancer screening', 'Mammogram', '2025-04-17'),
(10, 'UTI', 'Antibiotics', '2025-04-18'),
(11, 'Gastritis', 'Antacids', '2025-04-19'),
(12, 'Asthma', 'Inhalers', '2025-04-20'),
(13, 'Flu', 'Rest + antivirals', '2025-04-21'),
(14, 'Hypothyroidism', 'Thyroxine', '2025-04-22'),
(15, 'Insomnia', 'Melatonin + sleep hygiene', '2025-04-23');

-- Billing
INSERT INTO Billing (PatientID, TotalAmount, PaymentStatus, BillingDate) VALUES
(1, 150.00, 'Paid', '2025-04-10'),
(2, 100.00, 'Paid', '2025-04-11'),
(3, 80.00, 'Unpaid', '2025-04-12'),
(4, 60.00, 'Paid', '2025-04-13'),
(5, 200.00, 'Unpaid', '2025-04-14'),
(6, 90.00, 'Paid', '2025-04-10'),
(7, 300.00, 'Paid', '2025-04-15'),
(8, 75.00, 'Unpaid', '2025-04-16'),
(9, 250.00, 'Paid', '2025-04-17'),
(10, 95.00, 'Paid', '2025-04-18'),
(11, 110.00, 'Unpaid', '2025-04-19'),
(12, 130.00, 'Paid', '2025-04-20'),
(13, 70.00, 'Paid', '2025-04-21'),
(14, 85.00, 'Unpaid', '2025-04-22'),
(15, 145.00, 'Paid', '2025-04-23');

-- Pharmacy
INSERT INTO Pharmacy (Name, Stock, Price, ExpiryDate) VALUES
('Paracetamol 500mg', 500, 0.10, '2026-12-31'),
('Amoxicillin 250mg', 200, 0.50, '2025-11-30'),
('Ibuprofen 400mg', 300, 0.30, '2025-09-15'),
('Loratadine 10mg', 150, 0.25, '2026-04-01'),
('Omeprazole 20mg', 180, 0.40, '2026-01-01'),
('Atorvastatin 10mg', 220, 0.60, '2027-03-10'),
('Ciprofloxacin 500mg', 90, 0.70, '2025-08-20'),
('Aspirin 100mg', 400, 0.15, '2026-07-25'),
('Metformin 500mg', 250, 0.35, '2026-10-10'),
('Vitamin D 1000IU', 320, 0.20, '2027-05-01'),
('Lisinopril 10mg', 180, 0.45, '2026-12-12'),
('Hydrochlorothiazide 25mg', 210, 0.38, '2026-11-11'),
('Albuterol Inhaler', 75, 2.50, '2025-06-30'),
('Insulin Glargine', 60, 5.00, '2025-05-15'),
('Melatonin 5mg', 130, 0.22, '2026-08-08');
