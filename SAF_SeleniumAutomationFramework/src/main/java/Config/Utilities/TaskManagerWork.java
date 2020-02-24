package Config.Utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerWork {

    static void openFile(String filePath) throws InterruptedException {
        try {
            Desktop.getDesktop().open(new File(filePath));
            Thread.sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void killProcess(String processName) {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM " + processName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<String> getAllRunningProcessesFromTaskManager() throws IOException {
        ArrayList<String> runningAppNames_al = new ArrayList<String>();
        Process process = null;
        process = new ProcessBuilder("powershell",
                "\"gps| ? {$_.mainwindowtitle.length -ne 0} | Format-Table -HideTableHeaders  name, ID").start();
        try {
            Process finalProcess = process;
            new Thread(() -> {
                Scanner sc = new Scanner(finalProcess.getInputStream());
                if (sc.hasNextLine())
                    sc.nextLine();
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    runningAppNames_al.add(line);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return runningAppNames_al;
    }


}
