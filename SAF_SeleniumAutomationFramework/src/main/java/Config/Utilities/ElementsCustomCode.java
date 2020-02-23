package Config.Utilities;

import Config.DriverSetup.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ElementsCustomCode {
    private boolean highlightElement(WebElement element) {
        if(element == null) {
            System.out.println("Element is NULL");
            return false;
        }
        try {
            ((JavascriptExecutor) DriverSetup.driver).executeScript("arguments[0].style.border='3px solid red'", element);
        } catch (Exception e) {
        }
        return true;
    }
    public boolean waitForElementVisible(By webElementBy, boolean maxWaitForElementStatus){
        try{
            WebDriverWait waitObj;
            if(maxWaitForElementStatus)
                waitObj = new WebDriverWait(new DriverSetup().driver, new DriverSetup().maxWaitTimeForElement);
            else
                waitObj = new WebDriverWait(new DriverSetup().driver, 5);
            waitObj.until(ExpectedConditions.visibilityOf(new DriverSetup().driver.findElement(webElementBy)));
            highlightElement(new DriverSetup().driver.findElement(webElementBy));
            return true;
        }catch (Exception error){
            error.printStackTrace();
            return false;
        }
    }
    public boolean waitForElementClickable(By webElementBy, boolean maxWaitForElementStatus){
        try{
            WebDriverWait waitObj;
            if(maxWaitForElementStatus)
                waitObj = new WebDriverWait(new DriverSetup().driver, new DriverSetup().maxWaitTimeForElement);
            else
                waitObj = new WebDriverWait(new DriverSetup().driver, 5);
            waitObj.until(ExpectedConditions.elementToBeClickable(new DriverSetup().driver.findElement(webElementBy)));
            highlightElement(new DriverSetup().driver.findElement(webElementBy));
            return true;
        }catch (Exception error){
            return false;
        }
    }





    public static void main(String[] args) {


    }

}
