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
        if (availableCount < 0 || usedCount < 0 || reservedCount < 0) {
            throw new IllegalArgumentException("수량은 0보다 작을 수 없습니다.");
        }

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

    // 재고 추가 (총 발행량 증가)
    public Inventory addQuantity(int addedCount) {
        if (addedCount < 0) {
            throw new IllegalArgumentException("추가 수량은 0보다 커야 합니다.");
        }
        return new Inventory(
                this.availableCount + addedCount,
                this.usedCount,
                this.reservedCount // 기존 사용/예약 건수는 절대 건드리지 않음
        );
    }

    // 쿠폰 발급 (가용 재고 감소, 예약 재고 증가)
    public Inventory reserve() {
        if (this.availableCount <= 0) {
            throw new IllegalStateException("발급 가능한 쿠폰 재고가 부족합니다.");
        }
        return new Inventory(
                this.availableCount - 1,
                this.usedCount,
                this.reservedCount + 1
        );
    }

}