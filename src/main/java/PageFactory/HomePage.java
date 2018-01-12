package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    //Test01
    public final String USER_LOGIN = "nedved198725";
    public final String USER_PASSWORD = "nedved1987";
    @FindBy(name="Login")
    public static WebElement LOGIN_FIELD;
    @FindBy(name="Password")
    public static WebElement PASSWORD_FIELD;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement LOGIN_BUTTON;
    @FindBy(id="id-1")
    public static WebElement MAIL_LOGIN;
    @FindBy(id="id-2")
    public static WebElement MAIL_PASSWORD;
    @FindBy(className="button__content")
    public static WebElement MAIL_SUBMIT;
    //Test03
    @FindBy(css = "input[type^='submit']")//Поиск по началу строки//
    public static WebElement SEARCH_BUTTON;
    //Test04
    @FindBy(css = "#feed")
    public static WebElement SEARCH_MAIN_LINKS;
    @FindBy(className = "error-text")
    public static WebElement ERROR_DATA;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
     }
    public HomePage logIn() {
        LOGIN_FIELD.sendKeys(USER_LOGIN);
        PASSWORD_FIELD.sendKeys(USER_PASSWORD);
        LOGIN_BUTTON.submit();
        return this;
        //return new HomePage(webDriver);
    }
    public HomePage Mail_logIn() {
        MAIL_LOGIN.sendKeys(USER_LOGIN);
        MAIL_PASSWORD.sendKeys(USER_PASSWORD);
        MAIL_SUBMIT.submit();
        return this;
    }
    public HomePage LoginDB(String USER_LOGIN, String USER_PASSWORD){
        LOGIN_FIELD.clear();
        LOGIN_FIELD.sendKeys(USER_LOGIN);
        PASSWORD_FIELD.clear();
        PASSWORD_FIELD.sendKeys(USER_PASSWORD);
        LOGIN_BUTTON.submit();
        return new HomePage(webDriver);
    }
 }
