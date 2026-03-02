package com.example.oopdddstudyproject.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MembersCreate {
    private final String name;
    private final String address;
    private final int age;

    @Builder
    public MembersCreate(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
