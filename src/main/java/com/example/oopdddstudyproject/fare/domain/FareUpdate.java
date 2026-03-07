package com.example.oopdddstudyproject.fare.domain;

import com.example.oopdddstudyproject.common.vo.Money;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FareUpdate {
    private final Long id;
    private final String name;
    private final Money basePrice;

    @Builder
    public FareUpdate(Long id, String name, Money basePrice) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
    }
}
