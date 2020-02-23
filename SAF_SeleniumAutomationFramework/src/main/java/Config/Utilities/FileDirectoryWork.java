package Config.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileDirectoryWork {


    public boolean isFileOrDirectoryExists(String path) {
        File fileObj = new File(path);
        return fileObj.exists();
    }

    public boolean createDirectory(String path) {
        File fileObj = new File(path);
        if (!fileObj.exists()) {
            try {
                fileObj.mkdir();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean createFile(String path) {
        File fileObj = new File(path);
        if (!fileObj.exists()) {
            try {
                fileObj.createNewFile();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public ArrayList<String> getAllFileNamesFromFolder(String path) {
        ArrayList<String> fileNamesAL = new ArrayList<String>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files)
            fileNamesAL.add(file.getName());
        return fileNamesAL;
    }

    public ArrayList<String> getAllFolderNamesFromFolder(String path) {
        ArrayList<String> folderNamesAL = new ArrayList<String>();
        File directoryPath = new File(path);
        String[] contents = directoryPath.list();
        for (int i = 0; i < contents.length; i++)
            folderNamesAL.add(contents[i]);
        return folderNamesAL;
    }

    public String getFileLastModefiedDate(String path){
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return sdf.format(file.lastModified());
    }



    public static void main(String[] args) {
        System.out.println(""+new FileDirectoryWork().getFileLastModefiedDate("C:\\Users\\SureshChirra\\Desktop\\Account\\Java_WorkSpace\\SeleniumProject\\chromedriver.exe"));
    }
}
