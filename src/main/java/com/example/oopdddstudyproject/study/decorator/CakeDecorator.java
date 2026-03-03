package com.example.oopdddstudyproject.study.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CakeDecorator implements Cake{
    private Cake wrappedCake;

    public CakeDecorator(Cake cake) {
        this.wrappedCake = cake;
    }

    @Override
    public String makeCake() {
        return wrappedCake.makeCake();    }
}
