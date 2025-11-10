package decorator;

import model.Account;

/**
 * Decorator.
 * Provides a base wrapper for extending bonus behavior dynamically.
 */
public abstract class BonusDecorator implements Bonus{
    protected Bonus decoratedBonus;

    public BonusDecorator(Bonus decoratedBonus) {
        this.decoratedBonus = decoratedBonus;
    }

    @Override
    public int additionalBonus(Account account) {
        return decoratedBonus.additionalBonus(account);
    }
}
