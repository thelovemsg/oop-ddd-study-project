package com.example.oopdddstudyproject.study.chainofresponsibility;

public class FactoryClient {
    public static void main(String[] args) {
        // 1. 각 공정 생성
        ProcessHandler press = new PressHandler();
        ProcessHandler assembly = new AssemblyHandler();
        ProcessHandler paint = new PaintHandler();
        ProcessHandler shipment = new ShipmentHandler();

        // 2. 체인 연결 (철판 -> 조립 -> 도색 -> 출고)
        press.setNext(assembly)
             .setNext(paint)
             .setNext(shipment);

        // 3. 자동차 생산 시작! (맨 처음 단계에만 넣으면 알아서 끝까지 감)
        System.out.println("🚗 자동차 생산을 시작합니다...");
        Car newCar = new Car();
        
        press.handle(newCar); 
        
        System.out.println("\n🎉 모든 공정 완료!");
    }
}