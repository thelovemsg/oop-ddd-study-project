package com.example.oopdddstudyproject.coupon.domain;

import com.example.oopdddstudyproject.common.service.TimeGenerator;
import com.example.oopdddstudyproject.common.vo.Inventory;
import com.example.oopdddstudyproject.common.vo.Money;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Coupon {

    private final Long id;
    private final String description;
    private final LocalDate expireDate;
    private final Inventory inventory;
    private final Money originalPrice;
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public Coupon(Long id, String description, LocalDate expireDate, Inventory inventory, Money originalPrice, Long createdAt, Long modifiedAt) {
        this.id = id;
        this.description = description;
        this.expireDate = expireDate;
        this.inventory = inventory;
        this.originalPrice = originalPrice;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Coupon from(CouponCreate couponCreate, long currentTime) {
        Inventory inventory = Inventory.createInitial(couponCreate.getTotalCount());

        return Coupon.builder()
                .description(couponCreate.getDescription())
                .inventory(inventory)
                .originalPrice(couponCreate.getOriginalPrice())  // 추가
                .expireDate(couponCreate.getExpireDate())
                .createdAt(currentTime)
                .modifiedAt(currentTime)
                .build();
    }

    public Coupon updateCouponInfo(CouponUpdate couponUpdate, long currentTime) {
        return Coupon.builder()
                .id(this.id)
                .description(couponUpdate.getDescription())
                .inventory(this.inventory)
                .createdAt(this.createdAt)
                .expireDate(couponUpdate.getExpireDate())
                .modifiedAt(currentTime)
                .build();
    }

    public Coupon updateInventoryInfo(CouponUpdate couponUpdate, long currentTime) {

        Inventory inventory = Inventory.builder()
                .usedCount(couponUpdate.getUsedCount())
                .totalCount(couponUpdate.getTotalCount())
                .build();

        return Coupon.builder()
                .id(this.id)
                .description(this.description)
                .inventory(inventory)
                .expireDate(this.expireDate)
                .createdAt(this.createdAt)
                .modifiedAt(currentTime)
                .build();
    }

    public Coupon reserve(long currentTime) {
        if (this.expireDate.isBefore(LocalDate.now())) {
            throw new IllegalStateException("만료된 쿠폰입니다.");
        }

        Inventory usedInventory = this.inventory.use();

        return Coupon.builder()
                .id(this.id)
                .description(this.description)
                .inventory(usedInventory)
                .expireDate(this.expireDate)
                .originalPrice(this.originalPrice)
                .createdAt(this.createdAt)
                .modifiedAt(currentTime)
                .build();
    }

}
