package com.example.springedu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController {

    @PostMapping("/calc")
    public String postResult(int firstNum, int secondNum, String operator, Model model) {
        if(operator.equals("plus")) model.addAttribute("result", firstNum + secondNum);
        if(operator.equals("minus")) model.addAttribute("result", firstNum - secondNum);
        if(operator.equals("multiply")) model.addAttribute("result", firstNum * secondNum);
        if(operator.equals("divide")) {
            if(secondNum == 0) {
                model.addAttribute("result", "나눗셈 연산시 두 번째 숫자는 0일 수 없습니다!!");
                return "errorResult";
            }
            model.addAttribute("result", firstNum / secondNum);
        }
        return "calcResult";
    }
}
