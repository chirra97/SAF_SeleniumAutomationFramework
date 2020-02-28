package Config.DataBase;

import Config.Utilities.CustomCode;
import Config.Utilities.DateTimeWork;
import Config.Utilities.FileDirectoryWork;
import Config.Utilities.FlatFileWork;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class MDB_TestResults {


    private static void setupResultsFolderStructure() {
        String currentTimeMills = DateTimeWork.getCurrentDateTimeInMillis();
        MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH = MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH + "/CSR_SAF_RESULTS_" + currentTimeMills;

        //Create Results folder
        FileDirectoryWork.createDirectory(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH);

        //Screenshot Folder create
        MSACCESS_DataBaseConstants.MSACCESS_RESULTS_SCREENSHOTS_PATH = MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH + "/" + MSACCESS_DataBaseConstants.MSACCESS_RESULTS_SCREENSHOTS_PATH;
        FileDirectoryWork.createDirectory(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_SCREENSHOTS_PATH);

    }

    static LinkedHashMap<String, LinkedHashMap<String, String>> testCaseesSummaryReportData_LHM = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    public static LinkedHashMap<String, LinkedHashMap<String, String>> setTestCasesSummaryReportData(String testCaseName, LinkedHashMap<String, LinkedHashMap<String, String>> testCaseData_LHM){
        LinkedHashMap<String, String> testCaseReportDetils_LHS = new LinkedHashMap<String, String>();
        String status = "PASS";
        int totalStepsCount = testCaseData_LHM.size();
        int failStepsCount = 0;
        int passStepsCount = 0;

        LinkedHashMap<String, String> testCaseRowData_LHM = null;
        for (String rowNum:testCaseData_LHM.keySet()) {
            testCaseRowData_LHM = new LinkedHashMap<String, String>();
            testCaseRowData_LHM = testCaseData_LHM.get(rowNum);
            String status_temp = testCaseRowData_LHM.get("testStepStatus");
            if(status_temp.equalsIgnoreCase("FAIL")){
                status = "FAIL";
                failStepsCount += 1;
            }
        }
        String testCaseId = testCaseRowData_LHM.get("testCaseId");
        String testCaseStartTime = testCaseData_LHM.get("1").get("testStepExecutedAt").replace(".000000", "");
        String testCaseEndtTime = testCaseData_LHM.get(""+testCaseData_LHM.size()).get("testStepExecutedAt").replace(".000000", "");

        testCaseReportDetils_LHS.put("testCaseId", testCaseId);
        testCaseReportDetils_LHS.put("testCaseName", testCaseName);
        testCaseReportDetils_LHS.put("testCaseStatus", status);

        passStepsCount = totalStepsCount - failStepsCount;
        testCaseReportDetils_LHS.put("passStepsCount", ""+passStepsCount);
        testCaseReportDetils_LHS.put("failStepsCount", ""+failStepsCount);
        testCaseReportDetils_LHS.put("testCaseStartTime", testCaseStartTime);
        testCaseReportDetils_LHS.put("testCaseEndtTime", testCaseEndtTime);

        String testCaseExecutionDuration = DateTimeWork.getDateTimeDifferance("yyyy-MM-dd HH:mm:ss", testCaseStartTime, testCaseEndtTime);
        testCaseReportDetils_LHS.put("testCaseExecutionDuration", testCaseExecutionDuration);

        testCaseesSummaryReportData_LHM.put(testCaseName, testCaseReportDetils_LHS);
        return testCaseesSummaryReportData_LHM;
    }


    public static String createTestSummaryReport(LinkedHashMap<String, LinkedHashMap<String, String>> testCasesReportLogDetails_LHM){
        LinkedHashMap<String, Integer> tcSummaryReportData_LHM = new LinkedHashMap<String, Integer>();
        System.out.println("testCasesReportLogDetails_LHM : "+testCasesReportLogDetails_LHM);
        String tr = "";
        String td = "";
        for(String outerKey : testCasesReportLogDetails_LHM.keySet()){
            LinkedHashMap<String, String> tcData_LHM = new LinkedHashMap<String, String>();
            String tcReportName = outerKey.replaceAll(" ", "_")+".html";
            tcData_LHM = testCasesReportLogDetails_LHM.get(outerKey);
            String tdValue = "";
            String tdSet = "";
            for(String innerKey : tcData_LHM.keySet()){
                tdValue = tcData_LHM.get(innerKey);
                tdSet = tdSet + td + TestResultsConstants.summaryReportTestCaseTDStart + tdValue + TestResultsConstants.summaryReportTestCaseTDEnd;

                if(innerKey.equalsIgnoreCase("testCaseStatus")){
                    if(tcSummaryReportData_LHM.containsKey(innerKey+tdValue)){
                        int count = tcSummaryReportData_LHM.get(innerKey+tdValue) + 1;
                        tcSummaryReportData_LHM.put(innerKey+tdValue, count);
                    }else{
                        tcSummaryReportData_LHM.put(innerKey+tdValue, 1);
                    }
                }
                else if(innerKey.equalsIgnoreCase("passStepsCount")){
                    if(tcSummaryReportData_LHM.containsKey(innerKey)){
                        int count = tcSummaryReportData_LHM.get(innerKey) + Integer.parseInt(tdValue);
                        tcSummaryReportData_LHM.put(innerKey, count);
                    }else{
                        tcSummaryReportData_LHM.put(innerKey, Integer.parseInt(tdValue));
                    }
                }
                else if(innerKey.equalsIgnoreCase("failStepsCount")){
                    if(tcSummaryReportData_LHM.containsKey(innerKey)){
                        int count = tcSummaryReportData_LHM.get(innerKey) + Integer.parseInt(tdValue);
                        tcSummaryReportData_LHM.put(innerKey, count);
                    }else{
                        tcSummaryReportData_LHM.put(innerKey, Integer.parseInt(tdValue));
                    }
                }
            }
            //Adding link
            tdSet = tdSet +TestResultsConstants.summaryReportTestCaseTDLinkStart.replaceAll("###HREF_LINK###", tcReportName)+
                    TestResultsConstants.summaryReportTestCaseTDLinkEnd;

            tr = tr + TestResultsConstants.summaryReportTestCaseTRStart + tdSet + TestResultsConstants.summaryReportTestCaseTREnd;
        }
        System.out.println("tr : "+tr);
        System.out.println("tcSummaryReportData_LHM : "+tcSummaryReportData_LHM);

        String testSummaryReportString = TestResultsHTMLCode.summaryHTMLCode;
        testSummaryReportString = testSummaryReportString.replaceAll("###TCDATA###", tr);

        //System details update
        testSummaryReportString = testSummaryReportString.replaceAll("###OSNAME###", CustomCode.getSystemOSName());
        testSummaryReportString = testSummaryReportString.replaceAll("###UID###", CustomCode.getUserName());
        testSummaryReportString = testSummaryReportString.replaceAll("###EXETIME###", executionStartedAt);

        //Graphs Data update
        testSummaryReportString = testSummaryReportString.replaceAll("###TCPASSCOUNT###", ""+tcSummaryReportData_LHM.get("testCaseStatusPASS"));
        testSummaryReportString = testSummaryReportString.replaceAll("###TCFAILCOUNT###", ""+tcSummaryReportData_LHM.get("testCaseStatusFAIL"));

        testSummaryReportString = testSummaryReportString.replaceAll("###STEPPASSCOUNT###", ""+tcSummaryReportData_LHM.get("passStepsCount"));
        testSummaryReportString = testSummaryReportString.replaceAll("###STEPFAILCOUNT###", ""+tcSummaryReportData_LHM.get("failStepsCount"));

        FlatFileWork.appendTextToFile(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH+ "/CSR_SAF_SummaryReport.html", testSummaryReportString);

        return ""+true;
    }


    public static void createTestCaseReport(String testCaseName, LinkedHashMap<String, LinkedHashMap<String, String>> testCasesData_LHM){
        String tr = "";
        String td = "";
        String tcReportName = testCaseName.replaceAll(" ", "_") + ".html";
        for(String outerKey : testCasesData_LHM.keySet()) {
            LinkedHashMap<String, String> tcData_LHM = new LinkedHashMap<String, String>();
            tcData_LHM = testCasesData_LHM.get(outerKey);
            String tdValue = "";
            String tdSet = "";
            for (String innerKey : tcData_LHM.keySet()) {
                tdValue = tcData_LHM.get(innerKey);
                if(innerKey.equalsIgnoreCase("testCaseId")){
                    tdValue = tcData_LHM.get("testStepSno");
                }
                if(innerKey.equalsIgnoreCase("testCaseName") || innerKey.equalsIgnoreCase("testStepSno")){
                    continue;
                }else if(innerKey.equalsIgnoreCase("testStepImagePath")){
                    tdSet = tdSet +TestResultsConstants.tcDetailsReportTestCaseTDLinkStart.replaceAll("###HREF_LINK###", tdValue)+
                            TestResultsConstants.tcDetailsReportTestCaseTDLinkEnd;
                }
                else
                    tdSet = tdSet + td + TestResultsConstants.tcDetailsReportTestCaseTDStart + tdValue + TestResultsConstants.summaryReportTestCaseTDEnd;
            }
            tr = tr + TestResultsConstants.summaryReportTestCaseTRStart + tdSet + TestResultsConstants.summaryReportTestCaseTREnd;
        }
        System.out.println("testCaseDetailsReport : "+tr);

        String tcReportString = TestResultsHTMLCode.tcHTMLCode;
        tcReportString = tcReportString.replaceAll("###TCNAME###", testCaseName).replaceAll("###TCDETAILS###", tr);
        FlatFileWork.createAndDataWriteIntoFile(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH+ "/"+tcReportName, tcReportString);
    }

    static String executionStartedAt = "";
    public static void main(String[] args) throws SQLException {

        //Create Results table structure
        setupResultsFolderStructure();

        //Create Results Database
        MDB_TestResults_DBWork.createDataBase(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH,
                MSACCESS_DataBaseConstants.MSACCESS_RESULTS_DATABASE_NAME);

        //Create Results Table
        MDB_TestResults_DBWork.createTable(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH,
                MSACCESS_DataBaseConstants.MSACCESS_RESULTS_DATABASE_NAME, MSACCESS_DataBaseConstants.MSACCESS_REPORTS_TABLE_NAME,
                MSACCESS_DataBaseConstants.MSACCESS_REPORTS_TABLE_SCHEMA);

        for (int i = 0; i < 10; i++) {
            MDB_TestResults_DBWork.insertRecordIntoTable("Login Validation Positive", "Enter UID : "+i, "Enter PWD: "+i, "PASS", "2020-02-26 10:10:10",
                    "c:/csr.png");
        }
        for (int i = 0; i < 10; i++) {
            MDB_TestResults_DBWork.insertRecordIntoTable("Login Validation Negative", "Enter UID : "+i, "Enter PWD : "+i, "PASS", "2020-02-26 10:10:10",
                    "c:/csr.png");
        }



        executionStartedAt = DateTimeWork.getCurrentDateTime();
        ArrayList<String> allTestCasesName_AL = new ArrayList<String>();
        allTestCasesName_AL = MDB_TestResults_DBWork.getAllTestCaseNames(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH);
        System.out.println("allTestCasesName_AL : "+allTestCasesName_AL);
        for(String testCase : allTestCasesName_AL){
            LinkedHashMap<String, LinkedHashMap<String, String>> testCasesData_LHM = new  LinkedHashMap<String, LinkedHashMap<String, String>>();
            testCasesData_LHM = MDB_TestResults_DBWork.getTestCaseData(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH,
                    testCase);

            //Format the Database Data
            setTestCasesSummaryReportData(testCase, testCasesData_LHM);

            //Create Test wise detailed results
            createTestCaseReport(testCase, testCasesData_LHM);
        }

        //Create Summary results
        createTestSummaryReport(testCaseesSummaryReportData_LHM);

        System.exit(0);









    }


}
