package factory;

/**
 * Report interface.
 * Defines the contract for generating different types of reports.
 */
public interface Report {
    /**
     * Generates and displays the report.
     */
    void generateReport();

    /**
     * Returns the type/name of the report.
     * @return report type as string
     */
    String getReportType();
}

