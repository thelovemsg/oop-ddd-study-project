package com.example.oopdddstudyproject.study.chainofresponsibility;

class ShipmentHandler extends ProcessHandler {
    @Override
    protected void process(Car car) {
        System.out.println("--- 4단계: 출고 공정 ---");
        car.addLog("최종 검수 및 고객 인도 준비 완료");
    }
}