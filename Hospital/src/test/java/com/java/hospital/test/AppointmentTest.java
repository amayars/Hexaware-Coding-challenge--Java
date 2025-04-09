package com.java.hospital.test;

import com.java.hospital.model.Appointment;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class AppointmentTest {

    private static Appointment appointment;
    private static Date appointmentDate;

    @BeforeClass
    public static void setup() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        appointmentDate = sdf.parse("2025-06-15");

        appointment = new Appointment();
        appointment.setAppointmentId(500);
        appointment.setPatientId(5);
        appointment.setDoctorId(1);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDescription("Routine Checkup");
    }

    @Test
    public void testGetters() {
        assertEquals(500, appointment.getAppointmentId());
        assertEquals(5, appointment.getPatientId());
        assertEquals(1, appointment.getDoctorId());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals("Routine Checkup", appointment.getDescription());
    }

    @Test
    public void testToString() {
        assertTrue(appointment.toString().contains("Checkup"));
    }
}
