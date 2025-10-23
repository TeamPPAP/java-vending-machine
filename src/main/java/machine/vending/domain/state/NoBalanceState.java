package machine.vending.domain.state;

import machine.vending.domain.VendingMachineContext;
import machine.vending.exception.NoBalanceException;

public class NoBalanceState implements VendingMachineState {

    // Note: Thread-unsafe lazy initialization (자판기는 단일 스레드 환경에서만 동작)
    private static NoBalanceState instance;

    private NoBalanceState() {
    }

    public static NoBalanceState getInstance() {
        if (instance == null) {
            instance = new NoBalanceState();
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
            context.setState(HasBalanceState.getInstance());
        }
    }

    @Override
    public void userWithdrawnBalance(VendingMachineContext context, int amount) {
        throw new NoBalanceException();
    }
}
