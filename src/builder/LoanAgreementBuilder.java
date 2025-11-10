package builder;

import model.Client;
import java.time.LocalDate;
import java.util.Random;

/**
 * Builder for creating LoanAgreement objects.
 * Implements the Builder pattern to construct complex loan agreements step by step.
 * <p>
 * This pattern is useful when:
 * - An object has many parameters (required and optional)
 * - We want to make the construction process clear and readable
 * - We want to ensure immutability of the final object
 * <p>
 * Usage example:
 * <pre>
 * LoanAgreement loan = new LoanAgreementBuilder()
 *     .setClient(client)
 *     .setAmount(500_000)
 *     .setInterestRate(7.5)
 *     .setTermMonths(60)
 *     .build();
 * </pre>
 */
public class LoanAgreementBuilder implements LoanBuilder {
    // Required fields
    Client client;
    double amount;
    double interestRate;
    int termMonths;

    // Optional fields with default values
    String agreementNumber;
    LocalDate startDate;
    String purpose;
    boolean insuranceRequired;

    /**
     * Initializes builder with default values for optional fields.
     */
    public LoanAgreementBuilder() {
        // Set default values
        this.agreementNumber = generateAgreementNumber();
        this.startDate = LocalDate.now();
        this.purpose = "General purpose";
        this.insuranceRequired = false;
    }

    /**
     * Sets the client for the loan agreement.
     * This is a required field.
     *
     * @param client the client applying for the loan
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        this.client = client;
        return this;
    }

    /**
     * Sets the loan amount.
     * This is a required field.
     *
     * @param amount the loan amount in currency units
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }
        this.amount = amount;
        return this;
    }

    /**
     * Sets the annual interest rate.
     * This is a required field.
     *
     * @param interestRate the annual interest rate as a percentage (e.g., 7.5 for 7.5%)
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 100) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 100");
        }
        this.interestRate = interestRate;
        return this;
    }

    /**
     * Sets the loan term in months.
     * This is a required field.
     *
     * @param termMonths the loan term in months
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setTermMonths(int termMonths) {
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Term must be positive");
        }
        this.termMonths = termMonths;
        return this;
    }

    /**
     * Sets a custom agreement number.
     * This is an optional field (auto-generated if not set).
     *
     * @param agreementNumber the agreement number
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
        return this;
    }

    /**
     * Sets the loan start date.
     * This is an optional field (defaults to today).
     *
     * @param startDate the date when the loan starts
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Sets the purpose of the loan.
     * This is an optional field (defaults to "General purpose").
     *
     * @param purpose the purpose description
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    /**
     * Sets whether insurance is required for the loan.
     * This is an optional field (defaults to false).
     *
     * @param insuranceRequired true if insurance is required
     * @return this builder instance for method chaining
     */
    @Override
    public LoanBuilder setInsuranceRequired(boolean insuranceRequired) {
        this.insuranceRequired = insuranceRequired;
        return this;
    }

    /**
     * Builds and returns the final LoanAgreement object.
     * Validates that all required fields are set.
     *
     * @return a new immutable LoanAgreement instance
     * @throws IllegalStateException if required fields are not set
     */
    @Override
    public LoanAgreement build() {
        validateRequiredFields();
        return new LoanAgreement(this);
    }

    /**
     * Validates that all required fields are set before building.
     *
     * @throws IllegalStateException if any required field is missing
     */
    private void validateRequiredFields() {
        if (client == null) {
            throw new IllegalStateException("Client must be set before building");
        }
        if (amount <= 0) {
            throw new IllegalStateException("Loan amount must be set before building");
        }
        if (interestRate < 0) {
            throw new IllegalStateException("Interest rate must be set before building");
        }
        if (termMonths <= 0) {
            throw new IllegalStateException("Term months must be set before building");
        }
    }

    /**
     * Generates a unique agreement number.
     * Format: LOAN-YYYYMMDD-XXXX where XXXX is a random number.
     *
     * @return generated agreement number
     */
    private String generateAgreementNumber() {
        LocalDate today = LocalDate.now();
        String dateStr = String.format("%04d%02d%02d",
            today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        int randomNum = 1000 + new Random().nextInt(9000);
        return "LOAN-" + dateStr + "-" + randomNum;
    }
}

