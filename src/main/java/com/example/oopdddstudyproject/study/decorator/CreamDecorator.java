package com.example.oopdddstudyproject.study.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreamDecorator extends CakeDecorator{

    public CreamDecorator(Cake cake) {
        super(cake);
    }

    @Override
    public String makeCake() {
        return super.makeCake() + " + ☁️부드러운 생크림";
    }
}
