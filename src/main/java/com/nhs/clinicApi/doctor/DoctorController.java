package com.nhs.clinicApi.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        return "doctor/list";
    }
    @GetMapping("/create")
    public String getCreateDoctorForm(Model model){
        model.addAttribute("doctors", doctorService.findAll());
        return "doctor/create";
    }

    @PostMapping("/create")
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctor.setId_doctor((new Random()).nextInt(1000) +  + 1000);
        doctorService.save(doctor);
        return "redirect:/doctors";
    }
}
