package com.example.oopdddstudyproject.fake.repository;

import com.example.oopdddstudyproject.coupon.domain.Coupon;
import com.example.oopdddstudyproject.coupon.infrastructure.CouponRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeCouponRepository implements CouponRepository {
    private final Map<Long, Coupon> store = new HashMap<>();

    public void save(Coupon coupon) {
        store.put(coupon.getId(), coupon);
    }

    @Override
    public Coupon findById(Long id) {
        return store.get(id);
    }
}