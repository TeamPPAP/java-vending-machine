package vending_machine.view;

public class ExceptionView {

    public void printError(String message) {
        System.err.println(message);
    }

    public void printError(String message, Object... args) {
        System.err.printf(message, args);
    }
}
