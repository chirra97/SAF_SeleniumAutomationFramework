package Config.ElementActions;

import Config.DriverSetup.DriverSetup;
import Config.Utilities.ElementsCustomCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Link {

    By webElementBy;
    boolean maxWaitForElementStatus;
    public Link(By webElementBy, boolean maxWaitForElementStatus){
        this.webElementBy = webElementBy;
        this.maxWaitForElementStatus = maxWaitForElementStatus;
    }

    WebElement buttonWebElement;
    public void click(){
        if(new ElementsCustomCode().waitForElementClickable(webElementBy, maxWaitForElementStatus))
            buttonWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            buttonWebElement = null;
        buttonWebElement.click();
    }
}
