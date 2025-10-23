package vending_machine.domain.Announce;

public enum MenuAnnounce {
    PRINT_MENU("[%d] %s (%d원) - %d개\n"),
    PRINT_INSERT_MENU("[%d] 금액 추가 투입\n"),
    PRINT_REFUND_MENU("[%d] 금액 반환\n"),
    RETRY("추가 구매를 하시겠습니까? (Y/N)\n"),
    ITEM_OUT("\"%s\"가 나왔습니다! (거스름돈: %d원)\n"),
    SELECT_MENU("구입할 상품 번호를 입력하세요 : \n"),

    ;

    private final String message;

    MenuAnnounce(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
