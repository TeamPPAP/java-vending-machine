package domain;

import utilities.JsonLoader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StockHandler {
    private static final String FILE_PATH = "items.json";
    private final List<Item> items;

    public StockHandler() {
        JsonLoader<Item> loader = new JsonLoader<>();
        this.items = loader.load(FILE_PATH);
    }

    public int size() {
        return items.size();
    }

    public Item get(int index) {
        return items.get(index - 1);
    }

    @Override
    public String toString() {
        return IntStream.range(0, items.size())
            .mapToObj(i -> "[" + (i + 1) + "] " + items.get(i))
            .collect(Collectors.joining());
    }
}
