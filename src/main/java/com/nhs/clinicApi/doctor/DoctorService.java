package com.nhs.clinicApi.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.persistence.EntityExistsException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findById(int id) {
        return doctorRepository.findById(id).orElseThrow(EntityExistsException::new);
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
                new FileReader("lekarze.txt"));

        reader.readLine(); // NOTE: Skip header
        String line;

        while ((line = reader.readLine()) != null) {
            var data = line.split("\t");

            try {
                Integer id = Integer.parseInt(data[0]);
                String lastname = data[1];
                String name = data[2];
                String speciality = data[3];
                String birthday = data[4];
                String nip = data[5];
                String pesel = data[6];

                doctorRepository.save(
                        new Doctor(
                                id,
                                lastname,
                                name,
                                speciality,
                                birthday,
                                nip,
                                pesel,
                                Collections.emptySet()));
            } catch (Exception e) {
                continue;
            }
        }
        System.out.println("Finished populating `doctor` table");

    }


}
