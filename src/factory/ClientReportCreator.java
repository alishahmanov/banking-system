package factory;

/**
 * Concrete Creator for Client Reports.
 * Implements the factory method to create ClientReport instances.
 */
public class ClientReportCreator extends ReportCreator {

    /**
     * Factory method implementation.
     * Creates and returns a new ClientReport.
     *
     * @return a new ClientReport instance
     */
    @Override
    public Report createReport() {
        return new ClientReport();
    }

    /**
     * Returns information about this creator.
     *
     * @return creator description
     */
    @Override
    public String getCreatorInfo() {
        return "Client Report Creator - generates account reports for individual clients";
    }
}

