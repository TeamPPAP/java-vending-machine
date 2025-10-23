package vending_machine;

import vending_machine.model.Item;
import vending_machine.service.VendingMachine;
import vending_machine.util.ItemLoader;

import java.util.List;
import java.util.Scanner;

/**
 * 메인은 도메인(자판기 실행)의 역할을 가지지 않습니다.
 * TODO : 사용자 입력과 도메인은 분리됩니다.
 */
public class Application {
    static Scanner scanner;

    public static void main(String[] args) {
        ItemLoader itemLoader = new ItemLoader();
        VendingMachine vendingMachine = new VendingMachine(itemLoader.getItems());
        scanner = new Scanner(System.in);

        vendingMachine.run(scanner);

        List<Item> itemList = itemLoader.getItems();







    }


}
