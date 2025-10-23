package vending_machine;

import domain.Product;
import domain.StockHandler;
import exception.InsufficientBalanceException;
import exception.NoBalanceException;
import exception.OutOfStockException;
import view.InputView;
import view.OutputView;

public class VendingMachine {

    private final VendingMachineContext context;
    private final InputView inputView;
    private final OutputView outputView;


    public VendingMachine() {
        StockHandler stockHandler = new StockHandler();
        this.context = new VendingMachineContext(stockHandler);
        this.inputView = new InputView();
        this.outputView = new OutputView();
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
        outputView.printMenu(context.getStockHandler(), context.getBalance());
        
        if (context.getBalance() == 0) {
            int money = inputView.readMoney();
            depositBalance(money);
        }
    }

    private void handleUserActions() {
        int choice = inputView.readMenuChoice();
        
        if (choice == 6) {
            handleAddMoney();
            return;
        }
        
        if (choice == 7) {
            handleReturnMoney();
            return;
        }
        
        handlePurchase(choice);
    }

    private void handleAddMoney() {
        int money = inputView.readMoney();
        depositBalance(money);
    }

    private void handleReturnMoney() {
        int balance = context.getBalance();
        withdrawAllBalance();
        outputView.printReturnBalance(balance);
    }

    private void handlePurchase(int productNumber) {
        try {
            executePurchase(productNumber);
            handleAfterPurchase();
        } catch (InsufficientBalanceException e) {
            outputView.printInsufficientBalance(e.getShortage());
        } catch (OutOfStockException e) {
            outputView.printError(e.getMessage());
        } catch (NoBalanceException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void executePurchase(int productNumber) {
        Product product = context.getStockHandler().getByDisplayNumber(productNumber);
        purchase(productNumber);
        outputView.printPurchaseSuccess(product.getName(), context.getBalance());
    }

    private void handleAfterPurchase() {
        if (context.getBalance() == 0) {
            return;
        }
        
        boolean continuePurchase = inputView.readContinuePurchase();
        if (continuePurchase) {
            return;
        }
        
        handleReturnMoney();
    }

    private void depositBalance(int amount) {
        context.getState().userDepositedBalance(context, amount);
    }

    private void purchase(int item) {
        context.getState().userTriedPurchasing(context, item);
    }

    private void withdrawAllBalance() {
        int balance = context.getBalance();
        if (balance > 0) {
            context.getState().userWithdrawnBalance(context, balance);
        }
    }

}
