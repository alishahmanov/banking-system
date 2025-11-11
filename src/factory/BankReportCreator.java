package factory;

/**
 * Concrete Creator for Bank Reports.
 * Implements the factory method to create BankReport instances.
 */
public class BankReportCreator extends ReportCreator {

    /**
     * Factory method implementation.
     * Creates and returns a new BankReport.
     *
     * @return a new BankReport instance
     */
    @Override
    public Report createReport() {
        return new BankReport();
    }

    /**
     * Returns information about this creator.
     *
     * @return creator description
     */
    @Override
    public String getCreatorInfo() {
        return "Bank Report Creator - generates management reports for bank operations";
    }
}

