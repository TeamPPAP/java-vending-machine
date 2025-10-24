package vending_machine.domain.Announce;

public enum MachineAnnounce {
    //TODO 공통 출력 부분 빼기
    START("\uD83E\uDD64 안녕하세요! PPAP 자판기입니다. \uD83E\uDD64 \n"),
    DIVIDER_THIN("-------------------------------\n"),
    DIVIDER_THICK("===============================\n");

    private final String message;

    MachineAnnounce(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
