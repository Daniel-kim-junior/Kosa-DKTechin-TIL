package com.example.springedu.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.springedu.domain.LottoDTO;

@Controller
@SessionAttributes("try")
public class LottoController {

    @GetMapping("/lotto")
    public String getLottoResult(int lottoNum, Model model) {
        int ran = (int)(Math.random() * 6) + 1;
        Object obj = model.getAttribute("try");
        if(obj == null) {
            model.addAttribute("try", 0);
        }
        int n = (int) model.getAttribute("try");
        LottoDTO lottoDTO;

        if(n >= 3) {
            lottoDTO = new LottoDTO();
            lottoDTO.setResult("<hr>로또 응모는 낙점된 경우에 한하여 3번 까지만 가능합니다. 브라우저를 재기동 후에 응모해 주세요");
            lottoDTO.setImgName("images/snow.png");
            model.addAttribute("LottoDTO", lottoDTO);
            return "lottoView";
        }
        model.addAttribute("try", n + 1);
        lottoDTO = new LottoDTO();
        if(ran == lottoNum) {
            lottoDTO.setResult("<hr>추카추카!!!");
            lottoDTO.setImgName("images/sun.png");
        } else {
            lottoDTO.setResult("<hr>아쉽네 .. 다음 기회를!!");
            lottoDTO.setImgName("images/rain.png");
        }
        model.addAttribute("LottoDTO", lottoDTO);
        return "lottoView";
    }

}
