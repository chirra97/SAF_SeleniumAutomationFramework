package Config.DriverSetup;

import Config.Enum.BaseConfig;
import okio.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverSetup {

    static public WebDriver driver = null;
    static public int maxWaitTimeForElement = 60;
    static private String appBaseUrl = "http://www.google.com";
    static private String appTestUrl = "http://demo.guru99.com/test/newtours/register.php";

    public static void launChromeBrowser(String appURL) {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", "src/test/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(appURL);
        driver.manage().window().maximize();
        maxWaitTimeForElement = Integer.parseInt(System.getProperty(BaseConfig.maxWaitTime.toString()));
        driver.manage().timeouts().implicitlyWait(maxWaitTimeForElement, TimeUnit.SECONDS);
    }

}
