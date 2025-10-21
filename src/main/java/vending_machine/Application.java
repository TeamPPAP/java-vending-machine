package vending_machine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Application {
    static Scanner scanner;

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        scanner = new Scanner(System.in);


        System.out.println(vendingMachine.sayHello());

        ItemLoader itemLoader = new ItemLoader();
        List<Item> itemList = itemLoader.loadItems();

        for(int index = 1 ; index <= itemList.size(); index++){
            itemList.get(index -1).setId(index);
        }

        while(true){
            // 최초 메뉴 노출
            System.out.println(vendingMachine.showMenu(itemList));

            // 최초 금액 투입요구
            System.out.print("투입할 금액을 입력해주세요 : ");
            BigDecimal balance = BigDecimal.valueOf(scanner.nextInt());

            // 적절한 금액 투입시 자판기 상태변환 및 금액 추가 메서드 호출
            if(balance.compareTo(BigDecimal.ZERO) > 0){
                vendingMachine.turnCreditOn();
                vendingMachine.credit(balance);
            }

            // 자판기 상태에 따른 현재 금액 노출
            if(CreditState.HAS_CREDIT.equals(vendingMachine.creditState))
                System.out.println("현재 투입된 금액 : " + vendingMachine.balance + "원" );

            // 금액 투입 후 메뉴 노출
            System.out.println(vendingMachine.showMenu(itemList));

            System.out.print("구입할 상품 번호를 입력하세요 : ");
            int itemNumber = scanner.nextInt();

            // 1. 최초 id부여 규칙의 역순으로 상품 특정
            itemList.get(itemNumber -1);

            // 2. 객체 필드값 구분으로 상품 특정
            Item targetItem = itemList.stream().filter(item ->
                item.getId() == itemNumber
            ).findFirst().get();


            vendingMachine.debit(BigDecimal.valueOf(targetItem.getPrice()));
            targetItem.decreaseStock();

            System.out.println(targetItem.getName() + "가 나왔습니다. (거스름돈:" +vendingMachine.balance+"원)"  );

            System.out.print("추가 구매를 하시겠습니까?(Y/N)");
            String result = scanner.nextLine();
            scanner.next();

            if(vendingMachine.balance.compareTo(BigDecimal.ZERO)==0){
                System.out.println("종료된다.");
                break;
            }

            if("N".equals(result)){
                vendingMachine.refund();
                break;
            }

            System.out.println("현재 투입된 금액 : " + vendingMachine.balance + "원");

        }




    }


}
