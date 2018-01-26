package PageFactory;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends AbstractPage {
    @FindBy(css = "input[type='text']")  //input[type^='text']	search	//input[@type='text']
    public static WebElement SEARCH_INPUT;
    public SearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }
    @Step("Step: User search string on the SearchResultsPage")
    public SearchResultsPage doSearch(String searchValue) {
        SEARCH_INPUT.click();
        SEARCH_INPUT.sendKeys(searchValue);
        SEARCH_INPUT.sendKeys(Keys.ENTER);
        return new SearchResultsPage(webDriver);
    }
}
