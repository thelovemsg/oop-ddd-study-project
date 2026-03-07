package com.example.oopdddstudyproject.fare.domain.calculation.strategies;

import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.fare.domain.calculation.FareCalculationContext;
import com.example.oopdddstudyproject.fare.domain.calculation.FarePolicyStrategy;
import com.example.oopdddstudyproject.fare.domain.policy.CalculationBasisEnum;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicyTypeEnum;

public class FixedAmountDiscountStrategy implements FarePolicyStrategy {

    @Override
    public FarePolicyTypeEnum getType() {
        return FarePolicyTypeEnum.FIXED_AMOUNT_DISCOUNT;
    }

    @Override
    public FareCalculationContext apply(FareCalculationContext context, FarePolicy policy) {
        // 단리/복리에 따라 기준 금액 결정
        Money baseAmount = context.getOriginalPrice();

        // policy.getValue()가 20이면 20% 할증
        Money newPrice = context.getCurrentPrice().subtract(baseAmount);

        return context.applyPolicy(newPrice,"고정 할인 : " + policy.getValue() + "할인!");
    }
}
