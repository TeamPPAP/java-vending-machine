package vending_machine.view;

import java.util.List;
import java.util.stream.IntStream;
import vending_machine.controller.dto.ItemDTO;
import vending_machine.domain.Money;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("🥤 안녕하세요! PPAP 자판기입니다. 🥤");
    }

    public void printCurrentMoney(Money money) {
        System.out.println("\n현재 투입된 금액: " + money.amount() + "원");
    }

    public void printItemList(List<ItemDTO> items) {
        System.out.println("===============================");
        IntStream.range(0, items.size())
                .forEach(i -> {
                    ItemDTO itemDTO = items.get(i);
                    System.out.printf("[%d] %s (%d원) - %d개\n",
                            i + 1,
                            itemDTO.name(),
                            itemDTO.price(),
                            itemDTO.stock());
                });

        System.out.println("-------------------------------");
    }

    public void printPurchaseMenu() {
        System.out.println("[A] 금액 추가 투입");
        System.out.println("[R] 금액 반환");
        System.out.println("===============================");
    }

    public void printPurchaseSuccess(String itemName, Money change) {
        System.out.printf("\n\"%s\"가 나왔습니다! (거스름돈: %d원)\n\n", itemName, change.amount());
    }

    public void printReturnChange(Money change) {
        System.out.println("\n거스름돈 " + change.amount() + "원이 반환되었습니다.\n");
    }

    public void printErrorMessage(String message) {
        System.out.println("\n[ERROR] " + message + "\n");
    }
}
