package com.example.oopdddstudyproject.domain;

import com.example.oopdddstudyproject.FakeTimeGenerator;
import com.example.oopdddstudyproject.common.domain.Inventory;
import com.example.oopdddstudyproject.coupon.domain.Coupon;
import com.example.oopdddstudyproject.coupon.domain.CouponCreate;
import com.example.oopdddstudyproject.coupon.domain.CouponUpdate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        Assertions.assertThat(newCoupon.getDescription()).isEqualTo("테스트 생성입니다.");
        Assertions.assertThat(newCoupon.getCreatedAt()).isEqualTo(timeGenerator.millis());
        Assertions.assertThat(newCoupon.getModifiedAt()).isEqualTo(timeGenerator.millis());
        Assertions.assertThat(newCoupon.getInventory().getTotalCount()).isEqualTo(totalCount);
        Assertions.assertThat(newCoupon.getInventory().getReservedCount()).isEqualTo(reservedCount);
        Assertions.assertThat(newCoupon.getInventory().getAvailableCount()).isEqualTo(availableCount);
        Assertions.assertThat(newCoupon.getInventory().getUsedCount()).isEqualTo(usedCount);
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
                .reservedCount(0)
                .availableCount(1200)
                .usedCount(0)
                .build();

        Coupon coupon = Coupon.builder()
                .id(1L)
                .description("테스트 쿠폰 생성")
                .inventory(inventory)
                .createdAt(1000L)
                .modifiedAt(1000L)
                .build();

        //when
        FakeTimeGenerator timeGenerator = new FakeTimeGenerator(2000L);
        Coupon updatedCoupon = coupon.update(couponUpdate, timeGenerator);

        //then
        Assertions.assertThat(updatedCoupon.getId()).isEqualTo(coupon.getId());
        Assertions.assertThat(updatedCoupon.getCreatedAt()).isEqualTo(1000L);
        Assertions.assertThat(updatedCoupon.getModifiedAt()).isEqualTo(2000L);
        Assertions.assertThat(updatedCoupon.getDescription()).isEqualTo("테스트 쿠폰 생성 - 수정하기");
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