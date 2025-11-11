package factory;

/**
 * Abstract Creator class for the Factory Method pattern.
 * Defines the factory method interface for creating Report objects.
 * Subclasses override the factory method to specify which concrete Report to create.
 */
public abstract class ReportCreator {

    /**
     * Factory method to be implemented by concrete creators.
     * This is the core of the Factory Method pattern.
     *
     * @return a new Report instance
     */
    public abstract Report createReport();

    /**
     * Template method that uses the factory method.
     * Demonstrates the common workflow for generating reports.
     * This method remains the same across all subclasses.
     */
    public void generateAndShow() {
        Report report = createReport();
        System.out.println("\n>>> Generating: " + report.getReportType());
        report.generateReport();
    }

    /**
     * Returns information about this report creator.
     *
     * @return description of the creator and its report type
     */
    public abstract String getCreatorInfo();
}

