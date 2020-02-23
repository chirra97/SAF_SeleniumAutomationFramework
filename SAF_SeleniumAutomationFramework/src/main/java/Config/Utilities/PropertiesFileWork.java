package Config.Utilities;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Properties;

public class PropertiesFileWork {


    static LinkedHashMap<String, String> propertiesFileDataLHM = null;

    public static void createPropertiesFileWithData(String folderPath, String fileName,
                                                    LinkedHashMap<String, String> fileDataLHM) {

        try (OutputStream output = new FileOutputStream(folderPath + "\\" + fileName)) {
            Properties prop = new Properties();
            for (String key : fileDataLHM.keySet())
                prop.setProperty(key, fileDataLHM.get(key));
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void loadPropertiesFileData(String filePath) {
        propertiesFileDataLHM = new LinkedHashMap<String, String>();
        try (InputStream input = new FileInputStream(filePath)) {
            Properties prop = new Properties();
            prop.load(input);
            for (Object key : prop.keySet())
                propertiesFileDataLHM.put(key.toString(), prop.get(key).toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("propertiesFileDataLHM : " + propertiesFileDataLHM);
    }

    public static void appendDataToPropertiesFile(String filePath, LinkedHashMap<String, String> fileDataLHM) {
        propertiesFileDataLHM = new LinkedHashMap<String, String>();
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            Properties prop = new Properties();
            File file = new File(filePath);
            fileIn = new FileInputStream(file);
            prop.load(fileIn);

            for (Object key : prop.keySet())
                prop.setProperty(key.toString(), prop.get(key).toString());
            fileOut = new FileOutputStream(file);
            prop.store(fileOut, "CSR properties");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadPropertiesFileData(filePath);
    }

    public static void appendDataToPropertiesFile(String filePath, String key, String value) {
        propertiesFileDataLHM = new LinkedHashMap<String, String>();
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            Properties prop = new Properties();
            File file = new File(filePath);
            fileIn = new FileInputStream(file);
            prop.load(fileIn);
            prop.setProperty(key, value);
            fileOut = new FileOutputStream(file);
            prop.store(fileOut, "CSR properties");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadPropertiesFileData(filePath);
    }

    public static void updateDataInPropertiesFile(String filePath, String key, String value) {
        propertiesFileDataLHM = new LinkedHashMap<String, String>();
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            Properties prop = new Properties();
            File file = new File(filePath);
            fileIn = new FileInputStream(file);
            prop.load(fileIn);
            prop.remove(key);
            prop.setProperty(key, value);
            fileOut = new FileOutputStream(file);
            prop.store(fileOut, "CSR properties");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadPropertiesFileData(filePath);
    }
    public static void removeDataInPropertiesFile(String filePath, String key, String value) {
        propertiesFileDataLHM = new LinkedHashMap<String, String>();
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            Properties prop = new Properties();
            File file = new File(filePath);
            fileIn = new FileInputStream(file);
            prop.load(fileIn);
            prop.remove(key);
            fileOut = new FileOutputStream(file);
            prop.store(fileOut, "CSR properties");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loadPropertiesFileData(filePath);
    }

}
