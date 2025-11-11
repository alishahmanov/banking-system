package factory;

/**
 * Factory for creating different types of reports.
 * Implements the Factory Method pattern with proper Creator hierarchy.
 * <p>
 * This class provides two approaches:
 * 1. Direct report creation (simple factory style)
 * 2. Creator-based creation (full Factory Method pattern)
 * <p>
 * This class adheres to the Open-Closed Principle (OCP):
 * - Open for extension: new report types can be added easily
 * - Closed for modification: existing code doesn't need changes
 */
public class ReportFactory {

    /**
     * Creates a report creator based on the specified user role.
     * Returns a concrete Creator that can produce the appropriate Report.
     * This is the full Factory Method pattern implementation.
     *
     * @param userRole the role of the user requesting the report
     *                 ("client", "bank", or "audit")
     * @return ReportCreator instance corresponding to the user role
     * @throws IllegalArgumentException if the role is not recognized
     */
    public static ReportCreator getReportCreator(String userRole) {
        if (userRole == null || userRole.trim().isEmpty()) {
            throw new IllegalArgumentException("User role cannot be null or empty");
        }

        return switch (userRole.toLowerCase()) {
            case "client" -> new ClientReportCreator();
            case "bank" -> new BankReportCreator();
            case "audit" -> new AuditReportCreator();
            default -> throw new IllegalArgumentException(
                    "Unknown user role: " + userRole +
                    ". Supported roles: client, bank, audit"
            );
        };
    }

    /**
     * Creates a report based on the specified user role.
     * This is a convenience method that directly returns a Report instance.
     * Kept for backward compatibility with existing code.
     *
     * @param userRole the role of the user requesting the report
     *                 ("client", "bank", or "audit")
     * @return Report instance corresponding to the user role
     * @throws IllegalArgumentException if the role is not recognized
     */
    public static Report createReport(String userRole) {
        ReportCreator creator = getReportCreator(userRole);
        return creator.createReport();
    }
}

