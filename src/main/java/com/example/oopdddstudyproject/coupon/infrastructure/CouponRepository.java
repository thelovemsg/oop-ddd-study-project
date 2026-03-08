package com.example.oopdddstudyproject.coupon.infrastructure;

import com.example.oopdddstudyproject.coupon.domain.Coupon;

public interface CouponRepository {
    Coupon findById(Long couponId);
    void save(Coupon coupon);
}
