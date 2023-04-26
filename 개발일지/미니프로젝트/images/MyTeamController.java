package com.example.springedu.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springedu.domain.TeamDTO;
import com.example.springedu.domain.TeamMemberVO;

@Controller
public class MyTeamController {

    @GetMapping("/teamName")
    @ResponseBody
    public TeamDTO getTeam() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName("T-KO");
        return teamDTO;
    }

    @GetMapping("/teamPerson")
    @ResponseBody
    public TeamDTO getTeamPersons() {
        List<TeamMemberVO> list = new ArrayList<>();
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamMember(list);
        teamDTO.setTeamName("T-KO");
        list.add(new TeamMemberVO("삑사리", "홍승희", "돈까스"));
        list.add(new TeamMemberVO("박주",  "박주희", "라면"));
        list.add(new TeamMemberVO("Danny", "김대엽", "치킨"));
        list.add(new TeamMemberVO("도라이", "김민성", "칼국수"));
        return teamDTO;
    }
}
