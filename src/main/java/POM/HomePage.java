package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//https://www.slideshare.net/QAFest/qa-fes-2016-page-objects-e
//http://www.autotest.org.ua/optimization-test-page-object/


public class HomePage extends AbstractPage {
    //Test01

    public static final String USER_LOGIN = "nedved198725";//
    public static final String USER_PASSWORD = "nedved1987";//
    public static final String LOGIN_FIELD = "Login";
    public static final String PASSWORD_FIELD = "Password";
    public static final String LOGIN_BUTTON = "//button[@type='submit']";
    public static final String ERROR_DATA = "error-text";
    //test02
    public static final String NEW_FAVOURITES_LABELS_LEFT = "div.left"; //div.left
    public static final String NEW_FAVOURITES_LABELS_RIGHT = "div.right";
    public static final String COLOR_FAVOURITES_LABELS = "div.left>a";

    //Test03
    public static final String SEARCH_BUTTON = "input[type^='submit']";//Поиск по началу строки
    //Test04

    public static final String SEARCH_MAIN_LINKS = "#feed";
    public static final String MAIL_LOGIN = "id-1";
    public static final String MAIL_PASSWORD = "id-2";
    public static final String MAIL_SUBMIT = "button__content";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        webDriver.getTitle();
//        assertTrue(webDriver.findElement(By.name(LOGIN_FIELD)).isEnabled());
//        assertTrue(webDriver.findElement(By.name(PASSWORD_FIELD)).isEnabled());
//        assertTrue(webDriver.findElement(By.xpath(LOGIN_BUTTON)).isEnabled());
    }
    public HomePage logIn() {
        webDriver.findElement(By.name(LOGIN_FIELD)).sendKeys(USER_LOGIN);
        webDriver.findElement(By.name(PASSWORD_FIELD)).sendKeys(USER_PASSWORD);
        webDriver.findElement(By.xpath(LOGIN_BUTTON)).submit();
        return this;
        //return new HomePage(webDriver);
    }
    public HomePage Mail_logIn() {
        webDriver.findElement(By.id(MAIL_LOGIN)).sendKeys(USER_LOGIN);
        webDriver.findElement(By.id(MAIL_PASSWORD)).sendKeys(USER_PASSWORD);
        webDriver.findElement(By.className(MAIL_SUBMIT)).submit();
        return this;
       }
    public HomePage LoginDB(String USER_LOGIN, String USER_PASSWORD){
        WebElement Login = webDriver.findElement(By.name(LOGIN_FIELD));
        Login.clear();
        Login.sendKeys(USER_LOGIN);

        WebElement Password = webDriver.findElement(By.name(PASSWORD_FIELD));
        Password.clear();
        Password.sendKeys(USER_PASSWORD);

        WebElement Button = webDriver.findElement(By.xpath(LOGIN_BUTTON));
        Button.click();
        return new HomePage(webDriver);
    }
   }
