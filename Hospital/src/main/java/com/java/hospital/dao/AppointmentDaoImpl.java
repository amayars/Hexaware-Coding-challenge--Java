package com.java.hospital.dao;

import com.java.hospital.model.Appointment;
import com.java.hospital.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        Appointment appointment = null;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "SELECT * FROM Appointment WHERE appointmentId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, appointmentId);
            rs = pst.executeQuery();
            if (rs.next()) {
                appointment = mapResultSetToAppointment(rs);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "SELECT * FROM Appointment WHERE patientId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, patientId);
            rs = pst.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "SELECT * FROM Appointment WHERE doctorId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, doctorId);
            rs = pst.executeQuery();
            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        boolean success = false;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, appointment.getPatientId());
            pst.setInt(2, appointment.getDoctorId());
            pst.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            pst.setString(4, appointment.getDescription());
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        boolean success = false;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, appointment.getPatientId());
            pst.setInt(2, appointment.getDoctorId());
            pst.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            pst.setString(4, appointment.getDescription());
            pst.setInt(5, appointment.getAppointmentId());
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        boolean success = false;
        try {
            connection = ConnectionHelper.getConnection();
            String cmd = "DELETE FROM Appointment WHERE appointmentId = ?";
            pst = connection.prepareStatement(cmd);
            pst.setInt(1, appointmentId);
            success = pst.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    private Appointment mapResultSetToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(rs.getInt("appointmentId"));
        appointment.setPatientId(rs.getInt("patientId"));
        appointment.setDoctorId(rs.getInt("doctorId"));
        appointment.setAppointmentDate(rs.getDate("appointmentDate"));
        appointment.setDescription(rs.getString("description"));
        return appointment;
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
