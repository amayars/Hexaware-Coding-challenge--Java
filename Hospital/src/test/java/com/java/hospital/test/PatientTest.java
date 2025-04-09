package com.java.hospital.test;

import com.java.hospital.model.Gender;
import com.java.hospital.model.Patient;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class PatientTest {

    private static Patient patient;
    private static Date dob;

    @BeforeClass
    public static void setup() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dob = sdf.parse("1995-03-10");

        patient = new Patient();
        patient.setPatientId(1);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth(dob);
        patient.setGender(Gender.MALE);
        patient.setContactNumber("1234567890");
        patient.setAddress("Mumbai");
    }

    @Test
    public void testGetters() {
        assertEquals(1, patient.getPatientId());
        assertEquals("John", patient.getFirstName());
        assertEquals("Doe", patient.getLastName());
        assertEquals(dob, patient.getDateOfBirth());
        assertEquals(Gender.MALE, patient.getGender());
        assertEquals("1234567890", patient.getContactNumber());
        assertEquals("Mumbai", patient.getAddress());
    }

    @Test
    public void testToString() {
        String str = patient.toString();
        assertTrue(str.contains("John"));
        assertTrue(str.contains("Doe"));
    }
}
