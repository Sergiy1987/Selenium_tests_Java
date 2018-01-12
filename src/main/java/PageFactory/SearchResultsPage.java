package PageFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage {
    public final String EMAIL = "nedved198725@gmail.com";
    public final String SUBJECT_TEXT = "Subject_test";
    @FindBy(css = "input[type='text']")  //input[type^='text']	search	//input[@type='text']
    public static WebElement SEARCH_INPUT;
    @FindBy(xpath = "//div[@id='content']/aside/button")
    public static WebElement BUTTON_WRITE_EMAIL;
    @FindBy(name = "toInput")
    public static WebElement SEND_TO;
    @FindBy(name="subject")
    public static WebElement SUBJECT_MAIL;
    @FindBy(xpath = "//div[@id='screens']/div/div/div/button")
    public static WebElement BUTTON_SEND_EMAIL;

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
        BUTTON_SEND_EMAIL.click();
        return new SearchResultsPage(webDriver);
    }
}
