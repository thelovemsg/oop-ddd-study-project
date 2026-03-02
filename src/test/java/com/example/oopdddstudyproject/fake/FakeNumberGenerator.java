package com.example.oopdddstudyproject.fake;

import com.example.oopdddstudyproject.common.service.NumberGenerator;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FakeNumberGenerator implements NumberGenerator {

    private final String couponNumber;

    @Override
    public String generate(Coupon coupon) {
        return couponNumber;
    }
}
