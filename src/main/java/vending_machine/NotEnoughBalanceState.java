package vending_machine;

import exception.NoBalanceException;

public class NotEnoughBalanceState implements VendingMachineState {

    // Note: Thread-unsafe lazy initialization (자판기는 단일 스레드 환경에서만 동작)
    private static NotEnoughBalanceState instance;

    private NotEnoughBalanceState() {}

    public static NotEnoughBalanceState getInstance() {
        if (instance == null) {
            instance = new NotEnoughBalanceState();
        }
        return instance;
    }

    @Override
    public void userTriedPurchasing(VendingMachineContext context, int product) {
        throw new NoBalanceException();
    }

    @Override
    public void userDepositedBalance(VendingMachineContext context, int amount) {
        context.addBalance(amount);
        if (context.getBalance() > 0) {
            context.setState(EnoughBalanceState.getInstance());
        }
    }

    @Override
    public void userWithdrawnBalance(VendingMachineContext context, int amount) {
        throw new NoBalanceException();
    }
}
