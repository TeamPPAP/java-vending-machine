package vending_machine.controller;

import static vending_machine.util.InputParser.ADD_MONEY_COMMAND;
import static vending_machine.util.InputParser.RETURN_CHANGE_COMMAND;

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

                String choice = getValidInput(inputView::getMenuChoice);
                if (handleChoice(choice)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean handleChoice(String choice) {
        if (choice.equals(ADD_MONEY_COMMAND)) {
            addMoney();
            return false;
        }
        if (choice.equals(RETURN_CHANGE_COMMAND)) {
            returnChangeAndEndLoop();
            return true;
        }
        return handlePurchase(choice);
    }

    private void addMoney() {
        Money moneyToAdd = getValidInput(inputView::getInitialMoney);
        vendingMachine.insertMoney(moneyToAdd);
    }

    private boolean handlePurchase(String choice) {
        int itemIndex = Integer.parseInt(choice);
        processPurchase(itemIndex);
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
