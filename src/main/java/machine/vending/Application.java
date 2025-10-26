package machine.vending;

import machine.vending.controller.VendingMachineController;

public class Application {
    public static void main(String[] args) {
        VendingMachineController controller = new VendingMachineController();
        controller.run();
    }
}
