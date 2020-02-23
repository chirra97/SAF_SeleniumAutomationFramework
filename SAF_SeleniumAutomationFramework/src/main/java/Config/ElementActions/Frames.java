package Config.ElementActions;

import Config.DriverSetup.DriverSetup;
import Config.Utilities.ElementsCustomCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Frames {

    private By webElementBy;
    private int frameIndexNumber;
    private String frameName;
    private boolean maxWaitForElementStatus;
    public Frames(int frameIndexNumber, boolean maxWaitForElementStatus){
        this.frameIndexNumber = frameIndexNumber;
        this.maxWaitForElementStatus = maxWaitForElementStatus;
    }
    public Frames(String frameName, boolean maxWaitForElementStatus){
        this.frameName = frameName;
        this.maxWaitForElementStatus = maxWaitForElementStatus;
    }
    public Frames(By webElementBy, boolean maxWaitForElementStatus){
        this.webElementBy = webElementBy;
        this.maxWaitForElementStatus = maxWaitForElementStatus;
    }

    WebElement frameWebElement;
    public void switchToFrameByIndex(){
        DriverSetup.driver.switchTo().frame(frameIndexNumber);
    }
    public void switchToFrameByElement(){
        if(new ElementsCustomCode().waitForElementClickable(webElementBy, maxWaitForElementStatus))
            frameWebElement = DriverSetup.driver.findElement(webElementBy);
        else
            frameWebElement = null;
        DriverSetup.driver.switchTo().frame(frameWebElement);
    }
    public void switchToFrameByName(){
        if(new ElementsCustomCode().waitForElementVisible(By.className(frameName), maxWaitForElementStatus))
            frameWebElement = DriverSetup.driver.findElement(By.className(frameName));
        else
            frameWebElement = null;
        DriverSetup.driver.switchTo().frame(frameName);
    }
}
