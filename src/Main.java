import model.*;
import observer.*;
import factory.*;
import builder.*;
import strategy.*;
import facade.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== [BANK] Welcome to our Bank System ===\n");

        // ========================= OBSERVER + DECORATOR =========================
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

        // ========================= FACTORY METHOD =========================
        System.out.println("\n=== [FACTORY] Factory Method Pattern Demo ===\n");

        System.out.println("[SIMPLE FACTORY APPROACH]");
        Report clientReport = ReportFactory.createReport("client");
        clientReport.generateReport();

        Report bankReport = ReportFactory.createReport("bank");
        bankReport.generateReport();

        Report auditReport = ReportFactory.createReport("audit");
        auditReport.generateReport();

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

        // ========================= BUILDER =========================
        System.out.println("=== [BUILDER] Builder Pattern Demo ===\n");

        System.out.println("[DIRECT BUILDER APPROACH]");
        LoanAgreement simpleLoan = new LoanAgreementBuilder()
                .setClient(client)
                .setAmount(500_000)
                .setInterestRate(7.5)
                .setTermMonths(60)
                .build();
        simpleLoan.displayAgreementInfo();

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

        System.out.println("[FULL BUILDER PATTERN with Director]");
        LoanBuilder builder = new LoanAgreementBuilder();
        LoanAgreementDirector director = new LoanAgreementDirector(builder);

        LoanAgreement standardLoan = director.constructStandardLoan(client, 300_000);
        System.out.println("\n[DIRECTOR] Standard Personal Loan:");
        standardLoan.displayAgreementInfo();

        LoanAgreement mortgageLoan = director.constructMortgageLoan(client2, 2_500_000);
        System.out.println("[DIRECTOR] Mortgage Loan:");
        mortgageLoan.displayAgreementInfo();

        LoanAgreement carLoan = director.constructCarLoan(client, 800_000);
        System.out.println("[DIRECTOR] Car Loan:");
        carLoan.displayAgreementInfo();

        // ========================= STRATEGY =========================
        System.out.println("=== [STRATEGY] Interest Calculation Demo ===\n");

        InterestCalculator calculator = new InterestCalculator();

        calculator.setStrategy(new SavingsInterest());
        double savingsInterest = calculator.execute(savings);
        System.out.printf("[SAVINGS] Interest (3%%): +%.2f ₸%n", savingsInterest);

        calculator.setStrategy(new VIPInterest());
        double vipInterest = calculator.execute(deposit);
        System.out.printf("[VIP] Interest (5%%): +%.2f ₸%n", vipInterest);

        calculator.setStrategy(new LoanInterest());
        double loanInterest = calculator.execute(savings);
        System.out.printf("[LOAN] Interest (7%%): +%.2f ₸%n%n", loanInterest);

        // ========================= FACADE =========================
        System.out.println("=== [FACADE] BankingFacade Demo ===\n");

        BankingFacade facade = new BankingFacade();

        // Interest application via Facade
        facade.applyInterest(savings, new SavingsInterest());
        facade.applyInterest(deposit, new VIPInterest());

        // Transfer operation via Facade
        facade.transfer(savings, deposit, 50000);

        // Loan creation via Facade
        LoanAgreement newLoan = facade.createLoan(client, 400_000);
        newLoan.displayAgreementInfo();

        // Report generation via Facade
        facade.generateReport("bank");

        // Notifications via Facade
        facade.notifyClients("System maintenance tonight at 23:00.");

        System.out.println("\n=== [OK] All 6 patterns demonstrated successfully! ===");
    }
}
