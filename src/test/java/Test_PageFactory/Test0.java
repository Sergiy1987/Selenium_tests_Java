package Test_PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static PageFactory.HomePage.ERROR_DATA;
import static PageFactory.HomePage.LOGIN_BUTTON;
import static Tools.ExtentManager.createTest;
import static org.testng.AssertJUnit.assertEquals;
//https://examples.javacodegeeks.com/enterprise-java/testng/testng-parameters-annotation-example/
@Listeners({Listener.class})
@Epic("HomePage")
@Feature("Login Test with Parameters: LOGIN and PASSWORD")
public class Test0 extends MainTest {
    private ExtentTest test;
    private String requiredErrorMessage = "";

    @Test(groups = "HomePage", description = "Valid Login Scenario with Parameters: Login and Password.")
    @Description("Test Description: Login test with correct Parameters Login and Password.")
    @Story("Verify Login and Password with Parameters({\"USER\",\"PASSWORD\"})")
    @Parameters({ "LOGIN", "PASSWORD" })
public void LoginWithParameters(@Optional ("LOGIN") String LOGIN, @Optional ("PASSWORD") String PASSWORD) {
        test = createTest("HomePage", "LoginWithParameters");
        if (wait.elementDisplayedByWebElement(LOGIN_BUTTON)) {
            homePage.LoginParameters(LOGIN, PASSWORD);
            test.pass("LOGIN_BUTTON: " + LOGIN_BUTTON.getText() + " Status: " + LOGIN_BUTTON.isDisplayed() + " and is Displayed");
            String actualErrorDisplayed = ERROR_DATA.getText();
            assertEquals(requiredErrorMessage, actualErrorDisplayed);
            System.out.println(homePage.toString());
        } else {
            test.log(Status.FAIL,"LOGIN_BUTTON: " + LOGIN_BUTTON.getText() + " Status: " + LOGIN_BUTTON.isDisplayed() + " and doesn't Displayed");}
        }
}





