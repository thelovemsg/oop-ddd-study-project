package com.example.oopdddstudyproject.common.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Money {

    private final int amount;

    private Money(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0원 이상이어야 합니다.");
        }
        this.amount = amount;
    }

    public static Money of(int amount) {
        return new Money(amount);
    }

    public static Money zero() {
        return new Money(0);
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        if (this.amount < other.amount) {
            throw new IllegalArgumentException("차감 후 금액은 0원 이상이어야 합니다.");
        }
        return new Money(this.amount - other.amount);
    }

    public Money discountByRate(double rate) {
        if (rate < 0 || rate > 1) {
            throw new IllegalArgumentException("할인율은 0~1 사이여야 합니다.");
        }
        return new Money((int) (this.amount * (1 - rate)));
    }
}