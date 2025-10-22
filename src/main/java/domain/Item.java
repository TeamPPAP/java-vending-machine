package domain;

public class Item {
    private String name;
    private int price;
    private int stock;

    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void purchase() {
        this.stock--;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.price + "원) - " + this.stock + "개";
    }
}
