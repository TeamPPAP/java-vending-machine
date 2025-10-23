package vending_machine.service;

import vending_machine.model.CreditState;
import vending_machine.model.GameState;
import vending_machine.model.Item;
import vending_machine.model.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    Money money;
    Scanner scanner;
    GameState gameState;
    private List<Item> items = new ArrayList<>();

    public VendingMachine(List<Item> items) {
        this.items = items;
    }
    
    public void run (Scanner scanner) {
        this.scanner = scanner;
        money = new Money(scanner);
        this.gameState = GameState.IN_PROGRESS;

        System.out.println(sayHello());
        while(GameState.IN_PROGRESS.equals(gameState)) {
            // 최초 메뉴 노출
            System.out.println(showMenu(items));
            
            // 최초 금액 투입요구
            money.askForAmount();

            money.printBalance();

            // 금액 투입 후 메뉴 노출
            System.out.println(showMenu(items));

            // 선택된 아이템 객체를 특정하고 반환
            Item targetItem = getChoiceItem(askForItemId());

            // 아이템 구매
            purchaseItem(targetItem);

            // 추가 구매 여부
            askAgainPurchase();

            if (GameState.ENDED.equals(gameState)) {
                break;
            }

            money.printBalance(); //while문 밖에서 하면될거같음
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

        if(CreditState.HAS_CREDIT.equals(money.getCreditState()))
            menu = menu + "[6] 금액 추가 투입\n" + "[7] 금액 반환\n" + "====================================\n";

        return menu;
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

    /**
     * 물건구매 한묶음으로 동작합니다. (잔액 차감 + 재고 차감)
     */
    public void purchaseItem(Item targetItem) {
        BigDecimal balance = money.getBalance();
        BigDecimal price   = BigDecimal.valueOf(targetItem.getPrice()); // getPrice가 long/double이면 OK
        // getPrice가 int라면: BigDecimal.valueOf((long) targetItem.getPrice());

        if (balance.compareTo(price) < 0) {
            throw new IllegalArgumentException(
                    "잔액 부족: balance=" + balance + ", price=" + price
            );
        }

        money.debit(BigDecimal.valueOf(targetItem.getPrice()));
        targetItem.decreaseStock();

        System.out.println(targetItem.getName() + "가 나왔습니다. " + money.getChangeBalance());
    }

    public void askAgainPurchase() {
        System.out.print("추가 구매를 하시겠습니까?(Y/N)");
        String result = scanner.nextLine();
        scanner.next();

        if(CreditState.NO_CREDIT.equals(money.getCreditState())){
            System.out.println("종료된다.");
            gameState = GameState.ENDED;
        }

        if("N".equals(result)){
            money.refund();
            gameState = GameState.ENDED;
        }
    }
}
