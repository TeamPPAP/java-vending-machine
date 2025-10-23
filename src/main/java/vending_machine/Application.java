package vending_machine;

import vending_machine.controller.MachineController;

public class Application {
    public static void main(String[] args) {
        MachineController machineController = new MachineController();
        machineController.run();
    }
}