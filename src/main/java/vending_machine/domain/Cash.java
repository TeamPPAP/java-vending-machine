package vending_machine.domain;

public class Cash {
    private int cash=0;

    private Cash() {
    }

    public Cash(int cash) {
        this.cash = cash;
    }

//    public Cash forCreate(int cash) {
//        return new Cash(cash);
//    }

    public Cash updateCash(int cash) {
        this.cash += cash;
        validCash();
        return new Cash(cash);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isZero() {
        return cash <= 0;
    }

    private void validCash() {
        if(isZero()){
            throw new IllegalArgumentException("돈을 투입하세요.");
        }
    }

}
