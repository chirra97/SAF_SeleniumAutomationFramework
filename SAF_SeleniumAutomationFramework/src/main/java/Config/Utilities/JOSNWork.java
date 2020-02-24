package Config.Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class JOSNWork {

    static String getJSONFileData_BR(String filePath) {
        File file = new File(filePath);
        BufferedReader br = null;
        String fileData = "";
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
                fileData = fileData + "" + line.trim();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return fileData;
    }

    static Object configJSONFile(String filePath) {
        Object obj = null;
        try {
            // obj = new JSONParser().parse(new FileReader(filePath));
            obj = new JSONParser().parse(getJSONFileData_BR(filePath));
        } catch (Exception e) {
        }
        return obj;
    }

    static JSONArray extractAllItems(Object obj, String jsonTagName) {
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray itemsList = (JSONArray) jsonObject.get(jsonTagName);
        return itemsList;
    }

    static JSONArray extractAllArrayData(String jsonString, String jsonTagName) {
        Object obj = null;
        try {
            obj = new JSONParser().parse(jsonString);
        } catch (Exception e1) {
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray arrayListData = (JSONArray) jsonObject.get(jsonTagName);
        return arrayListData;
    }

    static String getTagValue(JSONObject jsonObj, String jsonTagName) {
        String jsonTagValue = "";
        try {
            jsonTagValue = jsonObj.get(jsonTagName).toString();
        } catch (Exception e) {
        }
        System.out.println(jsonTagName + " <> : " + jsonTagValue);
        return jsonTagValue;
    }

    static String getTagValue(String jsonString, String jsonTagName) {
        Object obj = null;
        try {
            obj = new JSONParser().parse(jsonString);
        } catch (Exception e1) {
        }

        JSONObject jsonObject = (JSONObject) obj;
        String jsonTagValue = "";
        try {
            jsonTagValue = jsonObject.get(jsonTagName).toString();
        } catch (Exception e) {
        }
        System.out.println(jsonTagName + " <> : " + jsonTagValue);
        return jsonTagValue;
    }
}
