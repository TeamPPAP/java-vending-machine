package vending_machine.util;

import static vending_machine.domain.Announce.ErrorAnnounce.INDEX_BOUND_ERROR;
import static vending_machine.domain.Announce.ErrorAnnounce.INPUT_EMPTY;
import static vending_machine.domain.Announce.ErrorAnnounce.ONLY_INPUT_NUMBER;
import static vending_machine.domain.Announce.ErrorAnnounce.YES_NO;

public class Validator {
    private Validator() {
    }

    public static void validateRetry(String input) throws IllegalArgumentException {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY.getMessage());
        }
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
            return;
        }
        throw new IllegalArgumentException(YES_NO.getMessage());
    }

    public static void validateMenuChoice(int choice, int size) throws IllegalArgumentException {
        if (choice >= 1 && choice <= size) {
            return;
        }
        String errorMessage = String.format(INDEX_BOUND_ERROR.getMessage(), size);
        throw new IllegalArgumentException(errorMessage);
    }

    public static int validateInt(String input) throws IllegalArgumentException {
        try {
            validateString(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_INPUT_NUMBER.getMessage());
        }
    }
    public static void validateString(String input) throws IllegalArgumentException {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY.getMessage());
        }
    }
}