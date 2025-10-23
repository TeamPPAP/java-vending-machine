package vending_machine.view;

import static vending_machine.domain.Announce.CashAnnounce.END;
import static vending_machine.domain.Announce.CashAnnounce.INSERT_CASH;
import static vending_machine.domain.Announce.CashAnnounce.NOW_CASH;
import static vending_machine.domain.Announce.MachineAnnounce.DIVIDER_THICK;
import static vending_machine.domain.Announce.MachineAnnounce.DIVIDER_THIN;
import static vending_machine.domain.Announce.MachineAnnounce.START;
import static vending_machine.domain.Announce.MenuAnnounce.ITEM_OUT;
import static vending_machine.domain.Announce.MenuAnnounce.PRINT_INSERT_MENU;
import static vending_machine.domain.Announce.MenuAnnounce.PRINT_MENU;
import static vending_machine.domain.Announce.MenuAnnounce.PRINT_REFUND_MENU;
import static vending_machine.domain.Announce.MenuAnnounce.RETRY;
import static vending_machine.domain.Announce.MenuAnnounce.SELECT_MENU;

import java.util.List;
import vending_machine.domain.Cash;
import vending_machine.domain.Item;

public class LogicView {

    public void printStartMessage() {
        System.out.printf(START.getMessage());
    }

    public void printVendingMachine(List<Item> items, Cash cash) {
        int size = items.size();
        System.out.printf(DIVIDER_THICK.getMessage());

        for (int i = 0; i < items.size(); i++) {
            Item tmp = items.get(i);
            System.out.printf(PRINT_MENU.getMessage(), i + 1, tmp.getName(), tmp.getPrice(), tmp.getStock());
        }

        System.out.printf(DIVIDER_THIN.getMessage());

        if (!cash.isZero()) {
            System.out.printf(PRINT_INSERT_MENU.getMessage(), size + 1);
            System.out.printf(PRINT_REFUND_MENU.getMessage(), size + 2);
        }
    }

    public void printInsertCash() {
        System.out.printf(INSERT_CASH.getMessage());
    }

    public void printCurrentCash(Cash cash) {
        System.out.printf(NOW_CASH.getMessage(), cash.getCash());
    }

    public void printThickDivider() {
        System.out.printf(DIVIDER_THICK.getMessage());
    }

    public void printSelectMenu() {
        System.out.printf(SELECT_MENU.getMessage());
    }

    public void printItemOut(Item item, Cash cash) {
        System.out.printf(ITEM_OUT.getMessage(), item.getName(), cash.getCash());
    }

    public void printExchange(Cash cash) {
        System.out.printf(END.getMessage(), cash.getCash());
    }

    public void printRetry() {
        System.out.println(RETRY.getMessage());
    }
}
