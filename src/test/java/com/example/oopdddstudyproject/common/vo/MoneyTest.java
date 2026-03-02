package com.example.oopdddstudyproject.common.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("0원으로 Money를 생성할 수 있다")
    void Money_생성_zero() {
        // when
        Money money = Money.zero();

        // then
        assertThat(money.getAmount()).isEqualTo(0);
    }

    @Test
    @DisplayName("양수 금액으로 Money를 생성할 수 있다")
    void Money_생성() {
        // when
        Money money = Money.of(10000);

        // then
        assertThat(money.getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("음수 금액으로 Money를 생성하면 예외가 발생한다")
    void Money_생성_음수_예외() {
        assertThatThrownBy(() -> Money.of(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("두 금액을 더하면 합산된 금액이 반환된다")
    void Money_더하기() {
        // given
        Money money1 = Money.of(10000);
        Money money2 = Money.of(5000);

        // when
        Money result = money1.add(money2);

        // then
        assertThat(result.getAmount()).isEqualTo(15000);
    }

    @Test
    @DisplayName("add()는 기존 객체를 변경하지 않고 새 객체를 반환한다")
    void Money_더하기_새객체_반환() {
        // given
        Money money1 = Money.of(10000);
        Money money2 = Money.of(5000);

        // when
        Money result = money1.add(money2);

        // then
        assertThat(result).isNotSameAs(money1);
        assertThat(money1.getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("금액을 차감하면 차감된 금액이 반환된다")
    void Money_빼기() {
        // given
        Money money1 = Money.of(10000);
        Money money2 = Money.of(3000);

        // when
        Money result = money1.subtract(money2);

        // then
        assertThat(result.getAmount()).isEqualTo(7000);
    }

    @Test
    @DisplayName("차감 후 금액이 음수가 되면 예외가 발생한다")
    void Money_빼기_음수_예외() {
        // given
        Money money1 = Money.of(3000);
        Money money2 = Money.of(10000);

        // when & then
        assertThatThrownBy(() -> money1.subtract(money2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감 후 금액은 0원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("할인율을 적용하면 할인된 금액이 반환된다")
    void Money_할인율_적용() {
        // given
        Money money = Money.of(10000);

        // when
        Money result = money.discountByRate(0.1);

        // then
        assertThat(result.getAmount()).isEqualTo(9000);
    }

    @Test
    @DisplayName("할인율이 0~1 범위를 벗어나면 예외가 발생한다")
    void Money_할인율_범위_예외() {
        // given
        Money money = Money.of(10000);

        // when & then
        assertThatThrownBy(() -> money.discountByRate(1.1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("할인율은 0~1 사이여야 합니다.");
    }

    @Test
    @DisplayName("금액이 같으면 동등하다")
    void Money_동등성_비교() {
        // given
        Money money1 = Money.of(10000);
        Money money2 = Money.of(10000);

        // then
        assertThat(money1).isEqualTo(money2);
        assertThat(money1).isNotSameAs(money2);
    }
}