package core;

import com.relevantcodes.extentreports.LogStatus;
import helper.BaseTestHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReport;
import java.io.IOException;

/*
 * This is the BaseTest class that handles the setup and teardown
 * of the test reporting environment. It uses TestNG annotations to
 * automatically generate HTML reports for all test cases.
 *
 * It is responsible for:
 * 1. Initializing Extent Reports before the test suite begins.
 * 2. Logging the status (PASS, FAIL, or SKIP) of each test method after it runs.
 * 3. Closing the report after the entire test suite has completed.
 * All test classes should inherit from this class to enable reporting.
 */
public class BaseTest {
    @BeforeSuite(alwaysRun = true)

    public void config() throws IOException {

        //Create the path in which we will create folder to keep html reports
        String subfolderpath = System.getProperty("user.dir") + "/reports/" + BaseTestHelper.Timestamp();
        //create sub folder
        BaseTestHelper.CreateFolder(subfolderpath);

        ExtentReport.initialize(subfolderpath + "/" + "API_Execution_Automation.html");
    }

    @AfterMethod(alwaysRun = true)

    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {

            ExtentReport.extentlog.log(LogStatus.PASS, "Test Case : " + result.getName() + " is passed ");

        } else if (result.getStatus() == ITestResult.FAILURE) {

            ExtentReport.extentlog.log(LogStatus.FAIL, "Test case : " + result.getName() + " is failed ");

            ExtentReport.extentlog.log(LogStatus.FAIL, "Test case is failed due to:  " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {

            ExtentReport.extentlog.log(LogStatus.SKIP, "Test case is Skiped " + result.getName());

        }
        ExtentReport.extentreport.endTest(ExtentReport.extentlog);
    }

    @AfterSuite(alwaysRun = true)

    public void endReport() {

      ExtentReport.extentreport.close();

    }
}
