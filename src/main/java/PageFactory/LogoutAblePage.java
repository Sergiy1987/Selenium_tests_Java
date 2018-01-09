package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutAblePage extends AbstractPage {
    @FindBy(linkText="Вийти")
    public static WebElement SIGNOUT_MENU;
    public static String SIGNOUT_MENU1 = "Вийти";
    public LogoutAblePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
    public HomePage logOut() {
        SIGNOUT_MENU.click();
        return new HomePage(webDriver);
    }
 }
