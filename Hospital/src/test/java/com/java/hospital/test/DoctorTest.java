package com.java.hospital.test;

import com.java.hospital.model.Doctor;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoctorTest {

    private static Doctor doctor;

    @BeforeClass
    public static void setup() {
        doctor = new Doctor();
        doctor.setDoctorId(101);
        doctor.setFirstName("Alice");
        doctor.setLastName("Smith");
        doctor.setSpecialization("Neurology");
        doctor.setContactNumber("9876543210");
    }

    @Test
    public void testGetters() {
        assertEquals(101, doctor.getDoctorId());
        assertEquals("Alice", doctor.getFirstName());
        assertEquals("Smith", doctor.getLastName());
        assertEquals("Neurology", doctor.getSpecialization());
        assertEquals("9876543210", doctor.getContactNumber());
    }

    @Test
    public void testToString() {
        assertTrue(doctor.toString().contains("Neurology"));
    }
}
