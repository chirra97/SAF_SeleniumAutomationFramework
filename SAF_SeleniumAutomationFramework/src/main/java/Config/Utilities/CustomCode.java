package Config.Utilities;

public class CustomCode {


    public static String getSystemOSName() {
        String osName = System.getProperty("os.name");
        return osName;
    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir");
        return path;
    }

    public static String getTempFolderPath() {
        String path = System.getProperty("java.io.tmpdir");
        return path;
    }


    public static void main(String[] args) {
        getTempFolderPath();
    }
}
