package vending_machine.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String readItemNumber() {
        return scanner.nextLine();
    }

    public String readMoney() {
        return scanner.nextLine();
    }

    public String readMorePurchases() {
        return scanner.nextLine();
    }
}
