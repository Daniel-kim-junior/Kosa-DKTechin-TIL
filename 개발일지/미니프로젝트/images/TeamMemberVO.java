package com.example.springedu.domain;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class TeamMemberVO {
    private String nicName;
    private String name;
    private String food;

    public TeamMemberVO(String nicName, String name, String food) {
        this.nicName = nicName;
        this.name = name;
        this.food = food;
    }
}
