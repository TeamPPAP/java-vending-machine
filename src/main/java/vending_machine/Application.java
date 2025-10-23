package vending_machine;

import java.util.List;
import java.util.Scanner;
import vending_machine.controller.MachineController;
import vending_machine.domain.Item;
import vending_machine.io.InputFile;

public class Application {
    public static void main(String[] args) {
        InputFile inputFile = new InputFile();
        List<Item> items = inputFile.loadItems();
        Scanner sc = new Scanner(System.in);
        MachineController machineController = new MachineController();

        machineController.run(items, sc);

    }
}
