package vending_machine;

import domain.Product;

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
    public void userTriedPurchasing(VendingMachineContext context, int product) {
        Product selectedItem = context.getStockHandler().getByDisplayNumber(product);

        if (!selectedItem.hasStock()) {
            throw new IllegalStateException("재고가 부족합니다.");
        }

        if (context.getBalance() < selectedItem.getPrice()) {
            int shortage = selectedItem.getPrice() - context.getBalance();
            throw new IllegalArgumentException("금액이 부족합니다. (부족한 금액: " + shortage + "원)");
        }

        context.subtractBalance(selectedItem.getPrice());
        context.getStockHandler().purchase(product);
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
