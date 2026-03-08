package com.example.oopdddstudyproject.fare.domain.service;

import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.fare.domain.Fare;
import com.example.oopdddstudyproject.fare.domain.calculation.FareCalculationContext;
import com.example.oopdddstudyproject.fare.domain.calculation.FareCalculationPipeline;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;

import java.util.List;

public class FareCalculationDomainService {
    private final FareCalculationPipeline pipeline;

    public FareCalculationDomainService(FareCalculationPipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Money calculateFinalPrice(Fare fare, List<FarePolicy> policies) {
        FareCalculationContext result = pipeline.calculate(fare, policies);
        return result.getCurrentPrice();
    }
}
