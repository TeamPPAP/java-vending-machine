package vending_machine.domain;

import static vending_machine.domain.Announce.ErrorAnnounce.GO_AWAY_POOR;
import static vending_machine.domain.Announce.ErrorAnnounce.INPUT_EMPTY;
import static vending_machine.domain.Announce.ErrorAnnounce.KOREA_MONEY;
import static vending_machine.domain.Announce.ErrorAnnounce.MINUS_CASH;
import static vending_machine.domain.Announce.ErrorAnnounce.NOT_ENOUGH_CASH;
import static vending_machine.domain.Announce.ErrorAnnounce.ONLY_INPUT_NUMBER;

public class Cash {
    private int cash = 0;

    private Cash() {
    }

    private Cash(int cash) {
        this.cash = cash;
    }

    public static Cash forCreate(int cash) {
        return new Cash(cash);
    }

    public Cash insertCash(String money) {
        int tmp = cashValidate(money);
        cash += tmp;
        return this;
    }

    public Cash perchaseCash(int price, Item item) throws IllegalArgumentException {
        purchaseConditions(item);
        this.cash -= price;
        return new Cash(cash);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isZero() {
        return cash <= 0;
    }

    private void purchaseConditions(Item item) {
        if (cash < item.getPrice()) {
            throw new IllegalArgumentException(NOT_ENOUGH_CASH.getMessage());
        }
    }

    public int cashValidate(String input) {
        validInput(input);
        int cash = validInt(input);
        validKoreaMoney();
        validCash();
        notMinusMoney();
        return cash;
    }

    private void validCash() {
        if (isZero()) {
            throw new IllegalArgumentException(GO_AWAY_POOR.getMessage());
        }
    }

    private void validInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(INPUT_EMPTY.getMessage());
        }
    }

    private int validInt(String input) {
        try {
            cash = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_INPUT_NUMBER.getMessage());
        }
        return cash;
    }

    private void validKoreaMoney() {
        if (cash % 10 != 0) {
            throw new IllegalArgumentException(KOREA_MONEY.getMessage());
        }
    }

    private void notMinusMoney() {
        if (cash < 0) {
            throw new IllegalArgumentException(MINUS_CASH.getMessage());
        }
    }


}
