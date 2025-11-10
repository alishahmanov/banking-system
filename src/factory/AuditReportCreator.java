package factory;

/**
 * Concrete Creator for Audit Reports.
 * Implements the factory method to create AuditReport instances.
 */
public class AuditReportCreator extends ReportCreator {

    /**
     * Factory method implementation.
     * Creates and returns a new AuditReport.
     *
     * @return a new AuditReport instance
     */
    @Override
    public Report createReport() {
        return new AuditReport();
    }

    /**
     * Returns information about this creator.
     *
     * @return creator description
     */
    @Override
    public String getCreatorInfo() {
        return "Audit Report Creator - generates compliance and security audit reports";
    }
}

