package Config.ElementActions;

import Config.DriverSetup.DriverSetup;
import Config.Utilities.ElementsCustomCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextBox {

    private By webElementBy;
    private boolean maxWaitForElementStatus;
    public TextBox(By webElementBy, boolean maxWaitForElementStatus){
        this.webElementBy = webElementBy;
        this.maxWaitForElementStatus = maxWaitForElementStatus;
    }
    private WebElement textWebElement;
    public void enterText(String inputText){
        if(new ElementsCustomCode().waitForElementVisible(webElementBy, maxWaitForElementStatus))
            textWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            textWebElement = null;
        textWebElement.sendKeys(inputText);
    }
    public void clearAndEnterText(String inputText){
        if(new ElementsCustomCode().waitForElementVisible(webElementBy, maxWaitForElementStatus))
            textWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            textWebElement = null;
        textWebElement.clear();
        textWebElement.sendKeys(inputText);
    }
}
