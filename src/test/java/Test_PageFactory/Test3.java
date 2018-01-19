package Test_PageFactory;

import PageFactory.HomePage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import static PageFactory.HomePage.SEARCH_BUTTON;
import static Tools.ExtentManager.createTest;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Test3 extends MainTest {
    private ExtentTest test;
    @Test(priority = 0, groups = "HomePage")
    public void verifyThatFindButtonIsVisible() {
        test = createTest("HomePage", "verifyThatFindButtonIsVisible");
        assertTrue(SEARCH_BUTTON.isDisplayed());
        test.pass("SEARCH_BUTTON: " + SEARCH_BUTTON.getText() + " Status: " + SEARCH_BUTTON.isDisplayed() + " and is Displayed");
    }
    @Test(priority = 1, groups = "HomePage")
    public void verifyThatFindButtonIsNotVisible() {
        test = createTest("HomePage", "verifyThatFindButtonIsNotVisible");
        new HomePage(webDriver);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style = 'display: none';",SEARCH_BUTTON);
        assertFalse(SEARCH_BUTTON.isDisplayed());
        test.pass("SEARCH_BUTTON: " + SEARCH_BUTTON.getText() + " Status: " + SEARCH_BUTTON.isDisplayed() + " and is not Displayed");
    }
}
