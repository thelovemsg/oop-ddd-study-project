package com.example.oopdddstudyproject.domain;

import com.example.oopdddstudyproject.FakeTimeGenerator;
import com.example.oopdddstudyproject.common.domain.Inventory;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import com.example.oopdddstudyproject.coupon.domain.CouponCreate;
import com.example.oopdddstudyproject.coupon.domain.CouponUpdate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CouponTest {

    @Test
    @DisplayName("쿠폰 정보가 잘 생성이 잘 되는지 확인")
    public void 쿠폰_정보_생성_테스트() {
        int reservedCount = 20;
        int availableCount = 980;
        int usedCount = 120;
        int totalCount = reservedCount + availableCount + usedCount;

        //given
        LocalDate expireDate = LocalDate.of(2026, 2, 23);

        CouponCreate couponCreate = CouponCreate.builder()
                .reservedCount(reservedCount)
                .availableCount(availableCount)
                .usedCount(usedCount)
                .description("테스트 생성입니다.")
                .expireDate(expireDate)
                .build();

        //when
        FakeTimeGenerator timeGenerator = new FakeTimeGenerator(10000);
        Coupon newCoupon = Coupon.from(couponCreate, timeGenerator);

        //then
        assertThat(newCoupon.getDescription()).isEqualTo("테스트 생성입니다.");
        assertThat(newCoupon.getCreatedAt()).isEqualTo(timeGenerator.millis());
        assertThat(newCoupon.getModifiedAt()).isEqualTo(timeGenerator.millis());
        assertThat(newCoupon.getInventory().getTotalCount()).isEqualTo(totalCount);
        assertThat(newCoupon.getInventory().getReservedCount()).isEqualTo(reservedCount);
        assertThat(newCoupon.getInventory().getAvailableCount()).isEqualTo(availableCount);
        assertThat(newCoupon.getInventory().getUsedCount()).isEqualTo(usedCount);
    }

    @Test
    @DisplayName("이미 발급(예약/사용)된 쿠폰의 재고 정보를 수정하려 하면 에러가 발생한다")
    public void 쿠폰_이미_판매되어_수정불가_에러() {
        //given
        Inventory inventory = Inventory.builder()
                .reservedCount(1) // 예약 정보가 있음
                .availableCount(99)
                .usedCount(0)
                .build();

        Coupon coupon = Coupon.builder()
                .id(1L)
                .description("발급 중인 쿠폰")
                .inventory(inventory)
                .createdAt(1000L)
                .modifiedAt(1000L)
                .build();

        CouponUpdate couponUpdate = CouponUpdate.builder()
                .availableCount(500) // 수량 변경 시도
                .description("수정 시도")
                .build();

        FakeTimeGenerator timeGenerator = new FakeTimeGenerator(2000L);

        //when & then
        assertThatThrownBy(() -> coupon.updateInventoryInfo(couponUpdate, timeGenerator))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 발급이 진행된 쿠폰은 수정할 수 없습니다.");
    }

    @Test
    @DisplayName("발급 내역이 있어도 설명이나 만료일 같은 일반 정보는 수정이 가능하다")
    public void 쿠폰_정보_수정_테스트() {
        //given
        Inventory inventory = Inventory.builder()
                .reservedCount(10) // 이미 판매 중
                .availableCount(90)
                .usedCount(5)
                .build();

        Coupon coupon = Coupon.builder()
                .id(1L)
                .description("기존 설명")
                .inventory(inventory)
                .expireDate(LocalDate.of(2026, 12, 31))
                .createdAt(1000L)
                .modifiedAt(1000L)
                .build();

        LocalDate newExpireDate = LocalDate.of(2027, 1, 1);
        CouponUpdate couponUpdate = CouponUpdate.builder()
                .description("변경된 설명")
                .expireDate(newExpireDate)
                .build();

        FakeTimeGenerator timeGenerator = new FakeTimeGenerator(2000L);

        //when
        Coupon updatedCoupon = coupon.updateCouponInfo(couponUpdate, timeGenerator);

        //then
        assertThat(updatedCoupon.getDescription()).isEqualTo("변경된 설명");
        assertThat(updatedCoupon.getExpireDate()).isEqualTo(newExpireDate);
        assertThat(updatedCoupon.getInventory()).isEqualTo(inventory); // 재고 정보는 그대로 유지됨
        assertThat(updatedCoupon.getModifiedAt()).isEqualTo(2000L);
    }

    @Test
    @DisplayName("쿠폰 만료 기능 테스트 (비즈니스 로직 추가 시 활용)")
    public void 쿠폰_만료_테스트() {
        // given
        Coupon coupon = Coupon.builder()
                .expireDate(LocalDate.of(2026, 12, 31))
                .build();

        // when (가상의 시나리오: 만료일 변경)
        CouponUpdate update = CouponUpdate.builder()
                .expireDate(LocalDate.of(2026, 1, 1))
                .build();

        Coupon expiredCoupon = coupon.updateCouponInfo(update, new FakeTimeGenerator(1000L));

        // then
        assertThat(expiredCoupon.getExpireDate()).isBefore(LocalDate.now());
    }
}