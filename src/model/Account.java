package model;

import decorator.AccountBalanceDecorator;
import decorator.AccountBonus;
import decorator.Bonus;
import decorator.DepositBalanceDecorator;
import observer.BankSystem;

/**
 * Represents a bank account (savings, deposit, or credit).
 * Manages operations and bonus calculation.
 */
public class Account {
    private static int nextId = 1;
    private int accountId;
    private String clientName;
    private AccountType accountType;
    private String accountName;
    private double balance;
    private double bonus;
    private Client client;
    private BankSystem bank = BankSystem.getInstance();

    public Account(Client client, AccountType accountType, String accountName) {
        accountId = nextId++;
        this.client = client;
        this.clientName = client.getName();
        this.accountType = accountType;
        this.accountName = accountName;
        balance = 0;
        bonus = 1.0;
        Bonus bonusLogic = new AccountBonus();
        if (accountType == AccountType.SAVINGS)
            bonusLogic = new AccountBalanceDecorator(bonusLogic);
        else if (accountType == AccountType.DEPOSIT)
            bonusLogic = new DepositBalanceDecorator(bonusLogic);

        double newBonus = bonusLogic.additionalBonus(this);
        bonus += newBonus;
    }

    public void deposit(double amount) {
        String operation = "deposit";
        if (amount > 0) {
            balance += amount;
            bank.balanceChange(clientName, accountName, operation, amount, balance);
        }
    }

    public void withdraw(double amount) {
        String operation = "withdraw";
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                bank.balanceChange(clientName, accountName, operation, amount, balance);
            } else {
                System.out.println("Insufficient funds for withdrawal.");
            }
        }
    }

    public void pay(double amount) {
        String operation = "payment";
        if (amount > 0) {
            if (balance >= amount) {
                double bonusAmount = amount * bonus / 100;
                balance = balance - amount + bonusAmount;
                bank.balanceChange(clientName, accountName, operation, amount, balance, bonusAmount);
            } else {
                System.out.println("Insufficient funds for payment.");
            }
        }
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void showAccountInfo() {
        System.out.println("──────────────── Account Info ────────────────");
        System.out.printf("Client: %s%n", clientName);
        System.out.printf("ID: %d%n", accountId);
        System.out.printf("Type: %s%n", accountType.getDescription());
        System.out.printf("Name: %s%n", accountName);
        System.out.printf("Balance: %.2f ₸%n", balance);
        System.out.printf("Bonus: %.1f%%%n", bonus);
        System.out.println("──────────────────────────────────────────────");

    }
}
