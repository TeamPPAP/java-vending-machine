package vending_machine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        System.out.println(vendingMachine.sayHello());

        ItemLoader itemLoader = new ItemLoader();
        List<Item> itemList = itemLoader.loadItems();

        for(int index = 1; index <= itemList.size() ; index++){
            itemList.get(index-1).setId(index);
        }


    }


}
