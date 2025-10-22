package vending_machine;

public class AdaptVendingMachine {

    private VendingMachineState vendingMachineState;

    public AdaptVendingMachine() {
        this.vendingMachineState = NotEnoughBalanceState.getInstance();
        // 초기 세팅
        // 인벤토리 적재
    }

    public void setVendingMachineState(VendingMachineState state) {
        this.vendingMachineState = state;
    }

    public void userTriedPurchasing() {
        vendingMachineState.userTriedPurchasing();
        // 구매 로직
    }


    public void userDepositedBalance() {
        vendingMachineState.userDepositedBalance();
        this.setVendingMachineState(EnoughBalanceState.getInstance());
    }


    public void userWithdrawnBalance() {
        vendingMachineState.userWithdrawnBalance();
        this.setVendingMachineState(NotEnoughBalanceState.getInstance());
    }




}
