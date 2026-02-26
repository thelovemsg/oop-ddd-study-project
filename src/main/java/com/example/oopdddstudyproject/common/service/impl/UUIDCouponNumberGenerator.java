package com.example.oopdddstudyproject.common.service.impl;

import com.example.oopdddstudyproject.common.service.CouponNumberGenerator;
import com.example.oopdddstudyproject.coupon.domain.Coupon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UUIDCouponNumberGenerator implements CouponNumberGenerator {

    private static final DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String generate(Coupon coupon) {
        String date = LocalDate.now().format(dateTimeFormatter);
        String couponId = String.format("%06d", coupon.getId());
        String random = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        return date + "-" + couponId + "-" + random;
    }
}