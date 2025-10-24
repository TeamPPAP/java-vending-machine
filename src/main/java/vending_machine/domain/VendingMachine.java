package vending_machine.domain;

import java.util.List;

public class VendingMachine {
    private final List<Item> items;
    private Money currentMoney;

    public VendingMachine(List<Item> items) {
        this.items = items;
        this.currentMoney = new Money(0);
    }

    public void insertMoney(Money moneyToInsert) {
        this.currentMoney = this.currentMoney.add(moneyToInsert);
    }

    public void purchaseItem(int itemIndex) {
        validateItemIndex(itemIndex);
        Item itemToPurchase = this.items.get(itemIndex - 1);
        validatePurchase(itemToPurchase);

        this.currentMoney = this.currentMoney.subtract(itemToPurchase.getPrice());
        itemToPurchase.decreaseStock();
    }

    public Money returnChange() {
        Money change = this.currentMoney;
        this.currentMoney = new Money(0);
        return change;
    }

    public List<Item> getItems() {
        return items;
    }

    public Money getCurrentMoney() {
        return currentMoney;
    }

    private void validateItemIndex(int itemIndex) {
        if (itemIndex <= 0 || items.size() < itemIndex) {
            throw new IllegalArgumentException("존재하지 않는 상품 번호입니다.");
        }
    }

    private void validatePurchase(Item item) {
        validateSufficientMoney(item);
        validateStockAvailable(item);
    }

    private void validateSufficientMoney(Item item) {
        if (!item.isPurchasable(this.currentMoney)) {
            int missingAmount = item.getPrice().amount() - this.currentMoney.amount();
            throw new IllegalArgumentException(String.format("금액이 부족합니다. (부족한 금액: %d원)", missingAmount));
        }
    }

    private void validateStockAvailable(Item item) {
        if (!item.hasStock()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
    }
}
