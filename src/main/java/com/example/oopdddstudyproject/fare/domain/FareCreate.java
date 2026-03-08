package com.example.oopdddstudyproject.fare.domain;

import com.example.oopdddstudyproject.common.vo.Money;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FareCreate {
    private final String name;
    private final Money basePrice;

    @Builder
    public FareCreate(String name, Money basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }
}
