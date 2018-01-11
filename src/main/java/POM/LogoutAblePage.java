package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutAblePage extends AbstractPage {
    public static final String SIGNOUT_MENU = "Вийти";
    public LogoutAblePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage logOut() {
        webDriver.findElement(By.linkText(SIGNOUT_MENU)).click();
        return new HomePage(webDriver);
    }
    public boolean isInitialized() {
        return webDriver.findElement(By.linkText(SIGNOUT_MENU)).isEnabled();
    }
}
