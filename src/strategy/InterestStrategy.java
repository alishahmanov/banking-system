package strategy;

import model.Account;

/**
 * Strategy interface for interest calculation.
 * Defines interchangeable algorithms for different account types.
 */
public interface InterestStrategy {
    double calculateInterest(Account account);
}
