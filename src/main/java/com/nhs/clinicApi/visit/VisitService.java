package com.nhs.clinicApi.visit;

import com.nhs.clinicApi.doctor.Doctor;
import com.nhs.clinicApi.doctor.DoctorService;
import com.nhs.clinicApi.patient.Patient;
import com.nhs.clinicApi.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public List<Visit> findAll() {
        return (List<Visit>) visitRepository.findAll();
    }

    public void save(Visit visit, int id_patient, int id_doctor){
        Patient patient = patientService.findById(id_patient);
        visit.setPatient(patient);
        Doctor doctor = doctorService.findById(id_doctor);
        visit.setDoctor(doctor);
        visitRepository.save(visit);
    }
    public Visit findById(int id) {
        return visitRepository.findById(id).orElseThrow(EntityExistsException::new);
    }


    @PostConstruct
    public void init() {
        try {
            this.readData();
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public void readData() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader("wizyty.txt", StandardCharsets.UTF_8)
        );

        reader.readLine(); // NOTE: Skip header
        String line;

        var formatter = DateTimeFormatter.ofPattern("yyyy-M-d");

        while ((line = reader.readLine()) != null) {

            var data = line.split("\t");

            try {
                Integer doctor = Integer.parseInt(data[0]);
                Integer patient = Integer.parseInt(data[1]);
                String date = data[2];

                //LocalDate.parse(date, formatter);

                visitRepository.save(
                        new Visit(
                                doctorService.findById(doctor),
                                patientService.findById(patient),
                                LocalDateTime.now() // TODO: Read from text
                        )
                );
            } catch (Exception e) {
                System.err.println(e);

                continue;
            }
        }

        System.out.println("Finished populating `visit` table");

    }
}

