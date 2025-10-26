package vending_machine.domain;

public class Item {
    private final String name;
    private final Money price;
    private final Stock stock;

    private Item(String name, Money price, Stock stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static Item from(String name, int price, int stock) {
        return new Item(name, new Money(price), new Stock(stock));
    }

    public void decreaseStock() {
        this.stock.decrease();
    }

    public boolean hasStock() {
        return this.stock.isAvailable();
    }

    public boolean isPurchasable(Money currentMoney) {
        return currentMoney.isGreaterThanOrEqualTo(this.price);
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }
}
