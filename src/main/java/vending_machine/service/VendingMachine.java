package vending_machine.service;

import vending_machine.model.CreditState;
import vending_machine.model.VendingSessionStatus;
import vending_machine.model.Item;
import vending_machine.model.Money;
import vending_machine.util.input.InputReaderFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class VendingMachine {
    Money money;
    VendingSessionStatus gameState;
    private List<Item> items = new ArrayList<>();

    InputReaderFactory reader;

    public VendingMachine(List<Item> items, InputReaderFactory reader) {
        this.items = items;
        this.reader = reader;
        this.money = new Money(reader);
        this.gameState = VendingSessionStatus.IN_PROGRESS;
    }
    
    public void run () {
        sayHello();

        while(VendingSessionStatus.IN_PROGRESS == gameState) {
            // 최초 메뉴 노출
            System.out.println(showMenu(this.items));
            
            // 최초 금액 투입요구
            this.money.readDepositAndCredit();

            this.money.printBalance();

            // 금액 투입 후 메뉴 노출
            System.out.println(showMenu(this.items));

            // 메뉴 선택에 따른 행위 분기
            int id = askForMenuId();
            Item targetItem = null;
            routeByMenu(id, targetItem);

            if (VendingSessionStatus.ENDED == gameState) {
                break;
            }

            this.money.printBalance();
        }
    }

    void sayHello(){
        System.out.println("\uD83E\uDD64 안녕하세요! PPAP 자판기입니다. \uD83E\uDD64");
    }

    String showMenu(List<Item> items){
        String menu =  "====================================\n";

        for(Item item : items){
            menu = menu + item.toString() + "\n";
        }

        menu = menu + "-----------------------------------\n";

        if(CreditState.HAS_CREDIT  ==  money.getCreditState())
            menu = menu + "[6] 금액 추가 투입\n" + "[7] 금액 반환\n" + "====================================\n";

        return menu;
    }

    /**
     * 구입할 상품 번호를 입력받고 반환합니다.
     * @return
     */
    private int askForMenuId() {
        System.out.print("선택할 메뉴 번호를 입력하세요 : ");
        return reader.integerInRange(1, 7).read();
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
        String result = reader.stringIn(List.of("Y", "N")).read();

        if(CreditState.NO_CREDIT == money.getCreditState()){
            System.out.println("종료된다.");
            gameState = VendingSessionStatus.ENDED;
        }

        if("N".equals(result)){
            money.refund();
            gameState = VendingSessionStatus.ENDED;
        }
    }

    /**
     * 메뉴 선택에 따른 행위를 분기합니다.
     * @param menuId
     * @param targetItem
     */
    public void routeByMenu(int menuId, Item targetItem) {
        try {
            switch (menuId) {
                case 1, 2, 3, 4, 5:
                    targetItem = getChoiceItem(menuId);
                    purchaseItem(targetItem);
                    askAgainPurchase();
                    break;
                case 6:
                    money.readDepositAndCredit();
                    break;
                case 7:
                    money.refund();
                    gameState = VendingSessionStatus.ENDED;
                    break;
                default:
                    throw new IllegalArgumentException("잘못된 값이 입력되었습니다.");
            }
        }catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }



}
