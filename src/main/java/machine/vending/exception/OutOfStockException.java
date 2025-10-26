package machine.vending.exception;

public class OutOfStockException extends VendingMachineException {
    private final String itemName;

    public OutOfStockException(String itemName) {
        super("재고가 부족합니다: " + itemName);
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
