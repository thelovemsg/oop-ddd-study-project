package com.example.oopdddstudyproject.domain;

import com.example.oopdddstudyproject.FakeTimeGenerator;
import com.example.oopdddstudyproject.common.domain.Inventory;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import com.example.oopdddstudyproject.coupon.domain.CouponUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CouponTest {

    @Test
    @DisplayName("쿠폰 정보가 잘 생성이 잘 되는지 확인")
    public void 쿠폰_정보_생성_테스트() {
        Inventory inventory = Inventory.builder()
                .reservedCount(20)
                .availableCount(980)
                .usedCount(120)
                .build();

        //given
        Coupon coupon = Coupon.builder()
                .id(1L)
                .description("테스트 쿠폰 생성")
                .inventory(inventory)
                .build();

        //when
        // 단순 조립 검증이므로 별도의 비즈니스 행위(when)는 없습니다.

        //then
        Assertions.assertThat(coupon.getId()).isEqualTo(1L);
        Assertions.assertThat(coupon.getDescription()).isEqualTo("테스트 쿠폰 생성");
        Assertions.assertThat(coupon.getInventory().getTotalCount()).isEqualTo(20+980+120);
        Assertions.assertThat(coupon.getInventory().getReservedCount()).isEqualTo(20);
        Assertions.assertThat(coupon.getInventory().getAvailableCount()).isEqualTo(980);
        Assertions.assertThat(coupon.getInventory().getUsedCount()).isEqualTo(120);
    }

    @Test
    @DisplayName("쿠폰 정보가 잘 수정되는지 확인")
    public void 쿠폰_수정_테스트() {
        LocalDate expireDate = LocalDate.of(2014, 10, 1);
        CouponUpdate couponUpdate = CouponUpdate
                .builder()
                .description("테스트 쿠폰 생성 - 수정하기")
                .expireDate(expireDate)
                .build();

        //given
        Inventory inventory = Inventory.builder()
                .reservedCount(20)
                .availableCount(980)
                .usedCount(120)
                .build();

        Coupon coupon = Coupon.builder()
                .id(1L)
                .description("테스트 쿠폰 생성")
                .inventory(inventory)
                .build();

        //when
        Coupon updatedCoupon = coupon.update(couponUpdate, new FakeTimeGenerator(1000L));

        //then
        Assertions.assertThat(updatedCoupon.getId()).isEqualTo(coupon.getId());
        Assertions.assertThat(updatedCoupon.getDescription()).isEqualTo(coupon.getDescription());
        Assertions.assertThat(updatedCoupon.getExpireDate()).isEqualTo(coupon.getDescription());
    }

    @Test
    @DisplayName("쿠폰 만료가 잘 되는지 확인")
    public void 쿠폰_만료_테스트() {
        //given
//        Coupon coupon = new Coupon();
        //when
        //then
    }

    @Test
    @DisplayName("이미 판매된 쿠폰의 경우 판매되었다는걸 사용자가 인지 할 수 있는지 확인")
    public void 쿠폰_이미_판매_에러() {
        //given
//        Coupon coupon = new Coupon();
        //when
        //then

    }

    @Test
    @DisplayName("이미 만료된 쿠폰의 경우 판매되었다는걸 사용자가 인지 할 수 있는지 확인")
    public void 쿠폰_이미_만료_에러() {
        //given
//        Coupon coupon = new Coupon();
        //when
        //then
    }

}