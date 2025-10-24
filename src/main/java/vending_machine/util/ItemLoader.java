package vending_machine.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vending_machine.domain.Item;

public class ItemLoader {
    public static final String ITEMS_FILE_NAME = "items.json";
    private static final String ITEM_NAME_KEY = "name";
    private static final String ITEM_PRICE_KEY = "price";
    private static final String ITEM_STOCK_KEY = "stock";

    public List<Item> loadItems() {
        Gson gson = new Gson();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ITEMS_FILE_NAME)) {
            validateNullInputStream(inputStream);
            return getItems(inputStream, gson);
        } catch (Exception e) {
            throw new RuntimeException("상품 목록을 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private void validateNullInputStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new RuntimeException("파일을 찾을 수 없습니다.");
        }
    }

    private List<Item> getItems(InputStream inputStream, Gson gson) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            List<Map<String, Object>> rawItems = parseRawItemList(reader, gson);
            return mapToItemList(rawItems);
        }
    }

    private List<Map<String, Object>> parseRawItemList(InputStreamReader reader, Gson gson) {
        Type listType = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        return gson.fromJson(reader, listType);
    }

    private List<Item> mapToItemList(List<Map<String, Object>> rawItemList) {
        List<Item> items = new ArrayList<>();
        for (Map<String, Object> rawItem : rawItemList) {
            items.add(mapToItem(rawItem));
        }
        return items;
    }

    private Item mapToItem(final Map<String, Object> rawItem) {
        String name = (String) rawItem.get(ITEM_NAME_KEY);
        int price = ((Double) rawItem.get(ITEM_PRICE_KEY)).intValue();
        int stock = ((Double) rawItem.get(ITEM_STOCK_KEY)).intValue();
        return Item.from(name, price, stock);
    }
}
