package vending_machine.service;

import static vending_machine.domain.Announce.CashAnnounce.INSERT_CASH;
import static vending_machine.domain.Announce.MachineAnnounce.DIVIDER_THIN;
import static vending_machine.domain.Announce.MenuAnnounce.PRINT_INSERT_MENU;
import static vending_machine.domain.Announce.MenuAnnounce.PRINT_MENU;
import static vending_machine.domain.Announce.MenuAnnounce.PRINT_REFUND_MENU;

import java.util.List;
import vending_machine.domain.Cash;
import vending_machine.domain.Item;

public class MachineService {
    public void printVendingMachine(List<Item> items, Cash cash) {
        int size = items.size();
        for (int i = 0; i < items.size(); i++) {
            Item tmp = items.get(i);
            System.out.printf(PRINT_MENU.getMessage(), i + 1, tmp.getName(), tmp.getPrice(), tmp.getStock());
        }
        if (!cash.isZero()) {
            System.out.printf(PRINT_INSERT_MENU.getMessage(),size+1);
            System.out.printf(PRINT_REFUND_MENU.getMessage(),size+2);
        }
        System.out.printf(DIVIDER_THIN.getMessage());
    }
}
