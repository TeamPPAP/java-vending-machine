package vending_machine;

public interface VendingMachineState {

    public void userTriedPurchasing(VendingMachineContext context, int product);

    public void userDepositedBalance(VendingMachineContext context, int amount);

    public void userWithdrawnBalance(VendingMachineContext context, int amount);

}
