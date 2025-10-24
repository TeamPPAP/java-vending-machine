package vending_machine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈이 최소 금액 미만일 경우 예외가 발생한다.")
    void shouldThrowException_whenLowerMinMoney() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0미만일 수 없습니다.");
    }
}
