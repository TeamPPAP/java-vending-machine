package vending_machine.util;

import vending_machine.domain.Money;

public class InputParser {
    public static final String RETRY_ACCEPTED_COMMAND = "Y";
    public static final String RETRY_REFUSED_COMMAND = "N";

    public static Money parseMoney(String input) {
        try {
            int amount = Integer.parseInt(input);
            return new Money(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 금액입니다. 숫자만 입력해주세요.");
        }
    }

    public static int parseInt(String input) {
        try {
            int number = Integer.parseInt(input);
            validateUpperNumber(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 번호입니다. 숫자만 입력해주세요.");
        }
    }

    public static String parseContinueChoice(String input) {
        if (RETRY_ACCEPTED_COMMAND.equalsIgnoreCase(input) || RETRY_REFUSED_COMMAND.equalsIgnoreCase(input)) {
            return input;
        }
        throw new IllegalArgumentException("유효하지 않은 선택입니다. 'Y' 또는 'N'을 입력해주세요.");
    }

    private static void validateUpperNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("번호는 1 이상의 정수여야 합니다.");
        }
    }
}
