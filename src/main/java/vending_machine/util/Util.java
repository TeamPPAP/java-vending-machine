package vending_machine.util;

import static vending_machine.util.Validator.stringValidate;

import java.util.Scanner;

public class Util {
    static Scanner sc = new Scanner(System.in);

    public static boolean retry(int exchange) {
        String input;
        System.out.println("추가 구매를 하시겠습니까? (Y/N)");
        do {
            input = sc.nextLine();
        } while (stringValidate(input));
        System.out.println("잔돈"+exchange);
    return input.equalsIgnoreCase("y");
    }
}
