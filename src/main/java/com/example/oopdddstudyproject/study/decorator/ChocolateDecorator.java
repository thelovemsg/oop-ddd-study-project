package com.example.oopdddstudyproject.study.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChocolateDecorator extends CakeDecorator{

    public ChocolateDecorator(Cake cake) {
        super(cake);
    }

    @Override
    public String makeCake() {
        return super.makeCake() + " + 꾸덕한 초콜릿 코팅";
    }
}
