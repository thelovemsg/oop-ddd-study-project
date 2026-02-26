package com.example.oopdddstudyproject.fake;

import com.example.oopdddstudyproject.common.service.CouponNumberGenerator;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FakeCouponNumberGenerator implements CouponNumberGenerator {

    private final String couponNumber;

    @Override
    public String generate(Coupon coupon) {
        return couponNumber;
    }
}
