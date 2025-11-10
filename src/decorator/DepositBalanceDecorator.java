package decorator;

import model.Account;
import model.AccountType;

/**
 * Concrete decorator for DEPOSIT accounts.
 * Adds bonus percentage depending on balance size.
 */
public class DepositBalanceDecorator extends BonusDecorator {
    public DepositBalanceDecorator(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public int additionalBonus(Account account) {
        int bonus = 0;
        if (account.getAccountType() == AccountType.DEPOSIT) {
            if (account.getBalance() > 500000) {
                bonus += 2;
            } else if (account.getBalance() > 250000) {
                bonus += 1;
            }
        }
        return bonus;
    }
}
