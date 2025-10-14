package vending_machine.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import vending_machine.domain.Item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class ItemLoader {
    private static final String ITEMS_FILE_NAME = "items.json";

    public static List<Item> load() {
        Gson gson = new Gson();

        InputStream inputStream = ItemLoader.class.getClassLoader().getResourceAsStream(ITEMS_FILE_NAME);
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(inputStream));

        Type itemListType = new TypeToken<List<Item>>() {
        }.getType();

        return gson.fromJson(reader, itemListType);
    }
}
