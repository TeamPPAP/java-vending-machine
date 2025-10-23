package machine.vending.domain.state;

import machine.vending.domain.VendingMachineContext;

public interface VendingMachineState {

    void userTriedPurchasing(VendingMachineContext context, int product);

    void userDepositedBalance(VendingMachineContext context, int amount);

    void userWithdrawnBalance(VendingMachineContext context, int amount);

}
