package com.java.hospital.dao;

import com.java.hospital.model.Appointment;
import com.java.hospital.model.Patient;
import com.java.hospital.myexceptions.PatientNumberNotFoundException;

import java.util.List;

public class HospitalServiceImpl implements IHospitalService {

    private AppointmentDao appointmentDao;
    private PatientDao patientDao;

    public HospitalServiceImpl() {
        this.appointmentDao = new AppointmentDaoImpl();
        this.patientDao = new PatientDaoImpl();  
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentDao.getAppointmentById(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        return appointmentDao.getAppointmentsForPatient(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        return appointmentDao.getAppointmentsForDoctor(doctorId);
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        return appointmentDao.scheduleAppointment(appointment);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return appointmentDao.updateAppointment(appointment);
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        return appointmentDao.cancelAppointment(appointmentId);
    }

    @Override
    public Patient getPatientById(int patientId) throws PatientNumberNotFoundException {
        Patient p = patientDao.getPatientById(patientId);
        if (p == null) {
            throw new PatientNumberNotFoundException("Patient ID " + patientId + " not found.");
        }
        return p;
    }
}
