package Config.Reports;

import Config.DriverSetup.DriverSetup;
import Config.Utilities.DateTimeWork;
import Config.Utilities.ElementsCustomCode;
import Config.Utilities.FileDirectoryWork;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class HTMLReport {

    static String projectFolderPath = System.getProperty("user.dir");
    static String htmlReport_resultsFolderPath = "src/test/Results/";
    static String htmlReport_screenshotFolderPath = htmlReport_resultsFolderPath + "ScreenShots/";
    static String htmlReport_htmlResultFilePath = htmlReport_resultsFolderPath + "SAF_Results_.html";
    static ExtentReports extent = null;
    static ExtentTest test = null;

    public static String takeScreenshot() {
        try {
            String format = "png";
            String imageFilePath = htmlReport_screenshotFolderPath + "Image_" + System.currentTimeMillis() + "." + format;
            TakesScreenshot scrShot =((TakesScreenshot)DriverSetup.driver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile=new File(imageFilePath);
            FileUtils.copyFile(SrcFile, DestFile);
            return imageFilePath.replace("src/test/Results/", "");
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return "";
    }
    public static void addTestCaseToReport(String testCaseName, String testCaseDescription) {
        if (extent == null) {
            if(!new FileDirectoryWork().isFileOrDirectoryExists(projectFolderPath+"/"+htmlReport_screenshotFolderPath))
                new FileDirectoryWork().createDirectory(projectFolderPath+"/"+htmlReport_screenshotFolderPath);
            String timeStamp = new DateTimeWork().getCurrentDateTime(new DateTimeWork().dateTimeFormat_4);
            htmlReport_htmlResultFilePath = htmlReport_htmlResultFilePath.replace(".html", timeStamp+".html");
            extent = new ExtentReports(htmlReport_htmlResultFilePath, true);
        }
        test = extent.startTest(testCaseName, testCaseDescription);
    }
    public static void addTestStepToReport(String status, String stepName, String description) {
        try {
            if (status.equalsIgnoreCase("PASS")) {
                test.log(LogStatus.PASS, stepName, description);
            } else if (status.equalsIgnoreCase("FAIL")) {
                test.log(LogStatus.FAIL, stepName, description);
            } else if (status.equalsIgnoreCase("INFO")) {
                test.log(LogStatus.INFO, stepName, description);
            }
        } catch (Exception e) {
        }
        try {
            saveReport();
        } catch (Exception e) {
        }
    }
    public static void addTestStepToReportWithScreenShot(String status, String stepName, String description) {
        try {
            if (status.equalsIgnoreCase("PASS")) {
                test.log(LogStatus.PASS, stepName, description + " : " + test.addScreenCapture(takeScreenshot()));
            } else if (status.equalsIgnoreCase("FAIL")) {
                test.log(LogStatus.FAIL, stepName, description + " : " + test.addScreenCapture(takeScreenshot()));
            } else if (status.equalsIgnoreCase("INFO")) {
                test.log(LogStatus.INFO, stepName, description + " : " + test.addScreenCapture(takeScreenshot()));
            }
        } catch (Exception e) {
        }
        try {
            saveReport();
        } catch (Exception e) {
        }
    }
    public static void saveReport() {
        try {
            extent.endTest(test);
            extent.flush();
        } catch (Exception e) {
        }
    }
}