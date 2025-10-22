package vending_machine.Util;

import java.util.Scanner;

public class Util {
    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();

    public boolean retry(int exchange) {
        String input;
        System.out.println("추가 구매를 하시겠습니까? (Y/N)");
        do {
            input = sc.nextLine();
        } while (validator.stringValidate(input));
        System.out.println("잔돈"+exchange);
    return input.equalsIgnoreCase("y");
    }
}
