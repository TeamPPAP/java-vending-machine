package vending_machine.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import vending_machine.model.Item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * main() 로직에서 ItemLoader를 가독성을 높이고 비즈니스를 분리하기 위해 캡슐화했습니다.
 * 외부에서는 getItems()으로만 접근을 허용함으로써 아이템 load 로직을 분리시켰습니다.
 */
public class ItemLoader {
    /**
     * 인덱스가 부여된 메뉴 아이템 리스트를 반환합니다.
     * @return
     */
    public List<Item> getItems() {
        return setItemId(loadItems());
    }

    /**
     * 메뉴 아이템 읽어옵니다.
     * @return
     */
    private List<Item> loadItems() {
        Gson gson = new Gson();

        // resources 폴더의 items.json 파일을 읽어옴
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("items.json");
        InputStreamReader reader = new InputStreamReader(inputStream);

        // JSON 배열을 List<Item>으로 변환
        Type itemListType = new TypeToken<List<Item>>() {}.getType();

        return gson.fromJson(reader, itemListType);
    }

    /**
     * 메뉴 아이템에 인덱스를 부여합니다.
     */
    private List<Item> setItemId(List<Item> itemList) {
        for(int index = 1 ; index <= itemList.size(); index++){
            itemList.get(index -1).setId(index);
        }
        return itemList;
    }

}
