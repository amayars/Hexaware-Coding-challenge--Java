package com.java.hospital.dao;

import com.java.hospital.model.Appointment;
import com.java.hospital.model.Patient;
import com.java.hospital.myexceptions.PatientNumberNotFoundException;

import java.util.List;

public interface IHospitalService {
    
    Appointment getAppointmentById(int appointmentId);
    
    Patient getPatientById(int patientId) throws PatientNumberNotFoundException;
    
    List<Appointment> getAppointmentsForPatient(int patientId);
    
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    
    boolean scheduleAppointment(Appointment appointment);
    
    boolean updateAppointment(Appointment appointment);
    
    boolean cancelAppointment(int appointmentId);  
}
