package com.example.oopdddstudyproject.coupon.domain;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CouponCreate {

    private final String description;
    private final LocalDate expireDate;
    private final int totalCount;
    private final int usedCount;

    @Builder
    public CouponCreate(String description, LocalDate expireDate, int totalCount, int usedCount) {
        this.description = description;
        this.expireDate = expireDate;
        this.totalCount = totalCount;
        this.usedCount = usedCount;
    }
}