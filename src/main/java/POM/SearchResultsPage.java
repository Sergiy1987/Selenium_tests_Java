package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends AbstractPage {
    public static final String SEARCH_INPUT ="input[type='text']";	//input[type^='text']	search	//input[@type='text']

    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchResultsPage doSearch(String searchValue) {
        webDriver.findElement(By.cssSelector(SEARCH_INPUT)).click();
        webDriver.findElement(By.cssSelector(SEARCH_INPUT)).sendKeys(searchValue);
        webDriver.findElement(By.cssSelector(SEARCH_INPUT)).sendKeys(Keys.ENTER);
        return new SearchResultsPage(webDriver);
    }
}
