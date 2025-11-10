import model.Account;
import model.AccountType;
import model.Client;
import observer.*;
import factory.*;
import builder.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== [BANK] Welcome to our Bank System ===\n");

        Client client = new Client("Sabulla", "Diana", "diana@bank.kz", "+77009890450");
        client.showClientInfo();

        Device phone = new MobilePhoneDevice();
        Device laptop = new LaptopDevice();
        client.addDevice(phone);
        client.addDevice(laptop);

        client.seeDevices();

        Account savings = new Account(client, AccountType.SAVINGS, "Dream account");
        Account deposit = new Account(client, AccountType.DEPOSIT, "Big goal deposit");

        client.createAccount(savings);
        client.createAccount(deposit);
        client.showAccounts();

        System.out.println("\n=== [OPERATIONS] Performing operations ===");
        savings.deposit(120000);
        System.out.println();
        deposit.deposit(300000);
        System.out.println();
        savings.pay(20000);
        System.out.println();
        deposit.withdraw(100000);

        System.out.println("\n=== [STATS] Final account info ===");
        client.showAccounts();

        // ============================================================
        // Demonstration of Factory Method Pattern
        // ============================================================
        System.out.println("\n=== [FACTORY] Factory Method Pattern Demo ===\n");

        // Method 1: Using simple factory (backward compatibility)
        System.out.println("[SIMPLE FACTORY APPROACH]");
        Report clientReport = ReportFactory.createReport("client");
        clientReport.generateReport();

        Report bankReport = ReportFactory.createReport("bank");
        bankReport.generateReport();

        Report auditReport = ReportFactory.createReport("audit");
        auditReport.generateReport();

        // Method 2: Using Creator hierarchy (Full Factory Method Pattern)
        System.out.println("[FULL FACTORY METHOD APPROACH with Creator Hierarchy]");

        ReportCreator clientCreator = ReportFactory.getReportCreator("client");
        System.out.println("Creator: " + clientCreator.getCreatorInfo());
        clientCreator.generateAndShow();

        ReportCreator bankCreator = ReportFactory.getReportCreator("bank");
        System.out.println("Creator: " + bankCreator.getCreatorInfo());
        bankCreator.generateAndShow();

        ReportCreator auditCreator = ReportFactory.getReportCreator("audit");
        System.out.println("Creator: " + auditCreator.getCreatorInfo());
        auditCreator.generateAndShow();

        // ============================================================
        // Demonstration of Builder Pattern
        // ============================================================
        System.out.println("=== [BUILDER] Builder Pattern Demo ===\n");

        // Method 1: Using builder directly
        System.out.println("[DIRECT BUILDER APPROACH]");
        LoanAgreement simpleLoan = new LoanAgreementBuilder()
                .setClient(client)
                .setAmount(500_000)
                .setInterestRate(7.5)
                .setTermMonths(60)
                .build();

        simpleLoan.displayAgreementInfo();

        // Creating a complex loan agreement with all optional fields
        Client client2 = new Client("Ivanov", "Ivan", "ivan@bank.kz", "+77001234567");

        LoanAgreement complexLoan = new LoanAgreementBuilder()
                .setClient(client2)
                .setAmount(1_200_000)
                .setInterestRate(6.8)
                .setTermMonths(120)
                .setPurpose("Real Estate Purchase")
                .setInsuranceRequired(true)
                .setAgreementNumber("LOAN-20251110-CUSTOM")
                .build();

        complexLoan.displayAgreementInfo();

        // Method 2: Using Director (Full Builder Pattern)
        System.out.println("[FULL BUILDER PATTERN with Director]");

        LoanBuilder builder = new LoanAgreementBuilder();
        LoanAgreementDirector director = new LoanAgreementDirector(builder);

        // Standard loan via Director
        LoanAgreement standardLoan = director.constructStandardLoan(client, 300_000);
        System.out.println("\n[DIRECTOR] Standard Personal Loan:");
        standardLoan.displayAgreementInfo();

        // Mortgage loan via Director
        LoanAgreement mortgageLoan = director.constructMortgageLoan(client2, 2_500_000);
        System.out.println("[DIRECTOR] Mortgage Loan:");
        mortgageLoan.displayAgreementInfo();

        // Car loan via Director
        LoanAgreement carLoan = director.constructCarLoan(client, 800_000);
        System.out.println("[DIRECTOR] Car Loan:");
        carLoan.displayAgreementInfo();

        System.out.println("=== [OK] All patterns demonstrated successfully! ===");
    }
}