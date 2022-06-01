package com.nhs.clinicApi.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor

public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public String getAllPatients(Model model){
        model.addAttribute("patients", patientService.findAll());
        return "patient/list";
    }
    @GetMapping("/create")
    public String getCreatePatientForm(Model model){
        model.addAttribute("patients", patientService.findAll());
        return "patient/create";
    }
    @PostMapping("/create")
    public String createPatient(@ModelAttribute Patient patient){
        patient.setId_patient((new Random()).nextInt(1000) +  + 1000);
        patientService.save(patient);
        return "redirect:/patients";
    }
}
