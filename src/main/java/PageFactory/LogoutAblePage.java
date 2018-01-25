package PageFactory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class LogoutAblePage extends AbstractPage {
    @FindBy(linkText="Вийти")
    public static WebElement SIGNOUT_MENU;
    public LogoutAblePage(WebDriver webDriver) {
        super(webDriver);
    }
    @Step("Step: User LogOut from HomePage")
    public HomePage logOut() {
        SIGNOUT_MENU.click();
        return new HomePage(webDriver);
    }
    public boolean isInitialized() {
        return SIGNOUT_MENU.isDisplayed();
    }
 }
