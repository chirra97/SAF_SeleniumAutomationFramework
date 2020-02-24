package Config.Utilities;

import Config.DriverSetup.DriverSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class ElementsCustomCode {
    public static void main(String[] args) {


    }

    private boolean highlightElement(WebElement element) {
        if (element == null) {
            System.out.println("Element is NULL");
            return false;
        }
        try {
            ((JavascriptExecutor) DriverSetup.driver).executeScript("arguments[0].style.border='3px solid red'", element);
        } catch (Exception e) {
        }
        return true;
    }

    public boolean waitForElementVisible(By webElementBy, boolean maxWaitForElementStatus) {
        try {
            WebDriverWait waitObj;
            if (maxWaitForElementStatus)
                waitObj = new WebDriverWait(new DriverSetup().driver, new DriverSetup().maxWaitTimeForElement);
            else
                waitObj = new WebDriverWait(new DriverSetup().driver, 5);
            waitObj.until(ExpectedConditions.visibilityOf(new DriverSetup().driver.findElement(webElementBy)));
            highlightElement(new DriverSetup().driver.findElement(webElementBy));
            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }

    public boolean waitForElementClickable(By webElementBy, boolean maxWaitForElementStatus) {
        try {
            WebDriverWait waitObj;
            if (maxWaitForElementStatus)
                waitObj = new WebDriverWait(new DriverSetup().driver, new DriverSetup().maxWaitTimeForElement);
            else
                waitObj = new WebDriverWait(new DriverSetup().driver, 5);
            waitObj.until(ExpectedConditions.elementToBeClickable(new DriverSetup().driver.findElement(webElementBy)));
            highlightElement(new DriverSetup().driver.findElement(webElementBy));
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public String captureApplicationScreenShot(String folderPath, String fileName) {
        TakesScreenshot scrShot = ((TakesScreenshot) new DriverSetup().driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        String imagePath = folderPath + "\\" + fileName;
        File DestFile = new File(imagePath);
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath;
    }

}
