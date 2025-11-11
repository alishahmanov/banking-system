package strategy;

import model.Account;

/**
 * Concrete Strategy for loan accounts.
 * Applies 7% interest rate.
 */
public class LoanInterest implements InterestStrategy {
    @Override
    public double calculateInterest(Account account) {
        return account.getBalance() * 0.07; // 7% annual
    }
}
