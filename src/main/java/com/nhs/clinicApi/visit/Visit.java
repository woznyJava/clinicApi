package com.nhs.clinicApi.visit;

import com.nhs.clinicApi.doctor.Doctor;
import com.nhs.clinicApi.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_visit;
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;

    public Visit(Doctor doctor, Patient patient, LocalDateTime date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }
}
