package vending_machine.view;

import java.util.Scanner;
import vending_machine.domain.Money;
import vending_machine.util.InputParser;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public Money getInitialMoney() {
        System.out.print("투입할 금액을 입력해주세요 : ");
        String input = scanner.nextLine();
        return InputParser.parseMoney(input);
    }

    public String getMenuChoice() {
        System.out.print("구입할 상품 번호를 입력하세요 : ");
        String input = scanner.nextLine();
        return InputParser.parseMenuChoice(input);
    }

    public String getContinueChoice() {
        System.out.print("추가 구매를 하시겠습니까? (Y/N) : ");
        String input = scanner.nextLine();
        return InputParser.parseContinueChoice(input);
    }
}
