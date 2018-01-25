package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends AbstractPage {
	public static final String USER_PROFILE_LINK = "//*[@id=\"login-block\"]/div/ul/li[1]/a";
	public static final String USER_SIGNOUT_MENU = ".login-button__menu-icon";
	public static final String USER_SIGNOUT_LINK = "login__logout";

	public static final String BUTTON_WRITE_EMAIL = "//div[@id='content']/aside/button";
	public static final String SEND_TO = "toInput";
	public static final String SUBJECT_MAIL = "subject";
	public static final String EMAIL = "nedved198725@gmail.com";
	public static final String SUBJECT_TEXT = "PageObjectPattern_Test";
	public static final String FRAME_ID = "mce_0_ifr";
	public static final String BODY_MAIL  = "tinymce";
	public static final String BODY_TEXT  = "UkrNet_Test_Message_FromPageObjectPattern";
	public static final String BUTTON_SEND_EMAIL = "//div[@id='screens']/div/div/div/button";
	public static final String LINK_TEXT_SEND = "лист";
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
	public ProfilePage SendEmail (){
		webDriver.findElement(By.xpath(BUTTON_WRITE_EMAIL)).click();
		webDriver.findElement(By.name(SEND_TO)).sendKeys(EMAIL);
		webDriver.findElement(By.name(SUBJECT_MAIL)).sendKeys(SUBJECT_TEXT);
		webDriver.switchTo().frame(FRAME_ID);
		webDriver.findElement(By.id(BODY_MAIL)).sendKeys(BODY_TEXT);
		webDriver.switchTo().defaultContent();
		webDriver.findElement(By.xpath(BUTTON_SEND_EMAIL)).click();
		return new ProfilePage(webDriver);
	}
}