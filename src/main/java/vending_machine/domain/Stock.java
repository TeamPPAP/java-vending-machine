package vending_machine.domain;

public class Stock {
    private static final int MIN_QUANTITY = 0;

    private int quantity;

    public Stock(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return 0 < this.quantity;
    }

    public void decrease() {
        if (isAvailable()) {
            this.quantity--;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new IllegalArgumentException("재고는 " + MIN_QUANTITY + "보다 작을 수 없습니다.");
        }
    }
}
