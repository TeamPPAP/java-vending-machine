package vending_machine.domain;

public class Stock {
    private int quantity;

    public Stock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("재고는 0보다 작을 수 없습니다.");
        }
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
}
