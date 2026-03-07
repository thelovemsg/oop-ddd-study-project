package com.example.oopdddstudyproject.fare.domain.policy;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FarePolicy {

    private final Long id;
    private final Long fareId;                // 어떤 Fare에 소속된 정책인지
    private final FarePolicyType type;        // 정책 종류
    private final BigDecimal value;           // 적용 값 (20이면 20%)
    private final CalculationBasis basis;     // 단리/복리
    private final int priority;               // 적용 순서 (낮을수록 먼저)
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public FarePolicy(Long id, Long fareId, FarePolicyType type, BigDecimal value,
                      CalculationBasis basis, int priority, Long createdAt, Long modifiedAt) {
        this.id = id;
        this.fareId = fareId;
        this.type = type;
        this.value = value;
        this.basis = basis;
        this.priority = priority;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}