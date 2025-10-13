package vending_machine.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import vending_machine.domain.Item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class InputFile {
    // 파일 읽는 로직
    public List<Item> loadItems() {
        Gson gson = new Gson();

        // resources 폴더의 items.json 파일을 읽어옴
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("items.json");
        InputStreamReader reader = new InputStreamReader(inputStream);

        // JSON 배열을 List<Item>으로 변환
        Type itemListType = new TypeToken<List<Item>>() {
        }.getType();
        return gson.fromJson(reader, itemListType);
    }
}
