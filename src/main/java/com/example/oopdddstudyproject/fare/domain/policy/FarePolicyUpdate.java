package com.example.oopdddstudyproject.fare.domain.policy;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FarePolicyUpdate {

    private final BigDecimal value;
    private final CalculationBasisEnum basis;
    private final int priority;

    @Builder
    public FarePolicyUpdate(BigDecimal value, CalculationBasisEnum basis, int priority) {
        this.value = value;
        this.basis = basis;
        this.priority = priority;
    }
}