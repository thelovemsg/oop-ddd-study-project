package com.example.oopdddstudyproject.study.observer;

public class NotificationObserver implements ReservationSubscriber {
    @Override
    public void update(ReservationContext context) {
        String message = String.format("[Notification] 객실 %s 상태가 %s(으)로 변경되었습니다. 알림을 발송합니다.",
                context.roomId(),
                context.reservationStatus());
        System.out.println(message);
    }
}