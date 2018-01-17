package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePage extends AbstractPage {
    //Test1
    public final String USER_LOGIN = "nedved198725";
    public final String USER_PASSWORD = "nedved1987";
    //@FindBy(name="Login")
    @FindBy(how=How.NAME, using="Login")
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
    //Test2
    @FindAll(@FindBy(how = How.CSS, using = "div.left>a"))
    public static List<WebElement> NEW_FAVOURITES_LABELS_LEFT_LIST;
    @FindBy(css="div.right")
    public static List<WebElement> NEW_FAVOURITES_LABELS_RIGHT;
    @FindBy(css = "div.left>a")
    public static WebElement COLOR_FAVOURITES_LABELS;
    @FindBy(className = "error-text")
    public static WebElement ERROR_DATA;
    //Test3
    @FindBy(css = "input[type^='submit']")//Поиск по началу строки//
    public static WebElement SEARCH_BUTTON;
    //Test4
    @FindBy(tagName = "h2")
    public static List<WebElement> h2;
    @FindAll(@FindBy(how = How.CSS, using = "#feed"))
    public static List<WebElement> allElements;
    //Test5
    @FindBy(linkText = "Листи")
    public static WebElement MESSAGES;

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
        webDriver.navigate().to(MAIL_URL);
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
