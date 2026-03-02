package com.example.oopdddstudyproject.member.domain;

import com.example.oopdddstudyproject.common.service.TimeGenerator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Members {

    private final Long id;
    private final String name;
    private final String address;
    private final int age;
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public Members(Long id, String name, String address, int age, Long createdAt, Long modifiedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Members create(MembersCreate membersCreate, Long time) {

        return Members.builder()
                .name(membersCreate.getName())
                .address(membersCreate.getAddress())
                .age(membersCreate.getAge())
                .createdAt(time)
                .modifiedAt(time)
                .build();
    }

    public Members updateMember(MembersUpdate membersUpdate, Long time) {

        return Members.builder()
                .id(this.id)
                .name(membersUpdate.getName())
                .address(membersUpdate.getAddress())
                .createdAt(this.createdAt)
                .modifiedAt(time)
                .build();
    }

}
