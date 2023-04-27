package com.example.springedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/study")
public class StudyPathController {

    @GetMapping(value = "/{num}/thymeleaf")
    public RedirectView getRequest(@PathVariable int num) {
        RedirectView redirectView;
        if(num == 1) {
            redirectView = new RedirectView();
            redirectView.setUrl("https://abbo.tistory.com/56");
            return redirectView;
        }
        if(num == 2) {
            redirectView = new RedirectView();
            redirectView.setUrl("https://abbo.tistory.com/57");
            return redirectView;
        }
        if(num == 3) {
            redirectView = new RedirectView();
            redirectView.setUrl("https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html");
            return redirectView;
        }
        if(num == 4) {
            redirectView = new RedirectView();
            redirectView.setUrl("https://www.baeldung.com/dates-in-thymeleaf");
            return redirectView;
        }

        return null;
    }
}
