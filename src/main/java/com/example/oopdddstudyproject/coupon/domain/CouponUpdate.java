package com.example.oopdddstudyproject.coupon.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CouponUpdate {
    private final Long id;
    private final String description;
    private final LocalDate expireDate;
    private final int usedCount;
    private final int totalCount;

    @Builder
    public CouponUpdate(Long id, String description, LocalDate expireDate, int usedCount, int totalCount) {
        this.id = id;
        this.description = description;
        this.expireDate = expireDate;
        this.usedCount = usedCount;
        this.totalCount = totalCount;
    }
}
