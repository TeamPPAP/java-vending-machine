package vending_machine;

import java.util.List;
import vending_machine.controller.VendingMachineController;
import vending_machine.domain.Item;
import vending_machine.domain.VendingMachine;
import vending_machine.util.ItemLoader;
import vending_machine.view.InputView;
import vending_machine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ItemLoader itemLoader = new ItemLoader();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        List<Item> items = itemLoader.load();

        VendingMachine vendingMachine = new VendingMachine(items);
        VendingMachineController controller = new VendingMachineController(vendingMachine, inputView, outputView);

        controller.run();
    }
}
