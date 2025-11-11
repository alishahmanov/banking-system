package strategy;

import model.Account;

/**
 * Concrete Strategy for VIP clients.
 * Applies a reduced 5% interest rate.
 */
public class VIPInterest implements InterestStrategy {
    @Override
    public double calculateInterest(Account account) {
        return account.getBalance() * 0.05; // 5% annual
    }
}
