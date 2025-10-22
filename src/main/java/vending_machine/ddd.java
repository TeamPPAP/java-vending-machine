package vending_machine;

import vending_machine.domain.Item;

//public class ddd {
//    while(true){
//        if (cash > 0) {
//            for (int i = 0; i < items.size(); i++) {
//                Item tmp = items.get(i);
//                System.out.printf("[%d] %s (%d원) - %d개\n", i+1, tmp.getName(), tmp.getPrice(), tmp.getStock());
//            }
//            System.out.println("-------------------------------");
//            System.out.printf("[%d] 금액 추가 투입\n" + "[%d] 금액 반환", items.size() + 1, items.size() + 2);
//            System.out.println("구입할 상품 번호를 입력하세요 : ");
//            int choiceMenu = Integer.parseInt(sc.nextLine());
//            Item i = items.get(choiceMenu-1);
//            System.out.println(i.getName() + "가 나왔습니다! (거스름돈:"+ (cash - i.getPrice())+"원)");
//            System.out.println("투입할 금액을 입력해주세요 : ");
//            cash += Integer.parseInt(sc.nextLine());
//            System.out.println("현재 투입된 금액 : " + cash);
//        }
//        if (cash <= 0) {
//            for (int i = 0; i < items.size(); i++) {
//                Item tmp = items.get(i);
//                System.out.printf("[%d] %s (%d원) - %d개\n", i+1, tmp.getName(), tmp.getPrice(), tmp.getStock());
//            }
//            System.out.println("투입할 금액을 입력해주세요 : ");
//            cash = Integer.parseInt(sc.nextLine());
//
//            System.out.println("현재 투입된 금액 : " + cash);
//        }
//    }
//}
