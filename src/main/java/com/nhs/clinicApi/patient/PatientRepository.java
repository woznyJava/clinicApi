package com.nhs.clinicApi.patient;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
