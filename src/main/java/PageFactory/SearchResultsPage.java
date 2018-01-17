package PageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage {
    public final String EMAIL = "nedved198725@gmail.com";
    public final String SUBJECT_TEXT = "PageFactoryPattern_Test";
    public final String BODY_TEXT = "UkrNet_Test_MessageFromPageFactoryPattern";
    @FindBy(css = "input[type='text']")  //input[type^='text']	search	//input[@type='text']
    public static WebElement SEARCH_INPUT;
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

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }


    public SearchResultsPage doSearch(String searchValue) {
        SEARCH_INPUT.click();
        SEARCH_INPUT.sendKeys(searchValue);
        SEARCH_INPUT.sendKeys(Keys.ENTER);
        return new SearchResultsPage(webDriver);
    }
    public SearchResultsPage SendEmail (){
        BUTTON_WRITE_EMAIL.click();
        SEND_TO.sendKeys(EMAIL);
        SUBJECT_MAIL.sendKeys(SUBJECT_TEXT);
        webDriver.switchTo().frame(frame);
        BODY_MAIL.sendKeys(BODY_TEXT);
        webDriver.switchTo().defaultContent();
        BUTTON_SEND_EMAIL.click();
        return new SearchResultsPage(webDriver);
    }
}
