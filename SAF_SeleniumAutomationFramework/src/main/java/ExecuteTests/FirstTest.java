package ExecuteTests;

import Config.DriverSetup.DriverSetup;
import Config.Reports.HTMLReport;
import Config.YAMLData.YAMLTestData;
import PageElements.HomePage;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FirstTest extends DriverSetup{


    @Parameters({"TC_ID"})
    @BeforeClass
    public void setup(String TC_ID){
        new YAMLTestData().loadTestCaseTestData(TC_ID);
        System.out.println("Test data : "+new YAMLTestData().getTestCaseFieldData("Url"));
        HTMLReport.addTestCaseToReport(TC_ID, TC_ID);
        launChromeBrowser(new YAMLTestData().getTestCaseFieldData("Url"));
    }

    @Test
    public void myScript(){
        HomePage.userId.enterText(new YAMLTestData().getTestCaseFieldData("Url"));
        HTMLReport.addTestStepToReportWithScreenShot("Pass", "HomePage Login", "HomePage Login");
    }

}
