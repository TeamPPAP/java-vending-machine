package vending_machine.domain;

import static vending_machine.domain.Announce.ErrorAnnounce.GO_AWAY_POOR;
import static vending_machine.domain.Announce.ErrorAnnounce.INPUT_EMPTY;
import static vending_machine.domain.Announce.ErrorAnnounce.KOREA_MONEY;
import static vending_machine.domain.Announce.ErrorAnnounce.MINUS_CASH;
import static vending_machine.domain.Announce.ErrorAnnounce.NOT_ENOUGH_CASH;
import static vending_machine.domain.Announce.ErrorAnnounce.ONLY_INPUT_NUMBER;

public class Cash {
    private int cash;

    private Cash() {
    }

    private Cash(int cash) {
        this.cash = cash;
    }

    public static Cash forCreate(int cash) {
        return new Cash(cash);
    }

    public void insertCash(String money) throws IllegalArgumentException {
        int num = cashValidate(money);
        cash+=num;
    }

    public void purchaseCash(Item item) throws IllegalArgumentException {
        purchaseConditions(item);
        this.cash -= item.getPrice();
    }

    public int getCash() {
        return cash;
    }

    public boolean isZero() {
        return cash <= 0;
    }

    private void purchaseConditions(Item item) {
        if (cash < item.getPrice()) {
            int num = item.getPrice() - cash;
            String message = String.format(NOT_ENOUGH_CASH.getMessage(), num);
            throw new IllegalArgumentException(message);
        }
    }

    public int cashValidate(String input) throws IllegalArgumentException {
        validInput(input);
        int money = validInt(input);
        validKoreaMoney(money);
        validCash(money);
        notMinusMoney(money);
        return money;
    }

    private void validCash() {
        if (isZero()) {
            throw new IllegalArgumentException(GO_AWAY_POOR.getMessage());
        }
    }
    private void validCash(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(GO_AWAY_POOR.getMessage());
        }
    }
    private void validInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY.getMessage());
        }
    }

    private int validInt(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_INPUT_NUMBER.getMessage());
        }
        return money;
    }

    private void validKoreaMoney(int money) {
        if (money % 10 != 0) {
            throw new IllegalArgumentException(KOREA_MONEY.getMessage());
        }
    }

    private void notMinusMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(MINUS_CASH.getMessage());
        }
    }
}