package domain;

public class Product {
    private final String name;
    private final int price;
    private final int stock;

    public Product(String name, int price, int stock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("재고는 음수일 수 없습니다.");
        }
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

    public boolean hasStock() {
        return stock > 0;
    }

    public Product decreaseStock() {
        if (stock <= 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        return new Product(name, price, stock - 1);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.price + "원) - " + this.stock + "개";
    }
}
