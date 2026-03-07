package com.example.oopdddstudyproject.study.observer;

import java.util.ArrayList;
import java.util.List;

public class ReservationPublisher {
    // 다이어그램의 - subscribers: Subscriber[]
    private final List<ReservationSubscriber> subscribers = new ArrayList<>();
    
    // 다이어그램의 - mainState (예: 일일 총 예약 건수 등 퍼블리셔 자체의 상태)
    private int totalDailyReservations = 0;

    // + subscribe(s: Subscriber)
    public void subscribe(ReservationSubscriber s) {
        subscribers.add(s);
    }

    // + unsubscribe(s: Subscriber)
    public void unsubscribe(ReservationSubscriber s) {
        subscribers.remove(s);
    }

    // + notify()
    protected void notifySubscribers(ReservationContext context) {
        for (ReservationSubscriber subscriber : subscribers) {
            subscriber.update(context);
        }
    }

    // + mainLogic(): 발행자의 주된 비즈니스 로직
    public void confirmReservation(String roomId) {
        // 1. 핵심 비즈니스 로직 수행 (DB 상태 업데이트 등)
        System.out.println("--- ReservationPublisher: 비즈니스 로직 처리 시작 ---");
        this.totalDailyReservations++;
        
        // 2. 상태 변경에 따른 Context 객체 생성
        ReservationContext context = new ReservationContext(roomId, "CONFIRMED", System.currentTimeMillis());
        
        // 3. 구독자들에게 통지
        notifySubscribers(context);
        System.out.println("--- ReservationPublisher: 비즈니스 로직 처리 완료 ---\n");
    }
}