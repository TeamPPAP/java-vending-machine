package vending_machine.service;

import java.security.InvalidKeyException;
import java.util.List;
import vending_machine.domain.Cash;
import vending_machine.domain.Item;
import vending_machine.io.InputFile; // Assuming io.InputFile exists

public class MachineService {
    private final List<Item> items;
    private final Cash cash;

    public MachineService() {
        InputFile inputFile = new InputFile();
        this.items = inputFile.loadItems();
        this.cash = Cash.forCreate(0);
    }

    public List<Item> getItems() {
        return items;
    }

    public Cash getCash() {
        return cash;
    }

    public int getMenuSize() {
        return items.size() + 2;
    }

    public void insertCash(String input) throws IllegalArgumentException {
        cash.insertCash(input);
    }

    public Item purchaseItem(int choiceIndex) throws IllegalArgumentException, InvalidKeyException {
        Item selectedItem = items.get(choiceIndex - 1);
        selectedItem.decreaseStock();
        cash.purchaseCash(selectedItem);
        return selectedItem;
    }
}