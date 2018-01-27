package Test_PageFactory;

import PageFactory.HomePage;
import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static PageFactory.HomePage.SEARCH_BUTTON;
import static Tools.ExtentManager.createTest;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
@Listeners({Listener.class})
@Epic("HomePage")
@Feature("VerifyButtonForVisibleOrInVisible")
public class Test3 extends MainTest {
    private ExtentTest test;

    @Test(priority = 0, groups = "HomePage", description = "Scenario: Verify What Find Search Button Is Visible")
    @Description("Test Description: Search Button Is Visible on the HomePage.")
    @Story("Check what search Button Is Visible on the HomePage")
    @Step("Step: Search Button Is Visible on the HomePage")
    public void verifyThatFindButtonIsVisible() {
        test = createTest("HomePage", "verifyWhatFindButtonIsVisible");
        assertTrue(SEARCH_BUTTON.isDisplayed());
        test.pass("SEARCH_BUTTON: " + SEARCH_BUTTON.getText() + " Status: " + SEARCH_BUTTON.isDisplayed() + " and is Displayed");
    }
    @Test(priority = 1, groups = "HomePage", description = "Scenario: Verify What Find Search Button Is InVisible")
    @Description("Test Description: Search Button Is InVisible on the HomePage.")
    @Story("Check what search Button Is InVisible on the HomePage")
    @Step("Step: Search Button Is InVisible on the HomePage")
    public void verifyThatFindButtonIsInVisible() {
        test = createTest("HomePage", "verifyThatFindButtonIsNotVisible");
        new HomePage(webDriver);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style = 'display: none';",SEARCH_BUTTON);
        assertFalse(SEARCH_BUTTON.isDisplayed());
        test.pass("SEARCH_BUTTON: " + SEARCH_BUTTON.getText() + " Status: " + SEARCH_BUTTON.isDisplayed() + " and is not Displayed");
    }
}
