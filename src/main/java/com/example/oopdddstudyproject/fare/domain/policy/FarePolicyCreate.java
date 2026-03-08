package com.example.oopdddstudyproject.fare.domain.policy;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FarePolicyCreate {

    private final FarePolicyTypeEnum type;
    private final BigDecimal value;
    private final CalculationBasisEnum basis;
    private final int priority;

    @Builder
    public FarePolicyCreate(FarePolicyTypeEnum type, BigDecimal value, CalculationBasisEnum basis, int priority) {
        this.type = type;
        this.value = value;
        this.basis = basis;
        this.priority = priority;
    }
}
