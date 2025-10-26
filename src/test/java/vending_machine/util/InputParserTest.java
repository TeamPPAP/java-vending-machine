package vending_machine.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    @DisplayName("정수 이외의 값 입력 시 예외가 발생한다.")
    void shouldThrowsException_whenNonInteger() {
        assertThatThrownBy(() -> InputParser.parseMoney("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 금액입니다. 숫자만 입력해주세요.");
    }

    @Test
    @DisplayName("가능한 돈 단위가 아닌 값 입력 시 예외가 발생한다.")
    void shouldThrowsException_whenNonMultipleOfMoneyUnit() {
        assertThatThrownBy(() -> InputParser.parseMoney("123"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("투입 금액은 100원 단위여야 합니다.");
    }

    @Test
    @DisplayName("최소 상품 번호 미만의 값 입력 시 예외가 발생한다.")
    void shouldThrowsException_whenLessThanOne() {
        assertThatThrownBy(() -> InputParser.parseMenuChoice("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("번호는 1 이상의 정수여야 합니다.");
    }

    @Test
    @DisplayName("추가 구매 요청에 Y/N 이외의 값 입력 시 예외가 발생한다.")
    void shouldThrowsException_whenInvalidInput() {
        assertThatThrownBy(() -> InputParser.parseContinueChoice("A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 선택입니다. 'Y' 또는 'N'을 입력해주세요.");
    }
}
