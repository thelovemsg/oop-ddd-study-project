package com.example.oopdddstudyproject.common.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Inventory {

    private final int totalCount;     // 내부에서 계산됨
    private final int availableCount;
    private final int usedCount;
    private final int reservedCount;

    @Builder
    public Inventory(int availableCount, int usedCount, int reservedCount) {
        // 1. 단일 속성 검증 (음수 불가)
        if (availableCount < 0 || usedCount < 0 || reservedCount < 0) {
            throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
        }

        // 2. 전체 수량은 파라미터로 받지 않고 무조건 내부에서 도출 (정합성 100% 보장)
        this.totalCount = availableCount + usedCount + reservedCount;
        this.availableCount = availableCount;
        this.usedCount = usedCount;
        this.reservedCount = reservedCount;
    }

    // 용도 1: 맨 처음 새 쿠폰을 기획/생성할 때 (초기 재고 세팅)
    public static Inventory createInitial(int totalCount) {
        if (totalCount < 0) {
            throw new IllegalArgumentException("초기 생성 수량은 0보다 작을 수 없습니다.");
        }
        return Inventory.builder()
                .availableCount(totalCount) // 처음엔 전체가 곧 남은 수량
                .usedCount(0)
                .reservedCount(0)
                .build();
    }

    // 용도 2: DB에서 기존 쿠폰 데이터를 읽어와서 객체로 복원할 때
    public static Inventory from(int availableCount, int usedCount, int reservedCount) {
        return Inventory.builder()
                .availableCount(availableCount)
                .usedCount(usedCount)
                .reservedCount(reservedCount)
                .build();
    }
}