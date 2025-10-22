package vending_machine.domain;

import java.util.List;

public class Items {
    private final List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item findItemByNumber(int itemNumber) {
        int index = itemNumber - 1;
        validateItemIndex(index);
        return items.get(index);
    }

    private void validateItemIndex(int index) {
        if (index < 0 || items.size() <= index) {
            throw new IllegalArgumentException("존재하지 않는 상품 번호입니다.");
        }
    }
}
