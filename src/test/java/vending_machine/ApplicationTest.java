package vending_machine;

import machine.vending.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends TestInit {

    @Test
    @DisplayName("정상적으로 실행이 된다.")
    void runTest() {
        run("2000", "2", "Y", "1", "6", "1000", "1", "N");

        assertThat(output()).contains(
            "현재 투입된 금액: 2000원",
            "\"칠성사이다\"가 나왔습니다! (거스름돈: 800원)",
            "추가 구매를 하시겠습니까? (Y/N)",
            "현재 투입된 금액: 800원",
            "금액이 부족합니다. (부족한 금액: 300원)",
            "현재 투입된 금액: 1800원",
            "\"코카콜라\"가 나왔습니다! (거스름돈: 700원)",
            "거스름돈 700원이 반환되었습니다."
        );
    }

    @Test
    @DisplayName("잘못된 입력일 경우 예외가 발생한다.")
    void throwsException() {
        assertThatThrownBy(() -> run("abc"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
