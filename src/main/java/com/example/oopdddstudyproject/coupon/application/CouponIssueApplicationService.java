package com.example.oopdddstudyproject.coupon.application;


import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import com.example.oopdddstudyproject.coupon.domain.IssuedCoupon;
import com.example.oopdddstudyproject.coupon.domain.service.CouponIssueDomainService;
import com.example.oopdddstudyproject.coupon.infrastructure.CouponRepository;
import com.example.oopdddstudyproject.fare.domain.Fare;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.infrastructure.FarePolicyRepository;
import com.example.oopdddstudyproject.fare.infrastructure.FareRepository;
import com.example.oopdddstudyproject.fare.domain.service.FareCalculationDomainService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CouponIssueApplicationService {

    private final FareRepository fareRepository;
    private final FarePolicyRepository farePolicyRepository;
    private final CouponRepository couponRepository;
    private final FareCalculationDomainService fareCalculationDomainService;
    private final CouponIssueDomainService couponIssueDomainService;

    public IssuedCoupon issueCoupon(Long fareId, Long couponId, Long memberId) {
        // 인프라에서 꺼내고
        Fare fare = fareRepository.findById(fareId);
        List<FarePolicy> policies = farePolicyRepository.findByFareId(fareId);
        Coupon coupon = couponRepository.findById(couponId);

        // 도메인한테 시키고
        Money finalPrice = fareCalculationDomainService.calculateFinalPrice(fare, policies);

        // 도메인한테 시키고
        return couponIssueDomainService.issueToMember(coupon, memberId, finalPrice);
    }
}