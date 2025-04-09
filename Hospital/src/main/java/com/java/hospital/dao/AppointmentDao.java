package com.java.hospital.dao;

import com.java.hospital.model.Appointment;
import java.util.List;

public interface AppointmentDao {
    Appointment getAppointmentById(int appointmentId);
    List<Appointment> getAppointmentsForPatient(int patientId);
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    boolean scheduleAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean cancelAppointment(int appointmentId);
}
