package com.example.oopdddstudyproject.fare.domain.calculation;

import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicyType;

public interface FarePolicyStrategy {

    // 이 전략이 어떤 타입을 처리하는지
    FarePolicyType getType();

    // 정책 적용
    FareCalculationContext apply(FareCalculationContext context, FarePolicy policy);
}