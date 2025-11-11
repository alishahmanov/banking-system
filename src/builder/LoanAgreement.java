package builder;

import model.Client;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a loan agreement in the banking system.
 * This class is designed to be constructed using the Builder pattern
 * to handle the complexity of creating loan agreements with multiple parameters.
 */
public class LoanAgreement {
    // Required fields
    private final Client client;
    private final double amount;
    private final double interestRate;
    private final int termMonths;

    // Optional fields with default values
    private final String agreementNumber;
    private final LocalDate startDate;
    private final String purpose;
    private final boolean insuranceRequired;

    /**
     * Private constructor to enforce the use of Builder.
     * Called only by LoanAgreementBuilder.
     */
    LoanAgreement(LoanAgreementBuilder builder) {
        this.client = builder.client;
        this.amount = builder.amount;
        this.interestRate = builder.interestRate;
        this.termMonths = builder.termMonths;
        this.agreementNumber = builder.agreementNumber;
        this.startDate = builder.startDate;
        this.purpose = builder.purpose;
        this.insuranceRequired = builder.insuranceRequired;
    }

    // Getters
    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public boolean isInsuranceRequired() {
        return insuranceRequired;
    }

    /**
     * Calculates the monthly payment amount.
     * Uses the standard loan payment formula.
     */
    public double calculateMonthlyPayment() {
        double monthlyRate = interestRate / 100 / 12;
        return amount * (monthlyRate * Math.pow(1 + monthlyRate, termMonths))
               / (Math.pow(1 + monthlyRate, termMonths) - 1);
    }

    /**
     * Calculates the total amount to be paid over the loan term.
     */
    public double calculateTotalPayment() {
        return calculateMonthlyPayment() * termMonths;
    }

    /**
     * Displays detailed information about the loan agreement.
     */
    public void displayAgreementInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║          LOAN AGREEMENT DETAILS                    ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println("[DOC] Agreement Number: " + agreementNumber);
        System.out.println("[CLIENT] Client: " + client.getName());
        System.out.println("[AMOUNT] Loan Amount: $" + String.format("%,.2f", amount));
        System.out.println("[RATE] Interest Rate: " + interestRate + "%");
        System.out.println("[TERM] Term: " + termMonths + " months (" + (termMonths / 12) + " years)");
        System.out.println("[DATE] Start Date: " + startDate.format(formatter));
        System.out.println("[PURPOSE] Purpose: " + purpose);
        System.out.println("[INSURANCE] Insurance: " + (insuranceRequired ? "Required" : "Not Required"));
        System.out.println("───────────────────────────────────────────────────");
        System.out.println("[PAYMENT] Monthly Payment: $" + String.format("%,.2f", calculateMonthlyPayment()));
        System.out.println("[TOTAL] Total Payment: $" + String.format("%,.2f", calculateTotalPayment()));
        System.out.println("[INTEREST] Total Interest: $" + String.format("%,.2f",
            calculateTotalPayment() - amount));
        System.out.println("════════════════════════════════════════════════════\n");
    }

    @Override
    public String toString() {
        return "LoanAgreement{" +
                "agreementNumber='" + agreementNumber + '\'' +
                ", client=" + client.getName() +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", termMonths=" + termMonths +
                '}';
    }
}

