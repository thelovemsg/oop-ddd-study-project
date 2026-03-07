package com.example.oopdddstudyproject.study.observer;

public record ReservationContext(
    String roomId,
    String reservationStatus,
    long timestamp
) {}