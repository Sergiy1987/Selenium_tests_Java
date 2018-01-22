package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends AbstractPage {
	public static final String USER_PROFILE_LINK = "//*[@id=\"login-block\"]/div/ul/li[1]/a";
	public static final String USER_SIGNOUT_MENU = ".login-button__menu-icon";
	public static final String USER_SIGNOUT_LINK = "login__logout";
	public ProfilePage(WebDriver webDriver) {
		super(webDriver);
			}

	public ProfilePage goToProfilePage() {
		webDriver.findElement(By.xpath(USER_PROFILE_LINK)).click();
		return new ProfilePage(webDriver);
	}
	public ProfilePage exit(){
		webDriver.findElement(By.cssSelector(USER_SIGNOUT_MENU)).click();
		webDriver.findElement(By.id(USER_SIGNOUT_LINK)).click();
		return new ProfilePage(webDriver);
	}
}