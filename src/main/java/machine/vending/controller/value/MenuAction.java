package machine.vending.controller.value;

import java.util.Arrays;

public enum MenuAction {
    PURCHASE(0),
    ADD_MONEY(1),
    RETURN_MONEY(2);

    private final int offset;

    MenuAction(int offset) {
        this.offset = offset;
    }

    public static MenuAction from(int choice, int inventorySize) {
        if (choice >= 1 && choice <= inventorySize) {
            return PURCHASE;
        }

        return Arrays.stream(values())
            .filter(action -> action != PURCHASE)
            .filter(action -> choice == inventorySize + action.offset)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 메뉴 번호입니다: " + choice));
    }

    public int calculateMenuNumber(int inventorySize) {
        return inventorySize + offset;
    }
}
