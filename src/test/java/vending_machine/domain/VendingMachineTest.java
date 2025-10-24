package vending_machine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VendingMachineTest {

    @Test
    @DisplayName("존재하지 않는 상품 번호로 구매 시 예외가 발생한다.")
    void shouldThrowsException_whenInvalidIndex() {
        List<Item> items = List.of(Item.from("콜라", 1000, 1));
        VendingMachine vendingMachine = new VendingMachine(items);
        vendingMachine.insertMoney(new Money(2000));

        assertThatThrownBy(() -> vendingMachine.purchaseItem(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 상품 번호입니다.");
    }

    @Test
    @DisplayName("금액이 부족할 경우 구매 시 예외가 발생한다.")
    void shouldThrowsException_whenInsufficientMoney() {
        List<Item> items = List.of(Item.from("콜라", 1000, 1));
        VendingMachine vendingMachine = new VendingMachine(items);
        vendingMachine.insertMoney(new Money(500));

        assertThatThrownBy(() -> vendingMachine.purchaseItem(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액이 부족합니다.");
    }

    @Test
    @DisplayName("재고가 없을 경우 구매 시 예외가 발생한다.")
    void shouldThrowsException_whenOutOfStock() {
        List<Item> items = List.of(Item.from("콜라", 1000, 0));
        VendingMachine vendingMachine = new VendingMachine(items);
        vendingMachine.insertMoney(new Money(2000));

        assertThatThrownBy(() -> vendingMachine.purchaseItem(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다.");
    }
}
