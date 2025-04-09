package com.java.hospital.dao;

import com.java.hospital.model.Patient;
import com.java.hospital.myexceptions.PatientNumberNotFoundException;

import java.util.List;

public interface PatientDao {
    Patient getPatientById(int patientId) throws PatientNumberNotFoundException;
    List<Patient> getAllPatients();
    boolean addPatient(Patient patient);
    boolean updatePatient(Patient patient);
    boolean deletePatient(int patientId);
}
