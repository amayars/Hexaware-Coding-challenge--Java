package com.java.hospital.main;

import com.java.hospital.dao.HospitalServiceImpl;
import com.java.hospital.dao.IHospitalService;
import com.java.hospital.model.Appointment;
import com.java.hospital.myexceptions.PatientNumberNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    static Scanner sc = new Scanner(System.in);
    static IHospitalService hospitalService = new HospitalServiceImpl();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- 🏥 Hospital Management ---");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. View Appointment by ID");
            System.out.println("3. View Appointments by Patient");
            System.out.println("4. View Appointments by Doctor");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    scheduleAppointment();
                    break;
                case 2:
                    viewAppointmentById();
                    break;
                case 3:
                    viewAppointmentsByPatient();
                    break;
                case 4:
                    viewAppointmentsByDoctor();
                    break;
                case 5:
                    updateAppointment();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }

        } while (choice != 7);
    }

    private static void scheduleAppointment() {
        try {
            System.out.print("Patient ID: ");
            int patientId = sc.nextInt();
            System.out.print("Doctor ID: ");
            int doctorId = sc.nextInt();
            sc.nextLine();
            System.out.print("Appointment Date (yyyy-MM-dd): ");
            Date date = sdf.parse(sc.nextLine());
            System.out.print("Description: ");
            String desc = sc.nextLine();

            
            hospitalService.getPatientById(patientId);  

            Appointment appointment = new Appointment(0, patientId, doctorId, date, desc);
            boolean success = hospitalService.scheduleAppointment(appointment);
            System.out.println(success ? "✅ Appointment scheduled." : "❌ Failed to schedule.");
        } catch (PatientNumberNotFoundException e) {
            System.out.println("❌ Patient not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewAppointmentById() {
        System.out.print("Enter Appointment ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        Appointment a = hospitalService.getAppointmentById(id);
        System.out.println(a != null ? a : "❌ Appointment not found.");
    }

    private static void viewAppointmentsByPatient() {
        try {
            System.out.print("Enter Patient ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            
            hospitalService.getPatientById(id);  

            List<Appointment> list = hospitalService.getAppointmentsForPatient(id);
            if (list.isEmpty()) {
                System.out.println("❌ No appointments found for patient.");
            } else {
                list.forEach(System.out::println);
            }
        } catch (PatientNumberNotFoundException e) {
            System.out.println("❌ Patient not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewAppointmentsByDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        List<Appointment> list = hospitalService.getAppointmentsForDoctor(id);
        if (list.isEmpty()) {
            System.out.println("❌ No appointments found for doctor.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void updateAppointment() {
        try {
            System.out.print("Enter Appointment ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            Appointment a = hospitalService.getAppointmentById(id);
            if (a == null) {
                System.out.println("❌ Appointment not found.");
                return;
            }

            System.out.print("New Patient ID: ");
            int newPatientId = sc.nextInt();
            System.out.print("New Doctor ID: ");
            int newDoctorId = sc.nextInt();
            sc.nextLine();
            System.out.print("New Appointment Date (yyyy-MM-dd): ");
            Date newDate = sdf.parse(sc.nextLine());
            System.out.print("New Description: ");
            String newDesc = sc.nextLine();

           
            hospitalService.getPatientById(newPatientId);  

            a.setPatientId(newPatientId);
            a.setDoctorId(newDoctorId);
            a.setAppointmentDate(newDate);
            a.setDescription(newDesc);

            boolean success = hospitalService.updateAppointment(a);
            System.out.println(success ? "✅ Appointment updated." : "❌ Failed to update.");
        } catch (PatientNumberNotFoundException e) {
            System.out.println("❌ Patient not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean cancelled = hospitalService.cancelAppointment(id);
        System.out.println(cancelled ? "✅ Appointment cancelled." : "❌ Cancel failed.");
    }
}
