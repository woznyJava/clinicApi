package com.nhs.clinicApi.visit;

import com.nhs.clinicApi.doctor.DoctorService;
import com.nhs.clinicApi.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/visits")
@RequiredArgsConstructor

public class VisitController {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final VisitService visitService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping
    public String getAllVisits(Model model) {
        model.addAttribute("visits", visitService.findAll());
        return "visit/list";
    }

    @GetMapping("/create")
    public String getCreateVisitForm(Model model) {
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("doctors", doctorService.findAll());
        return "visit/create";
    }

    @PostMapping("/create")
    public String createVisit(Visit visit, @RequestParam("id_patient") int id_patient,
                              @RequestParam("id_doctor") int id_doctor){
        visitService.save(visit, id_patient, id_doctor);
        return "redirect:/visits";
    }

    @PostMapping("/details")
    public String getVisitDetails(Model model, @RequestParam("id_visit") int id_visit) {
        var visit = visitService.findById(id_visit);

        model.addAttribute("patient", visit.getPatient());
        model.addAttribute("doctor", visit.getDoctor());

        return "visit/details";
    }
}
