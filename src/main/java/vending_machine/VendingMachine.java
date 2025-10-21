package vending_machine;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine {
    CreditState creditState;

    BigDecimal balance;

    public VendingMachine() {
        this.balance = BigDecimal.ZERO;
        creditState = CreditState.NO_CREDIT;
    }

    String sayHello(){
        return "\uD83E\uDD64 안녕하세요! PPAP 자판기입니다. \uD83E\uDD64";
    }


    String showMenu(List<Item> itemList){
        String menu =  "====================================\n";

        for(Item item : itemList){
            menu = menu + item.toString() + "\n";
        }

        menu = menu + "-----------------------------------\n";

        if(CreditState.HAS_CREDIT.equals(this.creditState))
            menu = menu + "[6] 금액 추가 투입\n" + "[7] 금액 반환\n" + "====================================\n";

        return menu;
    }

    void turnCreditOn(){
        creditState = CreditState.HAS_CREDIT;
    }

    void turnCreditOff(){
        creditState = CreditState.NO_CREDIT;
    }


    void credit(BigDecimal amount){
        this.balance = this.balance.add(amount);
    }

    void debit(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }

    void refund(){
        System.out.println(this.balance + "원이 반환되었습니다.");
        this.balance = BigDecimal.ZERO;
    }

}
