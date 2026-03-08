package com.example.oopdddstudyproject.fare.domain.calculation.strategies;

import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.fare.domain.calculation.FareCalculationContext;
import com.example.oopdddstudyproject.fare.domain.calculation.FarePolicyStrategy;
import com.example.oopdddstudyproject.fare.domain.policy.CalculationBasisEnum;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicyTypeEnum;

public class LongStayDiscountStrategy implements FarePolicyStrategy {

    @Override
    public FarePolicyTypeEnum getType() {
        return FarePolicyTypeEnum.LONG_STAY_DISCOUNT;
    }

    @Override
    public FareCalculationContext apply(FareCalculationContext context, FarePolicy policy) {
        Money baseAmount = (policy.getBasis() == CalculationBasisEnum.ORIGINAL)
                ? context.getOriginalPrice()
                : context.getCurrentPrice();

        Money discount = baseAmount.multiplyPercent(policy.getValue());
        Money newPrice = context.getCurrentPrice().subtract(discount);

        return context.applyPolicy(newPrice,
                "장기 투숙 할인 " + policy.getValue() + "% 적용");
    }
}