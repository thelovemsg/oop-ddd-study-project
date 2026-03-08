package com.example.oopdddstudyproject.fare.domain;

import com.example.oopdddstudyproject.common.vo.Money;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Fare {

    private final Long id;
    private final String name;
    private final Money basePrice;
    private final FareStatusEnum status;
    private final FareTypeEnum fareType;
    private final Long createdAt;
    private final Long modifiedAt;

    @Builder
    public Fare(Long id, String name, Money basePrice, FareStatusEnum status, FareTypeEnum fareType, Long createdAt, Long modifiedAt) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.status = status;
        this.fareType = fareType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // 요금제 최초 생성
    public static Fare from(FareCreate fareCreate, long currentTime) {
        return Fare.builder()
                .name(fareCreate.getName())
                .basePrice(fareCreate.getBasePrice())
                .status(FareStatusEnum.ACTIVE)
                .createdAt(currentTime)
                .modifiedAt(currentTime)
                .build();
    }

    // 요금제 기본 정보 업데이트 (이름 등)
    public Fare updateFareInfo(FareUpdate fareUpdate, long currentTime) {
        return Fare.builder()
                .id(this.id)
                .name(fareUpdate.getName())
                .basePrice(this.basePrice)
                .status(this.status)
                .createdAt(this.createdAt)
                .modifiedAt(currentTime)
                .build();
    }

    // 요금 가격 변경
    public Fare changePrice(Money newPrice, long currentTime) {
        if (newPrice == null) {
            throw new IllegalArgumentException("변경할 요금은 필수입니다.");
        }

        return Fare.builder()
                .id(this.id)
                .name(this.name)
                .basePrice(newPrice)
                .status(this.status)
                .createdAt(this.createdAt)
                .modifiedAt(currentTime)
                .build();
    }

    // 요금제 판매 중단 (소프트 딜리트/상태 변경)
    public Fare deactivate(long currentTime) {
        if (this.status == FareStatusEnum.INACTIVE) {
            throw new IllegalStateException("이미 비활성화된 요금제입니다.");
        }

        return Fare.builder()
                .id(this.id)
                .name(this.name)
                .basePrice(this.basePrice)
                .status(FareStatusEnum.INACTIVE)
                .createdAt(this.createdAt)
                .modifiedAt(currentTime)
                .build();
    }
}