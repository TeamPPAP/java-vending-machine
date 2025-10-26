package vending_machine.controller.dto;

public record ItemDTO(
        String name,
        int price,
        int stock
) {
}
