package com.example.oopdddstudyproject.study.decorator;

public class FruitDecorator extends CakeDecorator {
    public FruitDecorator(Cake cake) {
        super(cake);
    }

    @Override
    public String makeCake() {
        return super.makeCake() + " + 🍓싱싱한 제철 과일";
    }
}
