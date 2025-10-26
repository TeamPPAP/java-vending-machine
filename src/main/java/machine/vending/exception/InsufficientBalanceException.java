package machine.vending.exception;

public class InsufficientBalanceException extends VendingMachineException {
    private final int currentBalance;
    private final int requiredAmount;

    public InsufficientBalanceException(int currentBalance, int requiredAmount) {
        super("금액이 부족합니다. (부족한 금액: " + (requiredAmount - currentBalance) + "원)");
        this.currentBalance = currentBalance;
        this.requiredAmount = requiredAmount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public int getShortage() {
        return requiredAmount - currentBalance;
    }
}
