package PageFactory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class ProfilePage extends AbstractPage {

	@FindBy(linkText = "Листи")//check
	public static WebElement USER_PROFILE_LINK;
	@FindBy(css = ".login-button__menu-icon")//check
	public static WebElement USER_SIGNOUT_MENU;
	@FindBy(id = "login__logout")
	public static WebElement USER_SIGNOUT_LINK;
	public ProfilePage(WebDriver webDriver) {
		super(webDriver);
	}

	@Step("Step: User has moved to the ProfilePage")
	public ProfilePage goToProfilePage() {
		USER_PROFILE_LINK.click();
		return new ProfilePage(webDriver);
	}
	public ProfilePage exit(){
		USER_SIGNOUT_MENU.click();
		USER_SIGNOUT_LINK.click();
		return new ProfilePage(webDriver);
	}
}