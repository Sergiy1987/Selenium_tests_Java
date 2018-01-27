package Test_PageFactory;

import Tools.SwitchToWindow;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static PageFactory.HomePage.LOGIN_BUTTON;
import static PageFactory.LogoutAblePage.SIGNOUT_MENU;
import static PageFactory.ProfilePage.USER_PROFILE_LINK;
import static Test_PageFactory.Listener.saveTextLog;
import static Test_PageFactory.Listener.takeScreenShot;
import static Tools.ExtentManager.createTest;
import static org.testng.AssertJUnit.assertTrue;
//https://www.swtestacademy.com/allure-testng/
//allure serve allure-results
//allure generate allure-results/ -o allure-report
// mvn allure:serve
//mvn allure:report
//mvn io.qameta.allure:allure-maven:serve

@Listeners({Listener.class})
@Epic("HomePage/ProfilePage/LogOutPage")
@Feature("Login Tests/Profile Test/LogOut Test")
public class Test1 extends MainTest  {
    private ExtentTest test;
    private String ClassName = this.getClass().getSimpleName();
    private StringBuilder verificationErrors;
    @Test(groups = "HomePage", description = "Valid Login Scenario with correct username and password.")
    @Description("Test Description: Login test with correct username and correct password.")
    @Story("Valid username and password login test")
    public void verifyLogin() {
        try {
        test = createTest("HomePage", "VerifyLogin");
        if (wait.elementDisplayedByWebElement(LOGIN_BUTTON)) {
        test.pass("LOGIN_BUTTON: " + LOGIN_BUTTON.getText() + " Status: " + LOGIN_BUTTON.isDisplayed() + " and is Displayed");
        homePage.logIn();
        System.out.println(homePage.toString());
        } else {
        test.log(Status.FAIL,"LOGIN_BUTTON: " + LOGIN_BUTTON.getText() + " Status: " + LOGIN_BUTTON.isDisplayed() + " and doesn't Displayed");}
        } catch (Exception e) {
        test.log(Status.ERROR, e.getMessage());
        }
    }
    @Test(groups ="ProfilePage",dependsOnMethods = "verifyLogin",description = "When Login Scenario is finished, User go to the ProfilePage")
    @Description("Test Description: User has moved to the ProfilePage.")
    @Story("Verify Page UserProfile Open")
    public void verifyUserProfileOpened()  {
        //test = extent.createTest("ProfilePage", "verifyUserProfileOpened");
        test = createTest("ProfilePage", "verifyUserProfileOpened");
        wait.waitForPageLoaded();
        wait.waitForWebElementToBeClickAble(USER_PROFILE_LINK);
        test.pass("USER_PROFILE_LINK: " + USER_PROFILE_LINK.getText() + " Status: " + USER_PROFILE_LINK.isEnabled() + " and is Enabled");
        assertTrue(USER_PROFILE_LINK.isEnabled());
        profilePage.goToProfilePage();
        System.out.println(profilePage.toString());
        SwitchToWindow.Switch_to_parrent_opened_window(webDriver);
    }
    @Test(groups = "LogOutPage", dependsOnMethods = "verifyUserProfileOpened",description = "LogOut Scenario from HomePage")
    @Description("Test Description: User LogOutable from HomePage.")
    @Story("LogOut from HomePage")
    public void verifyLogout(Method method) throws NoSuchElementException {
        test = createTest("LogOutPage", "verifyLogout");
        try {
            wait.waitForPageLoaded();
            wait.waitForWebElementToBeClickAble(SIGNOUT_MENU);
            test.pass("SIGNOUT_MENU: " + SIGNOUT_MENU.getText() + " Status: " + SIGNOUT_MENU.isEnabled() + " and is Enabled");
            assertTrue(SIGNOUT_MENU.isEnabled());
            logoutablePage.logOut();
            System.out.println(logoutablePage.toString());
            takeScreenShot(webDriver, ClassName + "_" + method.getName() + ".png");
            saveTextLog("Screenshot taken" + " from " + ClassName + "_" + method.getName());
        }catch (NoSuchElementException ex){ex.getMessage();}
    }
    @Test(groups = "HomePage", dataProvider = "Authentication_array",description = "Valid Login Scenario with correct username and password from array.")
    @Description("Test Description: Array Authentication test with correct username and correct password.")
    @Story("Array Authentication with valid username and password login test")
    public void ArrayAuthentication(String USER_LOGIN,String USER_PASSWORD) {
        verificationErrors = new StringBuilder();
        try {
            test = createTest("HomePage", "Authentication_array");
            homePage.LoginDB(USER_LOGIN,USER_PASSWORD);
            wait.waitForPageLoaded();
            test.pass("USER_LOGIN: " + USER_LOGIN + ", USER_PASSWORD: " + USER_PASSWORD + " successfully");
            //wait.waitForClickable(By.linkText(SIGNOUT_MENU1));
            wait.waitForWebElementToBeClickAble(SIGNOUT_MENU);
            // System.out.println(SIGNOUT_MENU.getText());
            //WebElement LogOut = webDriver.findElement(By.linkText(SIGNOUT_MENU1));
             if (logoutablePage.isInitialized() && SIGNOUT_MENU.isEnabled()) {
             //if (LogOut.isDisplayed() && LogOut.isEnabled()) {
             logoutablePage.logOut();}
        } catch (Error e) {
        //Capture and append Exceptions/Errors
        verificationErrors.append(e.getMessage());
        }
    }
    @DataProvider(name = "Authentication_array")
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{"nedved198725", "nedved1987"},
                new Object[]{"nedved198725", "nedved1987"},
                new Object[]{"nedved198725", "nedved1987"},
                new Object[]{"nedved198725", "nedved1987"}
        };
    }
}
