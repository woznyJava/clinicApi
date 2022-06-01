package com.nhs.clinicApi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class RootController {
    @GetMapping
    public String getRoot() {
        return "home";
    }
}
