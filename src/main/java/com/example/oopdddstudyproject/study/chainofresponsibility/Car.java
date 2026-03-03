package com.example.oopdddstudyproject.study.chainofresponsibility;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Car {
    private String status = "원자재(철판)";
    private List<String> history = new ArrayList<>();

    public void addLog(String log) {
        this.history.add(log);
        this.status = log; // 현재 상태 업데이트
        System.out.println("Current Status: " + this.status);
    }
}
