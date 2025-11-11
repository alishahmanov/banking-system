package strategy;

import model.Account;

/**
 * Concrete Strategy for savings accounts.
 * Provides a 3% annual interest rate.
 */
public class SavingsInterest implements InterestStrategy {
    @Override
    public double calculateInterest(Account account) {
        return account.getBalance() * 0.03; // 3% annual
    }
}
