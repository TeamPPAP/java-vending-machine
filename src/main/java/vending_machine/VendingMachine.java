package vending_machine;

public class VendingMachine {
    private Money money;

    public VendingMachine() {
        this.money = new Money();
    }

    String sayHello(){
        return "\uD83E\uDD64 안녕하세요! PPAP 자판기입니다. \uD83E\uDD64";
    }
}
