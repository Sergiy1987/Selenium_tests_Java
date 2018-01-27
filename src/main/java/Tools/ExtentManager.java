package Tools;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;
    private static String filePath = "\\ExtentReports\\ExtentReportResults.html";

    public synchronized static ExtentReports GetExtent(){
        if (extent != null)
            return extent; //avoid creating new instance of html file
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        return extent;
    }
    public static ExtentHtmlReporter getHtmlReporter() {
        String workingDir = System.getProperty("user.dir");
        htmlReporter = new ExtentHtmlReporter(workingDir + filePath);
        // make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("PageFactory Report");
        htmlReporter.config().setReportName("Tests cycle");
        return htmlReporter;
    }
    public static ExtentTest createTest(String name, String description){
        test = extent.createTest(name, description);
        return test;
    }
}