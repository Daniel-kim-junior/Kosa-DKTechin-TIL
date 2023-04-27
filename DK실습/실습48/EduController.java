package com.example.springedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EduController {

    @PostMapping("/educontroller")
    public String postMap(String name,int avgScore, Model model) {
        model.addAttribute("name", name);

        if(avgScore >= 90) return "gradeA";
        if(avgScore >= 80 && avgScore <= 89) return "gradeB";
        if(avgScore >= 70 && avgScore <= 79) return "gradeC";
        return "gradeD";
    }
}
