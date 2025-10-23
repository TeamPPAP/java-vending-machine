package vending_machine.controller;

import java.security.InvalidKeyException;
import vending_machine.domain.Item;
import vending_machine.service.MachineService;
import vending_machine.util.Validator;
import vending_machine.view.ExceptionView;
import vending_machine.view.InputView;
import vending_machine.view.LogicView;

public class MachineController {
    private final MachineService ms;
    private final InputView inputView;
    private final LogicView logicView;
    private final ExceptionView exceptionView;

    public MachineController() {
        this.ms = new MachineService();
        this.inputView = new InputView();
        this.logicView = new LogicView();
        this.exceptionView = new ExceptionView();
    }

    public void run() {
        machineStart();
        boolean isRunning = true;

        while (isRunning) {
            printCurrent();
            int choice = selectMenu();
            isRunning = optionalMenuChoice(choice);

            if (!isRunning) {
                break;
            }
            isRunning = retry();
        }

        logicView.printExchange(ms.getCash());
        inputView.close();
    }

    private void machineStart() {
        logicView.printStartMessage();
        logicView.printVendingMachine(ms.getItems(), ms.getCash());
        insertCash();
    }

    private void printCurrent() {
        logicView.printCurrentCash(ms.getCash());
        logicView.printVendingMachine(ms.getItems(), ms.getCash());
        logicView.printThickDivider();
    }

    private void insertCash() {
        boolean inputSuccess = false;
        while (!inputSuccess) {
            try {
                logicView.printInsertCash();
                String input = inputView.readInput();
                ms.insertCash(input);
                logicView.printCurrentCash(ms.getCash());
                inputSuccess = true;
            } catch (IllegalArgumentException e) {
                exceptionView.printError(e.getMessage());
            }
        }
    }

    private int selectMenu() {
        logicView.printSelectMenu();
        while (true) {
            try {
                String input = inputView.readInput();
                int choice = Validator.validateInt(input);
                Validator.validateMenuChoice(choice, ms.getMenuSize());
                return choice;
            } catch (IllegalArgumentException e) {
                exceptionView.printError(e.getMessage());
            }
        }
    }

    private boolean optionalMenuChoice(int choice) {
        int menuSize = ms.getMenuSize();

        if (choice == menuSize) {
            return false;
        }

        if (choice == (menuSize - 1)) {
            insertCash();

            return true;
        }

        purchaseItem(choice);
        return true;
    }

    private void purchaseItem(int choice) {
        try {
            Item purchasedItem = ms.purchaseItem(choice);
            logicView.printItemOut(purchasedItem, ms.getCash());
        } catch (InvalidKeyException | RuntimeException e) {
            exceptionView.printError(e.getMessage());
        }
    }

    private boolean retry() {
        logicView.printRetry();
        while (true) {
            try {
                String input = inputView.readInput();
                Validator.validateRetry(input);
                return input.equalsIgnoreCase("y");
            } catch (IllegalArgumentException e) {
                exceptionView.printError(e.getMessage());
            }
        }
    }
}