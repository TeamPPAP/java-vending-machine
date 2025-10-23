package vending_machine.domain.Announce;

public enum ErrorAnnounce {
    NOT_ENOUGH_CASH("금액이 부족합니다. (부족한 금액: %d원)\n"),
    SOLD_OUT("해당 품목은 품절 되었습니다.\n"),
    GO_AWAY_POOR("현재 금액이 없습니다. 돈을 넣어주세요\n"),
    INSERT_MORE_CASH("투입한 금액이 0원입니다. 다시 넣어주세요.\n"),
    KOREA_MONEY("⚠️ 오류 : 1원 단위는 받지 않습니다. 다시 입력하세요.\n"),
    ONLY_INPUT_NUMBER("⚠️ 오류 : 숫자만 입력해주세요.\n"),
    MINUS_CASH("⚠️ 오류 : - 돈이라는 건 없습니다.\n"),
    INPUT_EMPTY("⚠️ 오류 : 빈 값은 입력될 수 없습니다.\n"),
    YES_NO("⚠️ 오류 : Y 또는 N으로 입력해주세요.\n"),
    INDEX_BOUND_ERROR("⚠️ 오류: 1부터 %d 사이의 번호를 입력해주세요.\n");

    private final String message;

    ErrorAnnounce(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
