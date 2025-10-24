package vending_machine.controller;

import java.util.List;
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
        printItemsAsDTO();
        setupInitialMoney();
        runPurchaseLoop();
    }

    private void setupInitialMoney() {
        while (true) {
            try {
                Money initialMoney = inputView.getInitialMoney();
                vendingMachine.insertMoney(initialMoney);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void runPurchaseLoop() {
        while (true) {
            try {
                outputView.printCurrentMoney(vendingMachine.getCurrentMoney());
                printItemsAsDTO();
                outputView.printPurchaseMenu();

                int choice = inputView.getItemToPurchase();

                if (choice == 6) {
                    addMoney();
                    continue;
                }
                if (choice == 7) {
                    returnChangeAndEndLoop();
                    return;
                }

                vendingMachine.purchaseItem(choice);
                Item purchasedItem = vendingMachine.getItems().get(choice - 1);
                outputView.printPurchaseSuccess(purchasedItem.getName(), vendingMachine.getCurrentMoney());

                if (!askForContinue()) {
                    returnChangeAndEndLoop();
                    return;
                }
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void addMoney() {
        Money moneyToAdd = inputView.getInitialMoney();
        vendingMachine.insertMoney(moneyToAdd);
    }

    private boolean askForContinue() {
        while (true) {
            try {
                String choice = inputView.getContinueChoice();
                return choice.equals(InputParser.RETRY_ACCEPTED_COMMAND);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void returnChangeAndEndLoop() {
        Money change = vendingMachine.returnChange();
        outputView.printReturnChange(change);
    }

    private void printItemsAsDTO() {
        List<Item> items = vendingMachine.getItems();
        List<ItemDTO> itemDTOs = items.stream()
                .map(item -> new ItemDTO(
                        item.getName(),
                        item.getPrice().amount(),
                        item.getStock().getQuantity())
                )
                .toList();

        outputView.printItemList(itemDTOs);
    }
}
