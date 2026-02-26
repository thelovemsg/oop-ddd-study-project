package com.example.oopdddstudyproject.member.domain;

import com.example.oopdddstudyproject.common.domain.Inventory;
import com.example.oopdddstudyproject.common.service.TimeGenerator;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import com.example.oopdddstudyproject.coupon.domain.CouponCreate;
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

    public static Members from(MembersCreate membersCreate, TimeGenerator timeGenerator) {
        long millis = timeGenerator.millis();

        return Members.builder()
                .name(membersCreate.getName())
                .address(membersCreate.getAddress())
                .age(membersCreate.getAge())
                .createdAt(millis)
                .modifiedAt(millis)
                .build();
    }

    public Members updateMember(MembersUpdate membersUpdate, TimeGenerator timeGenerator) {
        long millis = timeGenerator.millis();

        return Members.builder()
                .id(this.id)
                .name(membersUpdate.getName())
                .address(membersUpdate.getAddress())
                .createdAt(this.createdAt)
                .modifiedAt(millis)
                .build();
    }

}
