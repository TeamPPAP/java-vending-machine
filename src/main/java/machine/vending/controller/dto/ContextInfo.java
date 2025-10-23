package machine.vending.controller.dto;

import machine.vending.domain.Inventory;

public record ContextInfo(Inventory inventory, int balance) {
}
