package vending_machine;

import domain.StockHandler;

public class VendingMachine {

    private final VendingMachineContext context;

    public VendingMachine() {
        StockHandler stockHandler = new StockHandler();
        this.context = new VendingMachineContext(stockHandler);
    }

    public void run() {
        // TODO: 메인 루프 구현
    }

    public void depositBalance(int amount) {
        context.getState().userDepositedBalance(context, amount);
    }

    public void purchase(int item) {
        context.getState().userTriedPurchasing(context, item);
    }

    public void withdrawBalance(int amount) {
        context.getState().userWithdrawnBalance(context, amount);
    }

    public VendingMachineContext getContext() {
        return context;
    }

}
