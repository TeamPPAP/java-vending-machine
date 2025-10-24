package vending_machine.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import vending_machine.domain.Item;

public class ItemLoader {
    public static final String ITEMS_FILE_NAME = "items.json";

    public List<Item> loadItems() {
        try {
            List<ItemRawDTO> rawItems = parseItemsFile();
            return mapToItems(rawItems);
        } catch (IOException e) {
            throw new RuntimeException("상품 목록을 불러오는 중 오류가 발생했습니다.", e);
        }
    }

    private List<ItemRawDTO> parseItemsFile() throws IOException {
        Gson gson = new Gson();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ITEMS_FILE_NAME)) {
            validateFoundFile(inputStream);
            try (InputStreamReader reader = new InputStreamReader(inputStream)) {
                Type listType = new TypeToken<List<ItemRawDTO>>() {
                }.getType();
                return gson.fromJson(reader, listType);
            }
        }
    }

    private record ItemRawDTO(String name, double price, double stock) {
        public Item toItem() {
            return Item.from(name, (int) price, (int) stock);
        }
    }

    private List<Item> mapToItems(List<ItemRawDTO> rawItems) {
        return rawItems.stream()
                .map(ItemRawDTO::toItem)
                .collect(Collectors.toList());
    }

    private void validateFoundFile(final InputStream inputStream) {
        if (inputStream == null) {
            throw new RuntimeException("'" + ITEMS_FILE_NAME + "' 파일을 찾을 수 없습니다.");
        }
    }
}
