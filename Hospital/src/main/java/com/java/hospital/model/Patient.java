package com.java.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String contactNumber;
    private String address;
}
