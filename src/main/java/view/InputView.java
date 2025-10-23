package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int readMoney() {
        System.out.print("투입할 금액을 입력해주세요: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 숫자로 입력해주세요.");
        }
    }

    public int readMenuChoice() {
        System.out.print("구입할 상품 번호를 입력하세요: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("상품 번호는 숫자로 입력해주세요.");
        }
    }

    public boolean readContinuePurchase() {
        System.out.print("추가 구매를 하시겠습니까? (Y/N) ");
        String input = scanner.nextLine().trim().toUpperCase();
        
        if (input.equals("Y")) {
            return true;
        }
        if (input.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException("Y 또는 N을 입력해주세요.");
    }

    public void close() {
        scanner.close();
    }
}
