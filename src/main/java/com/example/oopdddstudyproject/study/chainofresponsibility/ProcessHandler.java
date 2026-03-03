package com.example.oopdddstudyproject.study.chainofresponsibility;

public abstract class ProcessHandler {
    protected ProcessHandler nextHandler;

    // 체인을 연결하는 메서드 (Set Next)
    public ProcessHandler setNext(ProcessHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler; // 메서드 체이닝을 위해 반환
    }

    // 공통 실행 로직 (템플릿 메서드 패턴처럼 동작)
    public void handle(Car car) {
        process(car); // 1. 내 공정을 수행한다.

        // 2. 다음 공정이 있으면 넘긴다.
        if (nextHandler != null) {
            nextHandler.handle(car);
        }
    }

    // 각 공장이 구현해야 할 추상 메서드
    protected abstract void process(Car car);
}