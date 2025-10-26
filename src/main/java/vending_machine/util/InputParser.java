package vending_machine.util;

import vending_machine.domain.Money;

public class InputParser {
    public static final String ADD_MONEY_COMMAND = "A";
    public static final String RETURN_CHANGE_COMMAND = "R";
    public static final String RETRY_ACCEPTED_COMMAND = "Y";
    public static final String RETRY_REFUSED_COMMAND = "N";

    private static final int MIN_AMOUNT_INCREMENT = 100;

    public static Money parseMoney(String input) {
        try {
            int amount = Integer.parseInt(input);
            validateAmountUnit(amount);
            return new Money(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 금액입니다. 숫자만 입력해주세요.");
        }
    }

    public static String parseMenuChoice(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.equalsIgnoreCase(ADD_MONEY_COMMAND) || trimmedInput.equalsIgnoreCase(RETURN_CHANGE_COMMAND)) {
            return trimmedInput.toUpperCase();
        }
        return String.valueOf(parseInt(trimmedInput));
    }

    public static String parseContinueChoice(String input) {
        if (input.equalsIgnoreCase(RETRY_ACCEPTED_COMMAND) || input.equalsIgnoreCase(RETRY_REFUSED_COMMAND)) {
            return input.toUpperCase();
        }
        throw new IllegalArgumentException("유효하지 않은 선택입니다. 'Y' 또는 'N'을 입력해주세요.");
    }

    private static int parseInt(String input) {
        try {
            int number = Integer.parseInt(input);
            validateUpperNumber(number);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 선택입니다. 상품 번호(숫자) 또는 'A', 'R'을 입력해주세요.");
        }
    }

    private static void validateAmountUnit(int amount) {
        if (amount % MIN_AMOUNT_INCREMENT != 0) {
            throw new IllegalArgumentException("투입 금액은 " + MIN_AMOUNT_INCREMENT + "원 단위여야 합니다.");
        }
    }

    private static void validateUpperNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("번호는 1 이상의 정수여야 합니다.");
        }
    }
}
