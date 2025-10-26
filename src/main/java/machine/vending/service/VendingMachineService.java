package machine.vending.service;

import machine.vending.controller.dto.ContextInfo;
import machine.vending.controller.dto.PurchaseResponse;
import machine.vending.domain.Inventory;
import machine.vending.domain.Product;
import machine.vending.domain.VendingMachineContext;

public class VendingMachineService {
    private final VendingMachineContext context;

    public VendingMachineService() {
        Inventory inventory = new Inventory();
        this.context = new VendingMachineContext(inventory);
    }

    public ContextInfo findContext() {
        return new ContextInfo(context.getInventory(), context.getBalance());
    }

    public boolean isBalanceZero() {
        return context.getBalance() == 0;
    }

    public PurchaseResponse purchase(int productNumber) {
        Product product = context.getInventory().getByDisplayNumber(productNumber);
        context.getState().userTriedPurchasing(context, productNumber);

        return new PurchaseResponse(product.getName(), context.getBalance());
    }

    public void depositBalance(int amount) {
        context.getState().userDepositedBalance(context, amount);
    }

    public int withdrawAllBalance() {
        int balance = context.getBalance();
        if (balance > 0) {
            context.getState().userWithdrawnBalance(context, balance);
        }
        return balance;
    }

    public int getInventorySize() {
        return context.getInventory().size();
    }
}
