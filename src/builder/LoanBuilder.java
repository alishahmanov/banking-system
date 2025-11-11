package builder;

import model.Client;
import java.time.LocalDate;

/**
 * Builder interface defining the contract for constructing loan agreements.
 * Allows different builder implementations for various types of loans.
 * Part of the Builder design pattern.
 */
public interface LoanBuilder {
    /**
     * Sets the client for the loan agreement.
     *
     * @param client the client applying for the loan
     * @return this builder instance for method chaining
     */
    LoanBuilder setClient(Client client);

    /**
     * Sets the loan amount.
     *
     * @param amount the loan amount in currency units
     * @return this builder instance for method chaining
     */
    LoanBuilder setAmount(double amount);

    /**
     * Sets the annual interest rate.
     *
     * @param interestRate the annual interest rate as a percentage
     * @return this builder instance for method chaining
     */
    LoanBuilder setInterestRate(double interestRate);

    /**
     * Sets the loan term in months.
     *
     * @param termMonths the loan term in months
     * @return this builder instance for method chaining
     */
    LoanBuilder setTermMonths(int termMonths);

    /**
     * Sets a custom agreement number.
     *
     * @param agreementNumber the agreement number
     * @return this builder instance for method chaining
     */
    LoanBuilder setAgreementNumber(String agreementNumber);

    /**
     * Sets the loan start date.
     *
     * @param startDate the date when the loan starts
     * @return this builder instance for method chaining
     */
    LoanBuilder setStartDate(LocalDate startDate);

    /**
     * Sets the purpose of the loan.
     *
     * @param purpose the purpose description
     * @return this builder instance for method chaining
     */
    LoanBuilder setPurpose(String purpose);

    /**
     * Sets whether insurance is required for the loan.
     *
     * @param insuranceRequired true if insurance is required
     * @return this builder instance for method chaining
     */
    LoanBuilder setInsuranceRequired(boolean insuranceRequired);

    /**
     * Builds and returns the final LoanAgreement object.
     *
     * @return a new immutable LoanAgreement instance
     */
    LoanAgreement build();
}

