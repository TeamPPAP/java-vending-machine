package vending_machine.controller;

import static vending_machine.domain.Announce.CashAnnounce.INSERT_CASH;
import static vending_machine.domain.Announce.CashAnnounce.NOW_CASH;
import static vending_machine.domain.Announce.MachineAnnounce.DIVIDER_THICK;
import static vending_machine.domain.Announce.MachineAnnounce.DIVIDER_THIN;
import static vending_machine.domain.Announce.MachineAnnounce.START;
import static vending_machine.domain.Announce.MenuAnnounce.SELECT_MENU;
import static vending_machine.util.Util.retry;
import static vending_machine.util.Validator.intValidate;
import static vending_machine.util.Validator.selectValidate;

import java.util.List;
import java.util.Scanner;
import vending_machine.domain.Cash;
import vending_machine.domain.Item;
import vending_machine.service.MachineService;
import vending_machine.view.MenuList;

public class MachineController {
    MenuList menuList = new MenuList();
    MachineService ms = new MachineService();

    //TODO 자판기 로직 (예: 선택, 추가 입금~~~,출력 호출)
    public void run(List<Item> items, Scanner sc) {
        Cash cash = Cash.forCreate(0);
        int menuSize=0;
        int exchange;
        int cnt = 0;
        do {
            if (cnt == 0) {
                System.out.printf(START.getMessage());
                ms.printVendingMachine(items, cash);
                System.out.printf(DIVIDER_THIN.getMessage());
                System.out.printf(INSERT_CASH.getMessage());
                menuSize = items.size();
            }
            if(cnt>=1|| !cash.isZero()){
                System.out.printf(NOW_CASH.getMessage(),cash.getCash());
                ms.printVendingMachine(items, cash);
                System.out.printf(DIVIDER_THIN.getMessage());
                menuSize = (items.size())+2;
            }
            boolean inputSuccess = false;
            do {
                try {
                    String input = sc.nextLine();
                    cash.insertCash(input);
                    inputSuccess = true;
                } catch (IllegalArgumentException e) {
                    System.err.printf(e.getMessage());
                }

            } while (!inputSuccess);

            System.out.printf(NOW_CASH.getMessage(), cash.getCash());
            System.out.printf(DIVIDER_THICK.getMessage());
            ms.printVendingMachine(items, cash);
            System.out.printf(SELECT_MENU.getMessage());

            String choice;
            do {
                choice = sc.nextLine();
                intValidate(input);
            } while (selectValidate(Integer.parseInt(choice), menuSize + 2));

            int select = Integer.parseInt(choice);

            Item choiceItem = null;

            if (select == (menuSize + 1)) {
                System.out.println("돈 넣어");

                String don = sc.nextLine();
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
            cnt++;
        } while (retry(exchange));

    }
}
