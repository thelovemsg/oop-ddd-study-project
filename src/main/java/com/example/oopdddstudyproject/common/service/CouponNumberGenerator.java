package com.example.oopdddstudyproject.common.service;

import com.example.oopdddstudyproject.coupon.domain.Coupon;

public interface CouponNumberGenerator {
    String generate(Coupon coupon);
}
