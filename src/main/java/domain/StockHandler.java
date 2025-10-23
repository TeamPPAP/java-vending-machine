package domain;

import infrastructure.JsonLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StockHandler {
    private final String filePath = "items.json";
    private final List<Product> products;

    public StockHandler() {
        JsonLoader<Product> loader = new JsonLoader<>(Product.class);
        this.products = new ArrayList<>(loader.load(filePath));
    }

    public int size() {
        return products.size();
    }

    public Product getByDisplayNumber(int displayNumber) {
        validateDisplayNumber(displayNumber);
        return products.get(displayNumber - 1);
    }

    public void purchase(int displayNumber) {
        validateDisplayNumber(displayNumber);
        int index = displayNumber - 1;
        Product currentProduct = products.get(index);
        products.set(index, currentProduct.decreaseStock());
    }

    private void validateDisplayNumber(int displayNumber) {
        if (displayNumber < 1 || displayNumber > products.size()) {
            throw new IllegalArgumentException("유효하지 않은 상품 번호입니다.");
        }
    }

    @Override
    public String toString() {
        return IntStream.range(0, products.size())
                .mapToObj(i -> "[" + (i + 1) + "] " + products.get(i))
                .collect(Collectors.joining("\n"));
    }

}
