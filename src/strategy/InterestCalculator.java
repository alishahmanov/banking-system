package strategy;

import model.Account;

/**
 * Context class for Strategy pattern.
 * Executes selected interest calculation algorithm.
 */
public class InterestCalculator {
    private InterestStrategy strategy;

    public void setStrategy(InterestStrategy strategy) {
        this.strategy = strategy;
    }

    public double execute(Account account) {
        if (strategy == null) {
            throw new IllegalStateException("Interest strategy is not set!");
        }
        return strategy.calculateInterest(account);
    }
}
