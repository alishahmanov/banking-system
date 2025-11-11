package facade;

import builder.LoanAgreement;
import builder.LoanAgreementBuilder;
import factory.Report;
import factory.ReportFactory;
import model.Account;
import model.Client;
import observer.BankSystem;
import strategy.InterestCalculator;
import strategy.InterestStrategy;

/**
 * Facade pattern.
 * Provides a unified interface to complex banking subsystems.
 */
public class BankingFacade {
    private final BankSystem bankSystem = BankSystem.getInstance();

    /**
     * Transfers money between two accounts.
     */
    public void transfer(Account from, Account to, double amount) {
        System.out.println("Initiating transfer...");
        from.withdraw(amount);
        to.deposit(amount);
        System.out.println("Transfer completed successfully!\n");
    }

    /**
     * Generates report based on user role.
     */
    public void generateReport(String role) {
        Report report = ReportFactory.createReport(role);
        report.generateReport();
    }

    /**
     * Applies interest to an account using the given strategy.
     */
    public void applyInterest(Account account, InterestStrategy strategy) {
        InterestCalculator calculator = new InterestCalculator();
        calculator.setStrategy(strategy);
        double interest = calculator.execute(account);
        account.deposit(interest);
        System.out.printf("Interest of %.2f ₸ added to account [%s]%n%n", interest, account.getAccountType());
    }

    /**
     * Creates a standard loan agreement for a client.
     */
    public LoanAgreement createLoan(Client client, double amount) {
        LoanAgreementBuilder builder = new LoanAgreementBuilder();
        return builder
                .setClient(client)
                .setAmount(amount)
                .setInterestRate(7.5)
                .setTermMonths(60)
                .setPurpose("Personal Loan")
                .build();
    }

    /**
     * Sends a message to all connected devices (Observer pattern link).
     */
    public void notifyClients(String message) {
        bankSystem.notifyObservers("[BANK NOTIFICATION]: " + message);
    }
}
