package com.nhs.clinicApi.patient;

import com.nhs.clinicApi.visit.Visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Patient {
    @Id
    private int id_patient;
    private String lastName;
    private String name;
    private String pesel;
    private String dateOfBirth;

    @OneToMany(mappedBy = "patient")
    private Set<Visit> visits;

    public Patient(int id_patient, String lastName, String name, String pesel, String dateOfBirth) {
        this.id_patient = id_patient;
        this.lastName = lastName;
        this.name = name;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
    }
}
