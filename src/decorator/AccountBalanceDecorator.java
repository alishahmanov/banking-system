package decorator;

import model.Account;
import model.AccountType;

/**
 * Concrete decorator for SAVINGS accounts.
 * Adds extra bonus based on balance thresholds.
 */
public class AccountBalanceDecorator extends BonusDecorator {
    public AccountBalanceDecorator(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public int additionalBonus(Account account) {
        int bonus = 0;
        if (account.getAccountType() == AccountType.SAVINGS) {
            if (account.getBalance() > 100000) {
                bonus += 2;
            } else if (account.getBalance() > 50000) {
                bonus += 1;
            }
        }
        return bonus;
    }
}
