package com.nhs.clinicApi.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientService {

    private  final PatientRepository patientRepository;

    public List<Patient> findAll(){
        return (List<Patient>) patientRepository.findAll();
    }
    public void save(Patient patient){
        patientRepository.save(patient);
    }
    public Patient findById(int id) {
        return patientRepository.findById(id).orElseThrow(EntityExistsException::new);
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
                new FileReader("pacjenci.txt", StandardCharsets.UTF_8)
        );

        reader.readLine(); // NOTE: Skip header
        String line;

        while ((line = reader.readLine()) != null) {

            var data = line.split("\t");

            try {
                Integer id = Integer.parseInt(data[0]);
                String lastname = data[1];
                String name = data[2];
                String pesel = data[3];
                String dateOfBirth = data[4];

                var patient = new Patient(
                        id,
                        lastname,
                        name,
                        pesel,
                        dateOfBirth
                );

                patientRepository.save(
                        new Patient(
                                id,
                                lastname,
                                name,
                                pesel,
                                dateOfBirth));
            } catch (Exception e) {
                System.err.println(e);

                continue;
            }
        }

        System.out.println("Finished populating `patient` table");

    }
}
