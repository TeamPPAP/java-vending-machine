package vending_machine;

import domain.StockHandler;

public class VendingMachineContext {

    private final StockHandler stockHandler;
    private int balance = 0;
    private int menuSelected = 0;
    private VendingMachineState currentState;

    public VendingMachineContext(StockHandler stockHandler) {
        this.stockHandler = stockHandler;
        this.currentState = NotEnoughBalanceState.getInstance();
    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public void subtractBalance(int amount) {
        this.balance -= amount;
    }

    public int getMenuSelected() {
        return menuSelected;
    }

    public void setMenuSelected(int menuSelected) {
        this.menuSelected = menuSelected;
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
