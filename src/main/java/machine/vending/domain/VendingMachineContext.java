package machine.vending.domain;

import machine.vending.domain.state.NoBalanceState;
import machine.vending.domain.state.VendingMachineState;

public class VendingMachineContext {

    private final Inventory inventory;
    private int balance = 0;
    private VendingMachineState currentState;

    public VendingMachineContext(Inventory inventory) {
        this.inventory = inventory;
        this.currentState = NoBalanceState.getInstance();
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

    public Inventory getInventory() {
        return inventory;
    }

    public VendingMachineState getState() {
        return currentState;
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }
}
