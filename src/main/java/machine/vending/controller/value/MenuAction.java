package machine.vending.controller.value;

public enum MenuAction {
    PURCHASE,
    ADD_MONEY,
    RETURN_MONEY;

    public static MenuAction of(int choice) {
        return switch (getChoice(choice)) {
            case 0 -> PURCHASE;
            case 1 -> ADD_MONEY;
            case 2 -> RETURN_MONEY;
            default -> throw new IllegalArgumentException("Invalid choice: " + choice);
        };
    }

    private static int getChoice(int choice) {
        if (choice < 0) choice = 0;
        return choice;
    }
}
