package com.example.oopdddstudyproject.fare.domain.calculation.strategies;

import com.example.oopdddstudyproject.common.vo.Money;
import com.example.oopdddstudyproject.fare.domain.Fare;
import com.example.oopdddstudyproject.fare.domain.FareStatusEnum;
import com.example.oopdddstudyproject.fare.domain.FareTypeEnum;
import com.example.oopdddstudyproject.fare.domain.calculation.FareCalculationContext;
import com.example.oopdddstudyproject.fare.domain.policy.CalculationBasisEnum;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicy;
import com.example.oopdddstudyproject.fare.domain.policy.FarePolicyTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendSurchargeStrategyTest {
    private WeekendSurchargeStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new WeekendSurchargeStrategy();
    }

    @Test
    @DisplayName("단리 기준으로 할증 금액을 계산한다")
    void 단리_할증_계산() {
        // given
        Fare fare = Fare.builder()
                .id(1L)
                .name("스탠다드 룸")
                .basePrice(Money.of(100000))
                .status(FareStatusEnum.ACTIVE)
                .fareType(FareTypeEnum.A_TYPE)
                .createdAt(1000L)
                .modifiedAt(1000L)
                .build();

        FareCalculationContext context = FareCalculationContext.init(fare);

        FarePolicy policy = FarePolicy.builder()
                .id(1L)
                .fareId(1L)
                .type(FarePolicyTypeEnum.WEEKEND_SURCHARGE)
                .value(new BigDecimal("20"))
                .basis(CalculationBasisEnum.ORIGINAL)
                .priority(1)
                .createdAt(1000L)
                .modifiedAt(1000L)
                .build();

        // when
        FareCalculationContext result = strategy.apply(context, policy);

        // then
        assertThat(result.getCurrentPrice()).isEqualTo(Money.of(120000));
        assertThat(result.getOriginalPrice()).isEqualTo(Money.of(100000));
    }
}