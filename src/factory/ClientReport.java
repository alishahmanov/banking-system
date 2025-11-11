package factory;

/**
 * Client report implementation.
 * Provides account information and transaction history for clients.
 */
public class ClientReport implements Report {

    @Override
    public void generateReport() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║        CLIENT ACCOUNT REPORT              ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("[STATS] Account Balance: $10,500.00");
        System.out.println("[CARD] Recent Transactions:");
        System.out.println("  - Deposit: +$5,000.00");
        System.out.println("  - Withdrawal: -$1,500.00");
        System.out.println("  - Payment: -$200.00");
        System.out.println("[BONUS] Bonus Balance: $125.50");
        System.out.println("═══════════════════════════════════════════════\n");
    }

    @Override
    public String getReportType() {
        return "Client Report";
    }
}

