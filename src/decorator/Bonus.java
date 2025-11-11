package decorator;

import model.Account;

/**
 * Component interface.
 * Core interface for all bonus calculation strategies.
 */
public interface Bonus {
    int additionalBonus(Account account);
}

