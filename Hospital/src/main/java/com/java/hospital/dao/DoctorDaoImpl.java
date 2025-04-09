package com.java.hospital.dao;

import com.java.hospital.model.Doctor;
import com.java.hospital.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "SELECT * FROM Doctor WHERE doctorId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, doctorId);
            rs = pst.executeQuery();
            if (rs.next()) {
                doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setContactNumber(rs.getString("contactNumber"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "SELECT * FROM Doctor";
            pst = connection.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctorId"));
                doctor.setFirstName(rs.getString("firstName"));
                doctor.setLastName(rs.getString("lastName"));
                doctor.setSpecialization(rs.getString("specialization"));
                doctor.setContactNumber(rs.getString("contactNumber"));
                doctors.add(doctor);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return doctors;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        boolean success = false;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "INSERT INTO Doctor (firstName, lastName, specialization, contactNumber) VALUES (?, ?, ?, ?)";
            pst = connection.prepareStatement(cmd);
            pst.setString(1, doctor.getFirstName());
            pst.setString(2, doctor.getLastName());
            pst.setString(3, doctor.getSpecialization());
            pst.setString(4, doctor.getContactNumber());
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        boolean success = false;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "UPDATE Doctor SET firstName = ?, lastName = ?, specialization = ?, contactNumber = ? WHERE doctorId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setString(1, doctor.getFirstName());
            pst.setString(2, doctor.getLastName());
            pst.setString(3, doctor.getSpecialization());
            pst.setString(4, doctor.getContactNumber());
            pst.setInt(5, doctor.getDoctorId());
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean deleteDoctor(int doctorId) {
        boolean success = false;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "DELETE FROM Doctor WHERE doctorId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, doctorId);
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
