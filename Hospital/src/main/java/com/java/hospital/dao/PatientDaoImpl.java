package com.java.hospital.dao;

import com.java.hospital.model.Patient;
import com.java.hospital.myexceptions.PatientNumberNotFoundException;
import com.java.hospital.model.Gender;
import com.java.hospital.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public Patient getPatientById(int patientId) throws PatientNumberNotFoundException {
        Patient patient = null;
        try {
            connection = ConnectionHelper.getConnection();
            String query = "SELECT * FROM Patient WHERE patientId = ?";
            pst = connection.prepareStatement(query);
            pst.setInt(1, patientId);
            rs = pst.executeQuery();
            if (rs.next()) {
                patient = new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setFirstName(rs.getString("firstName"));
                patient.setLastName(rs.getString("lastName"));
                patient.setDateOfBirth(rs.getDate("dateOfBirth"));
                patient.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
                patient.setContactNumber(rs.getString("contactNumber"));
                patient.setAddress(rs.getString("address"));
            } else {
                throw new PatientNumberNotFoundException("Patient with ID " + patientId + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
        	connection = ConnectionHelper.getConnection();
            String cmd = "SELECT * FROM Patient";
            pst = connection.prepareStatement(cmd);
            rs = pst.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setFirstName(rs.getString("firstName"));
                patient.setLastName(rs.getString("lastName"));
                patient.setDateOfBirth(rs.getDate("dateOfBirth"));
                patient.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
                patient.setContactNumber(rs.getString("contactNumber"));
                patient.setAddress(rs.getString("address"));
                patients.add(patient);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return patients;
    }

    @Override
    public boolean addPatient(Patient patient) {
        boolean success = false;
        try {
        	connection = ConnectionHelper.getConnection();
            String cmd = "INSERT INTO Patient (firstName, lastName, dateOfBirth, gender, contactNumber, address) VALUES (?, ?, ?, ?, ?, ?)";
            pst = connection.prepareStatement(cmd);
            pst.setString(1, patient.getFirstName());
            pst.setString(2, patient.getLastName());
            pst.setDate(3, new java.sql.Date(patient.getDateOfBirth().getTime()));
            pst.setString(4, patient.getGender().toString());
            pst.setString(5, patient.getContactNumber());
            pst.setString(6, patient.getAddress());
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        boolean success = false;
        try {
        	connection = ConnectionHelper.getConnection();
            String cmd = "UPDATE Patient SET firstName = ?, lastName = ?, dateOfBirth = ?, gender = ?, contactNumber = ?, address = ? WHERE patientId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setString(1, patient.getFirstName());
            pst.setString(2, patient.getLastName());
            pst.setDate(3, new java.sql.Date(patient.getDateOfBirth().getTime()));
            pst.setString(4, patient.getGender().toString());
            pst.setString(5, patient.getContactNumber());
            pst.setString(6, patient.getAddress());
            pst.setInt(7, patient.getPatientId());
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean deletePatient(int patientId) {
        boolean success = false;
        try {
        	connection = ConnectionHelper.getConnection();
            String cmd = "DELETE FROM Patient WHERE patientId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, patientId);
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
