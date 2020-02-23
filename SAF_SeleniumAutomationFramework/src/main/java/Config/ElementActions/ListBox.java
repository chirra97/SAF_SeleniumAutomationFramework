package Config.ElementActions;

import Config.DriverSetup.DriverSetup;
import Config.Utilities.ElementsCustomCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ListBox {

    By webElementBy;
    boolean maxWaitForElementStatus;
    public ListBox(By webElementBy, boolean maxWaitForElementStatus){
        this.webElementBy = webElementBy;
        this.maxWaitForElementStatus = maxWaitForElementStatus;
    }

    WebElement selectWebElement;
    public void selectOptionByIndex(int indexNumber){
        if(new ElementsCustomCode().waitForElementVisible(webElementBy, maxWaitForElementStatus))
            selectWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            selectWebElement = null;
        Select selObj = new Select(selectWebElement);
        selObj.selectByIndex(indexNumber);
    }
    public void selectOptionByValue(String value){
        if(new ElementsCustomCode().waitForElementVisible(webElementBy, maxWaitForElementStatus))
            selectWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            selectWebElement = null;
        Select selObj = new Select(selectWebElement);
        selObj.selectByValue(value);
    }
    public void selectOptionByVisibleText(String visibleText){
        if(new ElementsCustomCode().waitForElementVisible(webElementBy, maxWaitForElementStatus))
            selectWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            selectWebElement = null;
        Select selObj = new Select(selectWebElement);
        selObj.selectByVisibleText(visibleText);
    }
}
