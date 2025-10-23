package vending_machine.view;

import vending_machine.domain.Money;
import vending_machine.util.InputParser;

import java.util.Scanner;

/**
 * 사용자로부터 입력을 받는 역할에만 집중하는 클래스입니다.
 * 입력받은 문자열의 변환 및 검증은 InputParser에게 위임합니다.
 */
public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * 사용자에게 금액 투입을 요청하고, 입력받은 문자열을 InputParser를 통해 Money 객체로 변환하여 반환합니다.
     *
     * @return 변환된 Money 객체
     */
    public Money getInitialMoney() {
        System.out.print("투입할 금액을 입력해주세요 : ");
        String input = scanner.nextLine();
        return InputParser.parseMoney(input);
    }

    /**
     * 사용자에게 구매할 상품 번호 또는 메뉴 번호를 입력받아 정수로 변환하여 반환합니다.
     *
     * @return 변환된 번호
     */
    public int getItemToPurchase() {
        System.out.print("구입할 상품 번호를 입력하세요 : ");
        String input = scanner.nextLine();

        return InputParser.parseInt(input);
    }

    /**
     * 사용자에게 추가 구매 여부를 묻고, 유효한 선택 문자열("Y" 또는 "N")을 반환합니다.
     *
     * @return "Y" 또는 "N"
     */
    public String getContinueChoice() {
        System.out.print("추가 구매를 하시겠습니까? (Y/N) : ");
        String input = scanner.nextLine();
        return InputParser.parseContinueChoice(input);
    }
}
