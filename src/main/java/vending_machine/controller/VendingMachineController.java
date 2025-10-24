package vending_machine.controller;

import java.util.List;
import java.util.function.Supplier;
import vending_machine.controller.dto.ItemDTO;
import vending_machine.domain.Item;
import vending_machine.domain.Money;
import vending_machine.domain.VendingMachine;
import vending_machine.util.InputParser;
import vending_machine.view.InputView;
import vending_machine.view.OutputView;


public class VendingMachineController {
    public static final int ADD_MONEY_CHOICE = 6;
    public static final int RETURN_CHANGE_CHOICE = 7;

    private final VendingMachine vendingMachine;
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(VendingMachine vendingMachine, InputView inputView, OutputView outputView) {
        this.vendingMachine = vendingMachine;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        displayItems();
        setupInitialMoney();
        runPurchaseLoop();
    }

    private void setupInitialMoney() {
        Money initialMoney = getValidInput(inputView::getInitialMoney);
        vendingMachine.insertMoney(initialMoney);
    }

    private void runPurchaseLoop() {
        while (true) {
            try {
                outputView.printCurrentMoney(vendingMachine.getCurrentMoney());
                displayItems();
                outputView.printPurchaseMenu();

                int choice = getValidInput(inputView::getItemToPurchase);
                if (handleChoice(choice)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean handleChoice(int choice) {
        if (choice == ADD_MONEY_CHOICE) {
            addMoney();
            return false;
        }
        if (choice == RETURN_CHANGE_CHOICE) {
            returnChangeAndEndLoop();
            return true;
        }
        return handlePurchase(choice);
    }

    private void addMoney() {
        Money moneyToAdd = getValidInput(inputView::getInitialMoney);
        vendingMachine.insertMoney(moneyToAdd);
    }

    private boolean handlePurchase(int choice) {
        processPurchase(choice);
        if (!askForContinue()) {
            returnChangeAndEndLoop();
            return true;
        }
        return false;
    }

    private void processPurchase(int itemIndex) {
        vendingMachine.purchaseItem(itemIndex);
        Item purchasedItem = vendingMachine.getItems().get(itemIndex - 1);
        outputView.printPurchaseSuccess(purchasedItem.getName(), vendingMachine.getCurrentMoney());
    }

    private boolean askForContinue() {
        String choice = getValidInput(inputView::getContinueChoice);
        return choice.equals(InputParser.RETRY_ACCEPTED_COMMAND);
    }

    private void returnChangeAndEndLoop() {
        Money change = vendingMachine.returnChange();
        outputView.printReturnChange(change);
    }

    private void displayItems() {
        List<Item> items = vendingMachine.getItems();
        List<ItemDTO> itemDTOs = items.stream()
                .map(item -> new ItemDTO(item.getName(), item.getPrice().amount(), item.getStock().getQuantity()))
                .toList();
        outputView.printItemList(itemDTOs);
    }

    private <T> T getValidInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
