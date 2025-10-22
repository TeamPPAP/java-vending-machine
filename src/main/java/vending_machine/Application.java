package vending_machine;

import java.util.List;
import java.util.Scanner;
import vending_machine.Util.Util;
import vending_machine.Util.Validator;
import vending_machine.domain.Cash;
import vending_machine.domain.Item;
import vending_machine.io.InputFile;
import vending_machine.view.MenuList;

public class Application {
    public static void main(String[] args) {
        InputFile inputFile = new InputFile();
        List<Item> items = inputFile.loadItems();
        MenuList menuList = new MenuList();
        Validator validator = new Validator();
        Util util = new Util();
        Scanner sc = new Scanner(System.in);
        Cash cash = new Cash(0);
        int menuSize = items.size();
        int exchange;
        do {
            for (int i = 0; i < items.size(); i++) {
                Item tmp = items.get(i);
                System.out.printf("[%d] %s (%d원) - %d개\n", i + 1, tmp.getName(), tmp.getPrice(), tmp.getStock());
            }
            if (!cash.isZero()) {
                menuList.printOptionalMenu(menuSize);
            }
            System.out.println("투입할 금액을 입력해주세요 : ");
            String input;
            do {
                input = sc.nextLine();
            } while (validator.cashValidate(input));
            int money = cash.updateCash(Integer.parseInt(input)).getCash();

            System.out.println("-------------------------------");
            System.out.println("구입할 상품 번호를 입력하세요 : ");

            String choice;
            do {
                choice = sc.nextLine();
                validator.intValidate(input);
            } while (validator.selectValidate(Integer.parseInt(choice), menuSize + 2));

            int select = Integer.parseInt(choice);

            Item choiceItem = null;

            if (select == (menuSize + 1)) {
                System.out.println("돈 넣어");

                int don = Integer.parseInt(sc.nextLine());
                Cash c = cash.updateCash(don);

                System.out.println("현재 투입된 금액 : " + c.getCash());
            } else if (select == (menuSize + 2)) {
                System.out.println("거스름돈 : " + cash.getCash());
                return;
            } else {
                choiceItem = items.get(select - 1);
            }
            //TODO 이이상 입력 받으면 쌍욕박기

            choiceItem.setStock(choiceItem.getStock() - 1);

            if (choiceItem.getStock() <= 0) {
                System.out.println("물건 없어 새끼야.");
            }

            exchange = cash.getCash() - choiceItem.getPrice();

            if (exchange < 0) {
                System.out.println("꺼져 거지야");
                System.out.println("거스름돈 : " + cash.getCash());
                continue;
            }

            cash.setCash(exchange);
            System.out.println(choiceItem.getName() + "이 나왔엉 (거스름돈 : " + exchange + ")");

        } while (util.retry(exchange));

    }
}
