package com.example.oopdddstudyproject.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Members {

    private final Long id;
    private final String name;
    private final String address;
    private final int age;

    @Builder
    public Members(Long id, String name, String address, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
    }


}
