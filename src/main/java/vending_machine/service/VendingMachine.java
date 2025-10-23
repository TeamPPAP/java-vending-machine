package vending_machine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    CreditState creditState;
    Money money;
    Scanner scanner;
    private List<Item> items = new ArrayList<>();

    public VendingMachine(List<Item> items) {
        creditState = CreditState.NO_CREDIT;
        this.items = items;
    }
    
    void run (Scanner scanner) {
        this.scanner = scanner;

        sayHello();
        while(true){
            // 최초 메뉴 노출
            System.out.println(showMenu(items));

            // 최초 금액 투입요구
            this.money = new Money(scanner);
            money.askForAmount();

            // 적절한 금액 투입시 자판기 상태변환 및 금액 추가 메서드 호출
            if(balance.compareTo(BigDecimal.ZERO) > 0){
                this.turnCreditOn();
                this.credit(balance);
            }

            // 자판기 상태에 따른 현재 금액 노출
            if(CreditState.HAS_CREDIT.equals(this.creditState))
                System.out.println("현재 투입된 금액 : " + this.balance + "원" );

            // 금액 투입 후 메뉴 노출
            System.out.println(this.showMenu(items));

            // 선택된 아이템 객체를 특정하고 반환
            Item targetItem = getChoiceItem(askForItemId());

            this.debit(BigDecimal.valueOf(targetItem.getPrice()));
            targetItem.decreaseStock();

            System.out.println(targetItem.getName() + "가 나왔습니다. (거스름돈:" +this.balance+"원)"  );

            System.out.print("추가 구매를 하시겠습니까?(Y/N)");
            String result = scanner.nextLine();
            scanner.next();

            if(this.balance.compareTo(BigDecimal.ZERO)==0){
                System.out.println("종료된다.");
                break;
            }

            if("N".equals(result)){
                this.refund();
                break;
            }

            System.out.println("현재 투입된 금액 : " + this.balance + "원");
        }
    }

    String sayHello(){
        return "\uD83E\uDD64 안녕하세요! PPAP 자판기입니다. \uD83E\uDD64";
    }

    String showMenu(List<Item> items){
        String menu =  "====================================\n";

        for(Item item : items){
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

    /**
     * 구입할 상품 번호를 입력받고 반환합니다.
     * @return
     */
    private int askForItemId() {
        System.out.print("구입할 상품 번호를 입력하세요 : ");
        return scanner.nextInt();
    }

    /**
     * 선택된 상품 번호에 따른 상품 객체를 특정하고 반환합니다.
     * @param itemId
     * @return
     */
    private Item getChoiceItem(int itemId) {
        // 1. 최초 id부여 규칙의 역순으로 상품 특정
        items.get(itemId -1);

        // 2. 객체 필드값 구분으로 상품 특정
        return items.stream().filter(item ->
                item.getId() == itemId
        ).findFirst().get();
    }

}
