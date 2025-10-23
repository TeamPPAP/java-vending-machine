package view;

import domain.StockHandler;

public class OutputView {

    public void printWelcome() {
        System.out.println("🥤 안녕하세요! PPAP 자판기입니다. 🥤");
    }

    public void printMenu(StockHandler stockHandler, int balance) {
        System.out.println();
        if (balance > 0) {
            System.out.println("현재 투입된 금액: " + balance + "원");
        }
        System.out.println("===============================");
        System.out.println(stockHandler.toString());
        System.out.println("-------------------------------");
        
        if (balance > 0) {
            System.out.println("[6] 금액 추가 투입");
            System.out.println("[7] 금액 반환");
            System.out.println("===============================");
        }
    }

    public void printPurchaseSuccess(String itemName, int change) {
        System.out.println();
        System.out.println("\"" + itemName + "\"가 나왔습니다! (잔액: " + change + "원)");
    }

    public void printReturnBalance(int balance) {
        System.out.println();
        System.out.println("거스름돈 " + balance + "원이 반환되었습니다.");
    }

    public void printError(String message) {
        System.out.println();
        System.out.println(message);
    }

    public void printInsufficientBalance(int shortage) {
        System.out.println();
        System.out.println("금액이 부족합니다. (부족한 금액: " + shortage + "원)");
    }
}
