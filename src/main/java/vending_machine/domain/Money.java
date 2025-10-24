package vending_machine.domain;

public record Money(
        int amount
) {
    private static final int MIN_AMOUNT = 0;

    public Money {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("금액은 " + MIN_AMOUNT + "미만일 수 없습니다.");
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
