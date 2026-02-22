package com.example.oopdddstudyproject.coupon.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CouponUpdate {
    private final Long id;
    private final String description;
    private final LocalDate expireDate;
    private final int totalCount;
    private final int availableCount;
    private final int usedCount;
    private final int reservedCount;

    @Builder
    public CouponUpdate(Long id, String description, LocalDate expireDate, int totalCount, int availableCount, int usedCount, int reservedCount) {
        this.id = id;
        this.description = description;
        this.expireDate = expireDate;
        this.totalCount = totalCount;
        this.availableCount = availableCount;
        this.usedCount = usedCount;
        this.reservedCount = reservedCount;
    }
}
