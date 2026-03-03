package com.example.oopdddstudyproject.study.chainofresponsibility;

class PressHandler extends ProcessHandler {
    @Override
    protected void process(Car car) {
        System.out.println("--- 1단계: 프레스 공정 ---");
        car.addLog("철판 평탄화 및 절단 완료");
    }
}