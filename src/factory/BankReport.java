package factory;

/**
 * Bank report implementation.
 * Provides overall bank statistics and financial summary for bank managers.
 */
public class BankReport implements Report {

    @Override
    public void generateReport() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║        BANK MANAGEMENT REPORT             ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("[BANK] Total Accounts: 1,547");
        System.out.println("[MONEY] Total Deposits: $45,780,250.00");
        System.out.println("[CASH] Total Withdrawals: $12,340,150.00");
        System.out.println("[GROWTH] Net Growth: +15.3%");
        System.out.println("[USERS] Active Clients: 892");
        System.out.println("[TARGET] Loan Portfolio: $23,500,000.00");
        System.out.println("═══════════════════════════════════════════════\n");
    }

    @Override
    public String getReportType() {
        return "Bank Operations Report";
    }
}

