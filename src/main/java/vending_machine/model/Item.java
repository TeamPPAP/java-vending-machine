package vending_machine;

public class Item {
    private int id;
    private String name;
    private int price;
    private int stock;

    // TODO : id생성자 에서 반영하게하고 final처리 할 것.
    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void decreaseStock(){
        this.stock -= 1;
    }

    @Override
    public String toString() {
        return "["+id+"] "+name+" ("+price+"원) - "+stock+"개";
    }
}
