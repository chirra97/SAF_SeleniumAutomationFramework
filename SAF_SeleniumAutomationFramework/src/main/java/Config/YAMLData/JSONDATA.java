package Config.YAMLData;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

public class JSONDATA {


    private Yaml yamlObj = new Yaml();
    private String testDataYamlFilePath = "src/test/TestData/CUSTOM_YAMLDATA/JSONDATA.yaml";
    private FileInputStream testDataYamlFilePathFISObj;
    {
        try {
            testDataYamlFilePathFISObj = new FileInputStream(new File(testDataYamlFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static LinkedHashMap<String, Object> testDataFileData_lhm = null;
    public void configTestCaseTestDataYaml(){
        testDataFileData_lhm = (LinkedHashMap<String, Object>) yamlObj.load(testDataYamlFilePathFISObj);
    }
    private static LinkedHashMap<String, Object> testCaseData_lhm;
    public void loadTestCaseTestData(String TC_ID){
        if(testDataFileData_lhm == null)
            configTestCaseTestDataYaml();
        testCaseData_lhm = (LinkedHashMap<String, Object>)testDataFileData_lhm.get(TC_ID);
    }

    public String getTestCaseFieldData(String fieldName){
        return testCaseData_lhm.get(fieldName).toString();
    }


    public static void main(String[] args) {
        //YAMLTestData testDataObj = new YAMLTestData();
        new JSONDATA().configTestCaseTestDataYaml();
        new JSONDATA().loadTestCaseTestData("TC001");

        for(String key : testCaseData_lhm.keySet()){
            System.out.println("key : "+key+" <> "+testCaseData_lhm.get(key));
        }
}
}
