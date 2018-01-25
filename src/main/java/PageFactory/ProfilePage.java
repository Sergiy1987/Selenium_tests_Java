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
		//send email
	public final String EMAIL = "nedved198725@gmail.com";
	public final String SUBJECT_TEXT = "PageFactoryPattern_Test";
	public final String BODY_TEXT = "UkrNet_Test_MessageFromPageFactoryPattern";
	@FindBy(xpath = "//div[@id='content']/aside/button")
	public static WebElement BUTTON_WRITE_EMAIL;
	@FindBy(name = "toInput")
	public static WebElement SEND_TO;
	@FindBy(name="subject")
	public static WebElement SUBJECT_MAIL;
	@FindBy(id = "mce_0_ifr")
	public static WebElement frame;
	@FindBy(id = "tinymce")
	public static WebElement BODY_MAIL;
	@FindBy(xpath = "//div[@id='screens']/div/div/div/button")
	public static WebElement BUTTON_SEND_EMAIL;
	@FindBy(linkText = "лист")
	public static WebElement LINK_TEXT_SEND;

	public ProfilePage(WebDriver webDriver) {
		super(webDriver);
	}

	@Step("Step: User has moved to the ProfilePage")
	public ProfilePage goToProfilePage() {
		USER_PROFILE_LINK.click();
		return new ProfilePage(webDriver);
	}
	@Step("Step: User LogOut from ProfilePage")
	public ProfilePage exit(){
		USER_SIGNOUT_MENU.click();
		USER_SIGNOUT_LINK.click();
		return new ProfilePage(webDriver);
	}
	@Step("Step: User send Email on the ProfilePage")
	public ProfilePage SendEmail (){
		BUTTON_WRITE_EMAIL.click();
		SEND_TO.sendKeys(EMAIL);
		SUBJECT_MAIL.sendKeys(SUBJECT_TEXT);
		webDriver.switchTo().frame(frame);
		BODY_MAIL.sendKeys(BODY_TEXT);
		webDriver.switchTo().defaultContent();
		BUTTON_SEND_EMAIL.click();
		return new ProfilePage(webDriver);
	}

}