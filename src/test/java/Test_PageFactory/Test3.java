package Test_PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import static PageFactory.HomePage.SEARCH_BUTTON;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class Test3 extends MainTest {

    @Test(priority = 0, groups = "HomePage")
    public void verifyThatFindButtonIsVisible() {
        assertTrue(SEARCH_BUTTON.isDisplayed());
    }
    @Test(priority = 1, groups = "HomePage")
    public void verifyThatFindButtonIsNotVisible() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style = 'display: none';",SEARCH_BUTTON);
        assertFalse(SEARCH_BUTTON.isDisplayed());
    }
}
