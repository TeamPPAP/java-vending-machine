package vending_machine.domain;

import static vending_machine.domain.Announce.ErrorAnnounce.SOLD_OUT;

public class Item {
    String name;
    int price;
    int stock;

    public Item(String name, int price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public boolean decreaseStock() {
        if(this.stock>0){
            stock--;
            return false;
        }
        throw new IllegalArgumentException(SOLD_OUT.getMessage());
    }
}
