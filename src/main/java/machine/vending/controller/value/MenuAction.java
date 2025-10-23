package machine.vending.controller.value;

import java.util.Arrays;

public enum MenuAction {
    PURCHASE,
    ADD_MONEY,
    RETURN_MONEY;

    public static MenuAction of(int choice) {
        int choiceHandled = getChoice(choice);
        return Arrays.stream(values())
            .filter(e -> e.ordinal() == choiceHandled)
            .findFirst()
            .orElse(RETURN_MONEY);
    }

    private static int getChoice(int choice) {
        if (choice < 0) choice = 0;
        return choice;
    }
}
