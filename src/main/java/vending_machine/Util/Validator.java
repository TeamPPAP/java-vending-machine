package vending_machine.Util;

public class Validator {
    //TODO void 및 throw로 변경
    public boolean stringValidate(String input) {
        if (input.isBlank()) {
            System.err.println("⚠️ 오류 : 빈 값은 입력될 수 없습니다.");
            return true;
        }
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
            return false;
        }
        System.err.println("⚠️ 오류 : Y 또는 N으로 입력해주세요.");
        return true;
    }

    public boolean cashValidate(String input) {
        int cash = 0;
        if (input.isBlank()) {
            System.err.println("⚠️ 오류 : 빈 값은 입력될 수 없습니다.");
            return true;
        }
        try {
            cash = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("⚠️ 오류 : 숫자만 입력하세요.");
            return true;
        }
        if (cash < 0) {
            System.err.println("⚠️ 오류 : -돈이라는 없어...");
            return true;
        }
        return false;
    }

    public boolean selectValidate(int choice, int size) {
        if (choice >= 1 && choice <= size) {
            return false;
        } else {
            System.err.printf("⚠️ 오류: 1부터 %d 사이의 번호를 입력해주세요.\n", size);
            System.err.println("==================================================================");
            return true;
        }
    }

    public boolean intValidate(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("⚠️ 오류: 숫자만 입력하세요.");
        }
        return false;
    }
}