package vending_machine.model;

import vending_machine.util.input.InputReaderFactory;

import java.math.BigDecimal;

public class Money {
    private CreditState creditState;
    private BigDecimal balance;
    private InputReaderFactory reader;

    public Money(InputReaderFactory inputReaderFactory) {
        balance = BigDecimal.ZERO;
        creditState = CreditState.NO_CREDIT;
        reader = inputReaderFactory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    //TODO : 금액 투입 + 필드 반영을 위한 적절한 명칭 변경 예정
    public void askForAmount() {
        System.out.print("투입할 금액을 입력해주세요 : ");
        credit(BigDecimal.valueOf(reader.positiveInteger().read()));
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.turnCreditOn();
            this.balance = this.balance.add(amount);
        }
    }

    public void debit(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }

    public void refund(){
        System.out.println(this.balance + "원이 반환되었습니다.");
        this.balance = BigDecimal.ZERO;
    }

    void turnCreditOn(){
        creditState = CreditState.HAS_CREDIT;
    }

    void turnCreditOff(){
        creditState = CreditState.NO_CREDIT;
    }

    public CreditState getCreditState() {
        return creditState;
    }

    public void printBalance() {
        if(CreditState.HAS_CREDIT.equals(this.creditState))
            System.out.println("현재 투입된 금액 : " + this.balance + "원" );
    }

    public String getChangeBalance() {
        return "(거스름돈:\"" + this.balance+ "\"원)";
    }

}
