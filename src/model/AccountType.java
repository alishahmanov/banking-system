package model;

/**
 * Enum representing supported account types.
 */
public enum  AccountType {
    SAVINGS("Savings"),
    DEPOSIT("Deposit"),
    CREDIT("Credit");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
