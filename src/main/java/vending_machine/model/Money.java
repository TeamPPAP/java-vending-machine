package vending_machine;

import java.math.BigDecimal;
import java.util.Scanner;
//TODO : Scanner를 분리가 필요합니다.
public class Money {
    private BigDecimal balance;
    Scanner scanner;

    public Money(Scanner scanner) {
        this.scanner = scanner;
    }

    public void askForAmount() {
        System.out.print("투입할 금액을 입력해주세요 : ");
        this.balance = this.balance.add(BigDecimal.valueOf(scanner.nextInt()));

    }
}
