package exception;

public class NoBalanceException extends VendingMachineException {
    public NoBalanceException() {
        super("투입된 금액이 없습니다.");
    }
}
