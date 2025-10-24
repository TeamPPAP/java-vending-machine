package vending_machine.controller;

import java.security.InvalidKeyException;
import vending_machine.domain.Announce.State;
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
    private State machineState = State.FIRST_ON;

    public MachineController() {
        this.ms = new MachineService();
        this.inputView = new InputView();
        this.logicView = new LogicView();
        this.exceptionView = new ExceptionView();
    }

    public void run() {
        machineStart();
        State isRunning = State.KEEP_GOING;
        machineState = State.NOT_FIRST_ON;

        while (isRunning != State.OFF) {
            printCurrent();
            int choice = selectMenu();
            isRunning = choiceMenuLogic(choice);
        }
        logicView.printExchange(ms.getCash());
        inputView.close();
    }

    private State choiceMenuLogic(int choice) {
        try {
            State currentState = optionalMenuChoice(choice);
            if (currentState == State.OFF) {
                return currentState;
            }

            if (choice == ms.getMenuSize() - 1) {
                return State.KEEP_GOING;
            }

            return retry();

        } catch (IllegalArgumentException | InvalidKeyException e) {
            exceptionView.printError(e.getMessage());
            return State.KEEP_GOING;
        }
    }

    private void machineStart() {
        logicView.printStartMessage();
        logicView.printVendingMachine(ms.getItems(), ms.getCash(), machineState);
        insertCash();
    }

    private void printCurrent() {
        logicView.printCurrentCash(ms.getCash());
        logicView.printVendingMachine(ms.getItems(), ms.getCash(), machineState);
        logicView.printThickDivider();
    }

    private void insertCash() throws IllegalArgumentException {
        boolean inputSuccess = false;
        while (!inputSuccess) {
            try {
                logicView.printInsertCash();
                String input = inputView.readInput();
                ms.insertCash(input);

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

    private State optionalMenuChoice(int choice) throws IllegalArgumentException, InvalidKeyException {
        int menuSize = ms.getMenuSize();
        if (choice == menuSize) {
            return State.OFF;
        }
        if (choice == (menuSize - 1)) {
            insertCash();
            return State.KEEP_GOING;
        }
        purchaseItem(choice);
        return State.KEEP_GOING;
    }

    private void purchaseItem(int choice) throws InvalidKeyException {
        Item purchasedItem = ms.purchaseItem(choice);
        logicView.printItemOut(purchasedItem, ms.getCash());
    }

    private State retry() {
        logicView.printRetry();
        while (true) {
            try {
                String input = inputView.readInput();
                Validator.validateRetry(input);
                if (input.equalsIgnoreCase("y")) {
                    return State.KEEP_GOING;
                }
                return State.OFF;
            } catch (IllegalArgumentException e) {
                exceptionView.printError(e.getMessage());
            }
        }
    }
}