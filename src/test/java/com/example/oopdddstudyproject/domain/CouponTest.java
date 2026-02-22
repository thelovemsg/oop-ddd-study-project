package com.example.oopdddstudyproject.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CouponTest {


    @Test
    @DisplayName("쿠폰 생성이 잘 되는지 확인")
    public void 쿠폰_생성_테스트() {
        //given
        Coupon coupon = new Coupon();
        //when
        //then
    }

    @Test
    @DisplayName("생성된 쿠폰 조회가 잘 되는지 확인")
    public void 쿠폰_조회_테스트() {
        //given
        Coupon coupon = new Coupon();
        //when
        //then
    }

    @Test
    @DisplayName("쿠폰 삭제가 잘 되는지 확인")
    public void 쿠폰_삭제_테스트() {
        //given
        Coupon coupon = new Coupon();
        //when
        //then
    }


    @Test
    @DisplayName("쿠폰 만료가 잘 되는지 확인")
    public void 쿠폰_만료_테스트() {
        //given
        Coupon coupon = new Coupon();
        //when
        //then
    }

    @Test
    @DisplayName("이미 판매된 쿠폰의 경우 판매되었다는걸 사용자가 인지 할 수 있는지 확인")
    public void 쿠폰_이미_판매_에러() {
        //given
        Coupon coupon = new Coupon();
        //when
        //then

    }


    @Test
    @DisplayName("이미 만료된 쿠폰의 경우 판매되었다는걸 사용자가 인지 할 수 있는지 확인")
    public void 쿠폰_이미_만료_에러() {
        //given
        Coupon coupon = new Coupon();
        //when
        //then

    }

}