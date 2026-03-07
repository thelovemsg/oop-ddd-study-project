package com.example.oopdddstudyproject.fare.domain.policy;

public enum CalculationBasis {
    ORIGINAL,    // 단리: 항상 Fare.basePrice 기준으로 계산
    ACCUMULATED  // 복리: 직전 정책 적용 후 금액 기준으로 계산
}