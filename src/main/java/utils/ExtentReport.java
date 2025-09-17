package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.File;

/*
 * This utility class is responsible for managing the setup and configuration of Extent Reports.
 * It provides a single point of access for creating a new report and adding system information.
 *
 * It ensures that the ExtentReports instance is a singleton, meaning only one instance is created
 * throughout the test execution. It also loads the configuration from a specified XML file
 * to customize the report's appearance and content.
  *
 * @author Carolina Steadham
 */
public class ExtentReport {
    public static ExtentReports extentreport = null;
    public static ExtentTest extentlog;
    public static void initialize(String extentConfigXmlpath) {

        if (extentreport == null) {

            extentreport = new ExtentReports(extentConfigXmlpath, true);

            extentreport.addSystemInfo("Host Name", System.getProperty("user.name"));

            extentreport.addSystemInfo("Environment", "QA");

            extentreport.addSystemInfo("OS", "Mac OS X");

            extentreport.loadConfig(new File(System.getProperty("user.dir") + "/resources/ExtentConfig/extent-config.xml"));

        }
    }


}
