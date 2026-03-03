package com.example.oopdddstudyproject.study.chainofresponsibility;

class PaintHandler extends ProcessHandler {
    @Override
    protected void process(Car car) {
        System.out.println("--- 3단계: 도장 공정 ---");
        car.addLog("외부 도색 및 코팅 완료");
    }
}