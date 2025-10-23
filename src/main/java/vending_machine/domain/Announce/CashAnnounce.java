package vending_machine.domain.Announce;

public enum CashAnnounce {
    INSERT_CASH("투입할 금액을 입력해주세요 : \n"),
    NOW_CASH("현재 투입된 금액 : %d\n"),
    END("거스름돈 %d원이 반환되었습니다.\n");

    private final String message;

    CashAnnounce(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
