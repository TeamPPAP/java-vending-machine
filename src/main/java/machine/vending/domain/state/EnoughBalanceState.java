package machine.vending.domain.state;

import machine.vending.domain.Product;
import machine.vending.domain.VendingMachineContext;
import machine.vending.exception.InsufficientBalanceException;
import machine.vending.exception.OutOfStockException;

public class EnoughBalanceState implements VendingMachineState {

    // Note: Thread-unsafe lazy initialization (자판기는 단일 스레드 환경에서만 동작)
    private static EnoughBalanceState instance;

    private EnoughBalanceState() {
    }

    public static EnoughBalanceState getInstance() {
        if (instance == null) {
            instance = new EnoughBalanceState();
        }
        return instance;
    }

    @Override
    public void userTriedPurchasing(VendingMachineContext context, int product) {
        Product selectedItem = context.getInventory().getByDisplayNumber(product);

        if (!selectedItem.hasStock()) {
            throw new OutOfStockException(selectedItem.getName());
        }

        if (context.getBalance() < selectedItem.getPrice()) {
            throw new InsufficientBalanceException(context.getBalance(), selectedItem.getPrice());
        }

        context.subtractBalance(selectedItem.getPrice());
        context.getInventory().purchase(product);

        if (context.getBalance() == 0) {
            context.setState(NotEnoughBalanceState.getInstance());
        }
    }

    @Override
    public void userDepositedBalance(VendingMachineContext context, int amount) {
        context.addBalance(amount);
    }

    @Override
    public void userWithdrawnBalance(VendingMachineContext context, int amount) {
        context.subtractBalance(amount);
        if (context.getBalance() == 0) {
            context.setState(NotEnoughBalanceState.getInstance());
        }
    }
}
