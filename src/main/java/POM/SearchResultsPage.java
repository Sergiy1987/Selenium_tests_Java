package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends AbstractPage {
    public static final String SEARCH_INPUT ="input[type='text']";	//input[type^='text']	search	//input[@type='text']
    public static final String BUTTON_WRITE_EMAIL = "//div[@id='content']/aside/button";
    public static final String SEND_TO = "toInput";
    public static final String SUBJECT_MAIL = "subject";
    public static final String EMAIL = "nedved198725@gmail.com";
    public static final String SUBJECT_TEXT = "Subject_test";
    public static final String FRAME_ID = "mce_0_ifr";
    public static final String BODY_MAIL  = "tinymce";
    public static final String BODY_TEXT  = "UkrNet_Test_Message_FromPageObjectPatternPage";
    public static final String BUTTON_SEND_EMAIL = "//div[@id='screens']/div/div/div/button";
    public static final String LINK_TEXT_SEND = "лист";

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchResultsPage doSearch(String searchValue) {
        webDriver.findElement(By.cssSelector(SEARCH_INPUT)).click();
        webDriver.findElement(By.cssSelector(SEARCH_INPUT)).sendKeys(searchValue);
        webDriver.findElement(By.cssSelector(SEARCH_INPUT)).sendKeys(Keys.ENTER);
        return new SearchResultsPage(webDriver);
    }
    public SearchResultsPage SendEmail (){
        webDriver.findElement(By.xpath(BUTTON_WRITE_EMAIL)).click();
        webDriver.findElement(By.name(SEND_TO)).sendKeys(EMAIL);
        webDriver.findElement(By.name(SUBJECT_MAIL)).sendKeys(SUBJECT_TEXT);
        webDriver.switchTo().frame(FRAME_ID);
        webDriver.findElement(By.id(BODY_MAIL)).sendKeys(BODY_TEXT);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath(BUTTON_SEND_EMAIL)).click();
        return new SearchResultsPage(webDriver);
    }
}
