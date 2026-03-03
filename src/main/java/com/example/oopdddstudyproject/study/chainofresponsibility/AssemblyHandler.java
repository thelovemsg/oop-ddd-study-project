package com.example.oopdddstudyproject.study.chainofresponsibility;

class AssemblyHandler extends ProcessHandler {
    @Override
    protected void process(Car car) {
        System.out.println("--- 2단계: 차체 조립 공정 ---");
        car.addLog("엔진 장착 및 프레임 조립 완료");
    }
}