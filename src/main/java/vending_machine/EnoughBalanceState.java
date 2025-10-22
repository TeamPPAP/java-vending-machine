package vending_machine;

public class EnoughBalanceState implements VendingMachineState {

    private static EnoughBalanceState instance;

    private EnoughBalanceState() {}

    public static EnoughBalanceState getInstance() {
        if (instance == null) {
            instance = new EnoughBalanceState();
        }
        return instance;
    }

    @Override
    public void userTriedPurchasing() {
        // 실질 구매 로직
        // validation 등등
    }

    @Override
    public void userDepositedBalance() {
        // 돈이 있는데 더 넣음..
    }

    @Override
    public void userWithdrawnBalance() {
        // 더안산다함
    }
}
