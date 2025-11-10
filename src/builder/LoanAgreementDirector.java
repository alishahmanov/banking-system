package builder;

import model.Client;

/**
 * Director class for the Builder pattern.
 * Manages the construction process of loan agreements using builders.
 * Provides pre-configured methods for creating common loan types.
 */
public class LoanAgreementDirector {
    private LoanBuilder builder;

    /**
     * Constructs a director with the specified builder.
     *
     * @param builder the builder to use for construction
     */
    public LoanAgreementDirector(LoanBuilder builder) {
        this.builder = builder;
    }

    /**
     * Sets a new builder for the director.
     *
     * @param builder the new builder
     */
    public void setBuilder(LoanBuilder builder) {
        this.builder = builder;
    }

    /**
     * Constructs a standard personal loan agreement.
     * Standard terms: 7.5% interest, 5 years (60 months), no insurance.
     *
     * @param client the client applying for the loan
     * @param amount the loan amount
     * @return configured loan agreement
     */
    public LoanAgreement constructStandardLoan(Client client, double amount) {
        return builder
                .setClient(client)
                .setAmount(amount)
                .setInterestRate(7.5)
                .setTermMonths(60)
                .setPurpose("Personal loan")
                .setInsuranceRequired(false)
                .build();
    }

    /**
     * Constructs a mortgage loan agreement.
     * Mortgage terms: 6.8% interest, 30 years (360 months), insurance required.
     *
     * @param client the client applying for the loan
     * @param amount the loan amount
     * @return configured loan agreement
     */
    public LoanAgreement constructMortgageLoan(Client client, double amount) {
        return builder
                .setClient(client)
                .setAmount(amount)
                .setInterestRate(6.8)
                .setTermMonths(360)
                .setPurpose("Real Estate Purchase")
                .setInsuranceRequired(true)
                .build();
    }

    /**
     * Constructs a car loan agreement.
     * Car loan terms: 8.5% interest, 5 years (60 months), insurance required.
     *
     * @param client the client applying for the loan
     * @param amount the loan amount
     * @return configured loan agreement
     */
    public LoanAgreement constructCarLoan(Client client, double amount) {
        return builder
                .setClient(client)
                .setAmount(amount)
                .setInterestRate(8.5)
                .setTermMonths(60)
                .setPurpose("Vehicle Purchase")
                .setInsuranceRequired(true)
                .build();
    }

    /**
     * Constructs a business loan agreement.
     * Business loan terms: 9.0% interest, 10 years (120 months), no insurance.
     *
     * @param client the client applying for the loan
     * @param amount the loan amount
     * @return configured loan agreement
     */
    public LoanAgreement constructBusinessLoan(Client client, double amount) {
        return builder
                .setClient(client)
                .setAmount(amount)
                .setInterestRate(9.0)
                .setTermMonths(120)
                .setPurpose("Business Development")
                .setInsuranceRequired(false)
                .build();
    }

    /**
     * Constructs a custom loan with client-specified parameters.
     * Uses the builder directly for full customization.
     *
     * @param client the client applying for the loan
     * @param amount the loan amount
     * @param interestRate the interest rate
     * @param termMonths the loan term in months
     * @param purpose the loan purpose
     * @return configured loan agreement
     */
    public LoanAgreement constructCustomLoan(Client client, double amount,
                                             double interestRate, int termMonths,
                                             String purpose) {
        return builder
                .setClient(client)
                .setAmount(amount)
                .setInterestRate(interestRate)
                .setTermMonths(termMonths)
                .setPurpose(purpose)
                .build();
    }
}

