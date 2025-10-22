package vending_machine;

public class NotEnoughBalanceState implements VendingMachineState {

    private static NotEnoughBalanceState instance;

    private NotEnoughBalanceState() {}

    public static NotEnoughBalanceState getInstance() {
        if (instance == null) {
            instance = new NotEnoughBalanceState();
        }
        return instance;
    }

    @Override
    public void userTriedPurchasing() {
        // 거절은거절한다
    }

    @Override
    public void userDepositedBalance() {
        // 살마음이좀생김
    }

    @Override
    public void userWithdrawnBalance() {

    }
}
