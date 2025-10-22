package vending_machine.domain;

public class Item {
    private final String name;
    private final int price;
    private int stock;

    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void decreaseStock(int stockCount) {
        this.stock -= stockCount;
    }

    public String getName() {
        return name;
    }
}
