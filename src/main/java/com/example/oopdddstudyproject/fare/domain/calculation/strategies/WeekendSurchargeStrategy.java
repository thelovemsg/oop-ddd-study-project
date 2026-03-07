package com.example.oopdddstudyproject.fare.domain.calculation.strategies;

import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.fare.domain.calculation.FareCalculationContext;
import com.example.oopdddstudyproject.fare.domain.calculation.FarePolicyStrategy;
import com.example.oopdddstudyproject.fare.domain.policy.CalculationBasisEnum;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicyTypeEnum;

public class WeekendSurchargeStrategy implements FarePolicyStrategy {

    @Override
    public FarePolicyTypeEnum getType() {
        return FarePolicyTypeEnum.WEEKEND_SURCHARGE;
    }

    @Override
    public FareCalculationContext apply(FareCalculationContext context, FarePolicy policy) {
        // 단리/복리에 따라 기준 금액 결정
        Money baseAmount = (policy.getBasis() == CalculationBasisEnum.ORIGINAL)
                ? context.getOriginalPrice()
                : context.getCurrentPrice();

        // policy.getValue()가 20이면 20% 할증
        Money surcharge = baseAmount.multiplyPercent(policy.getValue());
        Money newPrice = context.getCurrentPrice().add(surcharge);

        return context.applyPolicy(newPrice,
                "주말 할증 " + policy.getValue() + "% 적용");
    }
}