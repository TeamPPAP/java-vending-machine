package vending_machine;

import vending_machine.service.VendingMachine;
import vending_machine.util.ItemLoader;
import vending_machine.util.input.InputReaderFactory;

import java.util.Scanner;

/**
 * 메인은 도메인(자판기 실행)의 역할을 가지지 않습니다.
 * TODO : 사용자 입력과 도메인은 분리됩니다.
 */
public class Application {
    public static void main(String[] args) {
        ItemLoader itemLoader = new ItemLoader();

        try(Scanner scanner = new Scanner(System.in)){
            VendingMachine vendingMachine = new VendingMachine(
                    itemLoader.getItems(), new InputReaderFactory(scanner));
            vendingMachine.run();
        }
    }
}
