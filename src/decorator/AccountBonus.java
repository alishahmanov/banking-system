package decorator;

import model.Account;

/**
 * Concrete component.
 * Represents the base bonus calculator without any decorations.
 */
public class AccountBonus implements Bonus{
    @Override
    public int additionalBonus(Account account) {
        return 0;
    }
}
