package Config.DataBase;

import Config.Utilities.DateTimeWork;
import Config.Utilities.FileDirectoryWork;

import java.sql.SQLException;
import java.util.ArrayList;
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

    static LinkedHashMap<String, LinkedHashMap<String, String>> testCaseesReportLogDetails_LHM = new LinkedHashMap<String, LinkedHashMap<String, String>>();
    public static void setTestCaseesReportLogDetails(String testCaseName, LinkedHashMap<String, LinkedHashMap<String, String>> testCaseData_LHM){
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

        testCaseesReportLogDetails_LHM.put(testCaseName, testCaseReportDetils_LHS);
    }



    public static void main(String[] args) throws SQLException {

        ArrayList<String> allTestCasesName_AL = new ArrayList<String>();
        allTestCasesName_AL = MDB_TestResults_DBWork.getAllTestCaseNames("src/test/Results/CSR_SAF_RESULTS_1582727021199");
        System.out.println("allTestCasesName_AL : "+allTestCasesName_AL);
        for(String testCase : allTestCasesName_AL){
            LinkedHashMap<String, LinkedHashMap<String, String>> testCaseData_LHM = new  LinkedHashMap<String, LinkedHashMap<String, String>>();
            testCaseData_LHM = MDB_TestResults_DBWork.getTestCaseData("src/test/Results/CSR_SAF_RESULTS_1582727021199",
                    testCase);

            setTestCaseesReportLogDetails(testCase, testCaseData_LHM);
        }

        System.out.println("testCaseesReportLogDetails_LHM : "+testCaseesReportLogDetails_LHM);


        System.exit(0);




        setupResultsFolderStructure();

        MDB_TestResults_DBWork.createDataBase(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH,
                MSACCESS_DataBaseConstants.MSACCESS_RESULTS_DATABASE_NAME);

        MDB_TestResults_DBWork.createTable(MSACCESS_DataBaseConstants.MSACCESS_RESULTS_PATH,
                MSACCESS_DataBaseConstants.MSACCESS_RESULTS_DATABASE_NAME, MSACCESS_DataBaseConstants.MSACCESS_REPORTS_TABLE_NAME,
                MSACCESS_DataBaseConstants.MSACCESS_REPORTS_TABLE_SCHEMA);

        for (int i = 0; i < 10; i++) {
              MDB_TestResults_DBWork.insertRecordIntoTable("Login Validation Positive", "Enter UID", "Enter PWD", "PASS", "2020-02-26 10:10:10",
                    "c:/csr.png");
        }

        for (int i = 0; i < 10; i++) {
            MDB_TestResults_DBWork.insertRecordIntoTable("Login Validation Negative", "Enter UID", "Enter PWD", "PASS", "2020-02-26 10:10:10",
                    "c:/csr.png");
        }



    }


}
