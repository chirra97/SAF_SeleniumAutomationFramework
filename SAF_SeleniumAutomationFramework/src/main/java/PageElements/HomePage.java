package PageElements;

import Config.ElementActions.*;
import org.openqa.selenium.By;

public class HomePage {

    public static TextBox userId = new TextBox(By.xpath("//input[@class='gLFyf gsfi']"), true);
    public static Link gmail = new Link(By.xpath("//a[@class='gb_g']"), true);
    public static ListBox tools = new ListBox(By.xpath("//select[@name='country']"), true);

}
