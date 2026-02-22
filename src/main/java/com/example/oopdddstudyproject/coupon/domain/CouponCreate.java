package com.example.oopdddstudyproject.coupon.domain;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CouponCreate {

    private final String description;
    private final LocalDate expireDate;
    private final int totalCount;
    private final int availableCount;
    private final int usedCount;
    private final int reservedCount;

    @Builder
    public CouponCreate(String description, LocalDate expireDate, int totalCount, int availableCount, int usedCount, int reservedCount) {
        this.description = description;
        this.expireDate = expireDate;
        this.totalCount = totalCount;
        this.availableCount = availableCount;
        this.usedCount = usedCount;
        this.reservedCount = reservedCount;
    }
}