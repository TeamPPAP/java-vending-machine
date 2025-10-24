package vending_machine.domain;

public record Money(
        int amount
) {

    public Money {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0 미만일 수 없습니다.");
        }
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        return new Money(this.amount - other.amount);
    }

    public boolean isGreaterThanOrEqualTo(Money other) {
        return other.amount <= this.amount;
    }
}
