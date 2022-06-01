package com.nhs.clinicApi.doctor;

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
public class Doctor {
    @Id
    private Integer id_doctor;
    private String lastName;
    private String name;
    private String specjalty;
    private String dateOfBirth;
    private String nip;
    private String pesel;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

}
