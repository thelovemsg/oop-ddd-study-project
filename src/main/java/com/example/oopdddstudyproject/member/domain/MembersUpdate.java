package com.example.oopdddstudyproject.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MembersUpdate {
    private final String name;
    private final String address;
    private final int age;

    @Builder
    public MembersUpdate(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
}
