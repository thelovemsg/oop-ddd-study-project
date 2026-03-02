package com.example.oopdddstudyproject.coupon.domain;

import com.example.oopdddstudyproject.common.vo.Money;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CouponCreate {

    private final String description;
    private final LocalDate expireDate;
    private final Money originalPrice;
    private final int totalCount;
    private final int usedCount;

    @Builder
    public CouponCreate(String description, LocalDate expireDate, Money originalPrice, int totalCount, int usedCount) {
        this.description = description;
        this.expireDate = expireDate;
        this.originalPrice = originalPrice;
        this.totalCount = totalCount;
        this.usedCount = usedCount;
    }
}