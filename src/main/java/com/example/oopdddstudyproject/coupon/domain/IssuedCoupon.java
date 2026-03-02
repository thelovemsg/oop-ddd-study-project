package com.example.oopdddstudyproject.coupon.domain;

import com.example.oopdddstudyproject.common.service.NumberGenerator;
import com.example.oopdddstudyproject.common.service.TimeGenerator;
import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.coupon.domain.vo.IssuedCouponStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class IssuedCoupon {

    private final Long id;
    private final Long couponId;
    private final Long memberId;
    private final String couponNumber;
    private final IssuedCouponStatus status;
    private final Long issuedAt;
    private final Money appliedPrice;  // 추가
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public IssuedCoupon(Long id, Long couponId, Long memberId, String couponNumber, IssuedCouponStatus status, Long issuedAt, Money appliedPrice, Long createdAt, Long modifiedAt) {
        this.id = id;
        this.couponId = couponId;
        this.memberId = memberId;
        this.couponNumber = couponNumber;
        this.status = status;
        this.issuedAt = issuedAt;
        this.appliedPrice = appliedPrice;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static IssuedCoupon issue(Coupon coupon, Long memberId, String couponNumber, Long issuedAt) {
        return IssuedCoupon.builder()
                .couponId(coupon.getId())
                .memberId(memberId)
                .couponNumber(couponNumber)
                .appliedPrice(coupon.getOriginalPrice())  // 추가
                .status(IssuedCouponStatus.UNUSED)
                .issuedAt(issuedAt)
                .build();
    }

    public IssuedCoupon use(long usingTime) {
        if (this.status != IssuedCouponStatus.UNUSED) {
            throw new IllegalStateException("사용 가능한 쿠폰이 아닙니다.");
        }

        return IssuedCoupon.builder()
                .id(this.id)
                .couponId(this.couponId)
                .memberId(this.memberId)
                .couponNumber(this.couponNumber)
                .appliedPrice(this.appliedPrice)
                .status(IssuedCouponStatus.USED)
                .issuedAt(this.issuedAt)
                .createdAt(this.createdAt)
                .modifiedAt(usingTime)
                .build();
    }

    public IssuedCoupon expire(long time) {
        if (this.status == IssuedCouponStatus.USED) {
            throw new IllegalStateException("이미 사용된 쿠폰은 만료할 수 없습니다.");
        }

        return IssuedCoupon.builder()
                .id(this.id)
                .couponId(this.couponId)
                .memberId(this.memberId)
                .appliedPrice(this.appliedPrice)
                .couponNumber(this.couponNumber)
                .status(IssuedCouponStatus.EXPIRED)
                .issuedAt(this.issuedAt)
                .createdAt(this.createdAt)
                .modifiedAt(time)
                .build();
    }
}