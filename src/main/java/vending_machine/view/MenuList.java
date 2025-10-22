package vending_machine.view;

import java.util.List;
import vending_machine.domain.Item;

public class MenuList {
    //TODO 이름바꾸고 전체적인 출력문
    public void printMenu(List<Item> items) {

    }
    public void printOptionalMenu(int size){
        System.out.println("["+ (size+1) +"] 금액 추가 투입\n"
                + "["+(size+2)+"] 금액 반환\n"
                + "===============================");
    }
}
