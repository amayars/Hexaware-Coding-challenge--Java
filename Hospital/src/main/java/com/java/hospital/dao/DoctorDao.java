package com.java.hospital.dao;

import com.java.hospital.model.Doctor;
import java.util.List;

public interface DoctorDao {
    Doctor getDoctorById(int doctorId);
    List<Doctor> getAllDoctors();
    boolean addDoctor(Doctor doctor);
    boolean updateDoctor(Doctor doctor);
    boolean deleteDoctor(int doctorId);
}
