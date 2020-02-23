package Config.YAMLData;


import Config.Enum.BaseConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class YAMLConfigDataRead {

    class loadConfigYamlFile{
        Yaml yamlObj = new Yaml();
        String configYamlFilePath = "src/test/TestData/BASE_YAMLDATA/CONFIG.yaml";
        FileInputStream configYamlFilePathFISObj;
        {
            try {
                configYamlFilePathFISObj = new FileInputStream(new File(configYamlFilePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        LinkedHashMap<String, Object> configFileData_lhm = null;
        void loadConfigYaml(){
            configFileData_lhm = (LinkedHashMap<String, Object>) yamlObj.load(configYamlFilePathFISObj);
            System.out.println("configData : "+configFileData_lhm.toString());
        }
        String scriptRunBrowser = "";
        String isParallel = "No";
        String threadCount = "1";
        String waitTime = "5";
        void setExeBrowser_IsParallel_ThreadCount(){
            LinkedHashMap<String, Object> runConfigData_lhm = (LinkedHashMap<String, Object>)configFileData_lhm.get("RunConfig");
            for(String runConfigKey : runConfigData_lhm.keySet()){
                if(runConfigKey.equalsIgnoreCase("Chrome") ||
                        runConfigKey.equalsIgnoreCase("Firefox") ||
                        runConfigKey.equalsIgnoreCase("IE")){
                    if((runConfigData_lhm.get(runConfigKey).toString()).equalsIgnoreCase("True")){
                        scriptRunBrowser = runConfigKey.toString();
                    }
                }else if(runConfigKey.equalsIgnoreCase("Isparallel")){
                    isParallel = runConfigData_lhm.get(runConfigKey).toString();
                    if(isParallel.equalsIgnoreCase("true")){
                        threadCount = runConfigData_lhm.get("ThreadCount").toString();
                    }
                }
                else if(runConfigKey.equalsIgnoreCase("WaitTime_Seconds")){
                    waitTime = runConfigData_lhm.get(runConfigKey).toString();
                }
            }
            System.out.println("scriptRunBrowser : "+scriptRunBrowser);
            System.out.println("isParallel : "+isParallel);
            System.out.println("threadCount : "+threadCount);
            System.out.println("waitTime : "+waitTime);

            System.setProperty(BaseConfig.exeBrowser.toString(), scriptRunBrowser);
            System.setProperty(BaseConfig.isParallel.toString(), isParallel);
            System.setProperty(BaseConfig.threadCount.toString(), threadCount);
            System.setProperty(BaseConfig.maxWaitTime.toString(), waitTime);
        }
        ArrayList<String> setTestNGData(){
            ArrayList<String> exe_TCNUM_TCSCRIPT_Data_al = new  ArrayList<String>();
            LinkedHashMap<String, Object> exeTestCaseNumbers_lhm = (LinkedHashMap<String, Object>)configFileData_lhm.get("ExecuteTestCaseNumAndStatusMapping");
            for(String exeTestCaseKey : exeTestCaseNumbers_lhm.keySet()){
                String testcaseNumber = exeTestCaseNumbers_lhm.get(exeTestCaseKey).toString();
                if(testcaseNumber.equalsIgnoreCase("true")){
                    LinkedHashMap<String, Object> testScriptNameMap = (LinkedHashMap<String, Object>)configFileData_lhm.get("ExecuteTestCaseNumAndClassNameMapping");
                    String testScriptName = testScriptNameMap.get(""+exeTestCaseKey.toString()).toString();
                    exe_TCNUM_TCSCRIPT_Data_al.add("TC_NUM = "+exeTestCaseKey+"###TC_SCRIPTNAME = "+testScriptName);
                }
            }
            System.out.println("exe_TCNUM_TCSCRIPT_Data_al : "+exe_TCNUM_TCSCRIPT_Data_al);
            return exe_TCNUM_TCSCRIPT_Data_al;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        loadConfigYamlFile obj1 = new YAMLConfigDataRead().new loadConfigYamlFile();
        obj1.loadConfigYaml();
        obj1.setExeBrowser_IsParallel_ThreadCount();
        obj1.setTestNGData();
    }


    public ArrayList<String> YAMLConfigDataLoad(){
        YAMLConfigDataRead obj = new YAMLConfigDataRead();
        loadConfigYamlFile obj1 = obj.new loadConfigYamlFile();
        obj1.loadConfigYaml();
        obj1.setExeBrowser_IsParallel_ThreadCount();
        ArrayList<String> exeData = obj1.setTestNGData();
        return exeData;
    }


}
