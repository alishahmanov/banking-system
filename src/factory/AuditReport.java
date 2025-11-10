package factory;

/**
 * Audit report implementation.
 * Provides compliance and security audit information for auditors.
 */
public class AuditReport implements Report {

    @Override
    public void generateReport() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║        AUDIT & COMPLIANCE REPORT          ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("[SEARCH] Audit Period: November 2025");
        System.out.println("[OK] Compliance Status: PASSED");
        System.out.println("[WARNING] Flagged Transactions: 3");
        System.out.println("[SECURITY] Security Incidents: 0");
        System.out.println("[CHECKLIST] Regulatory Requirements: 100% Met");
        System.out.println("[REVIEW] Reviewed Accounts: 245");
        System.out.println("[NOTE] Notes: All AML checks completed successfully");
        System.out.println("═══════════════════════════════════════════════\n");
    }
}

