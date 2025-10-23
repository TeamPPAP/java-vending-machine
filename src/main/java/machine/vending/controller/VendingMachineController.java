package machine.vending.controller;

import machine.vending.controller.dto.ContextInfo;
import machine.vending.controller.dto.PurchaseResponse;
import machine.vending.controller.value.MenuAction;
import machine.vending.exception.InsufficientBalanceException;
import machine.vending.exception.NoBalanceException;
import machine.vending.exception.OutOfStockException;
import machine.vending.service.VendingMachineService;
import machine.vending.view.InputView;
import machine.vending.view.OutputView;

public class VendingMachineController {

    private final VendingMachineService vendingMachineService;
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.vendingMachineService = new VendingMachineService();
    }

    public void run() {
        outputView.printWelcome();

        while (true) {
            try {
                showMenuAndGetMoney();
                handleUserActions();
            } catch (Exception e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void showMenuAndGetMoney() {
        ContextInfo info = vendingMachineService.findContext();
        outputView.printMenu(info.inventory(), info.balance());

        if (vendingMachineService.isBalanceZero()) {
            int money = inputView.readMoney();
            vendingMachineService.depositBalance(money);
        }
    }

    private void handleUserActions() {
        int choice = inputView.readMenuChoice();
        int size = vendingMachineService.getInventorySize();

        MenuAction menuAction = MenuAction.from(choice, size);

        switch (menuAction) {
            case ADD_MONEY -> handleAddMoney();
            case RETURN_MONEY -> handleReturnMoney();
            case PURCHASE -> handlePurchase(choice);
        }
    }

    private void handleAddMoney() {
        int money = inputView.readMoney();
        vendingMachineService.depositBalance(money);
    }

    private void handleReturnMoney() {
        int balance = vendingMachineService.withdrawAllBalance();
        outputView.printReturnBalance(balance);
    }

    private void handlePurchase(int productNumber) {
        try {
            executePurchase(productNumber);
            handleAfterPurchase();
        } catch (InsufficientBalanceException e) {
            outputView.printInsufficientBalance(e.getShortage());
        } catch (OutOfStockException | NoBalanceException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void executePurchase(int productNumber) {
        PurchaseResponse response = vendingMachineService.purchase(productNumber);
        outputView.printPurchaseSuccess(response.name(), response.balance());
    }

    private void handleAfterPurchase() {
        if (vendingMachineService.isBalanceZero()) {
            return;
        }

        boolean continuePurchase = inputView.readContinuePurchase();
        if (!continuePurchase) {
            handleReturnMoney();
        }
    }
}
