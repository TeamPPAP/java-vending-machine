package vending_machine;

import domain.StockHandler;

public class VendingMachineContext {

    private final StockHandler stockHandler;
    private int balance = 0;
    private VendingMachineState currentState;

    public VendingMachineContext(StockHandler stockHandler) {
        this.stockHandler = stockHandler;
        this.currentState = NotEnoughBalanceState.getInstance();
    }

    public void addBalance(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("투입 금액은 양수여야 합니다.");
        }
        this.balance += amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public void subtractBalance(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
        if (this.balance < amount) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }
        this.balance -= amount;
    }

    public StockHandler getStockHandler() {
        return stockHandler;
    }

    public VendingMachineState getState() {
        return currentState;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
}
