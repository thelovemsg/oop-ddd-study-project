package com.example.oopdddstudyproject.coupon.domain;

import com.example.oopdddstudyproject.common.service.TimeGenerator;
import com.example.oopdddstudyproject.common.domain.Inventory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Coupon {

    private final Long id;
    private final String description;
    private final Inventory inventory;
    private final LocalDate expireDate;
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public Coupon(Long id, String description, Inventory inventory, LocalDate expireDate, Long createdAt, Long modifiedAt) {
        this.id = id;
        this.description = description;
        this.inventory = inventory;
        this.expireDate = expireDate;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Coupon from(CouponCreate couponCreate, TimeGenerator timeGenerator) {
        Inventory inventory = Inventory.builder()
                .availableCount(couponCreate.getAvailableCount())
                .usedCount(couponCreate.getUsedCount())
                .reservedCount(couponCreate.getReservedCount())
                .build();

        long millis = timeGenerator.millis();

        return Coupon.builder()
                .description(couponCreate.getDescription())
                .inventory(inventory)
                .expireDate(couponCreate.getExpireDate())
                .createdAt(millis)
                .modifiedAt(millis)
                .build();
    }

    public Coupon update(CouponUpdate couponUpdate, TimeGenerator timeGenerator) {
        isModifiable();

        Inventory inventory = Inventory.builder()
                .availableCount(couponUpdate.getAvailableCount())
                .usedCount(couponUpdate.getUsedCount())
                .reservedCount(couponUpdate.getReservedCount())
                .build();

        return Coupon.builder()
                .id(this.id)
                .description(couponUpdate.getDescription())
                .inventory(inventory)
                .createdAt(this.createdAt)
                .modifiedAt(timeGenerator.millis())
                .build();
    }

    private void isModifiable() {
        if (this.inventory.getReservedCount() > 0 || this.inventory.getUsedCount() > 0) {
            throw new IllegalStateException("이미 발급이 진행된 쿠폰은 수정할 수 없습니다.");
        }
    }

}
