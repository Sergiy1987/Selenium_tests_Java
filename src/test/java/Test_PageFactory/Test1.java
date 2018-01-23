package Test_PageFactory;

import Tools.SwitchToWindow;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static PageFactory.HomePage.LOGIN_BUTTON;
import static PageFactory.LogoutAblePage.SIGNOUT_MENU;
import static PageFactory.ProfilePage.USER_PROFILE_LINK;
import static Tools.ExtentManager.createTest;
import static Tools.Tools.takeScreenShot;
import static org.testng.AssertJUnit.assertTrue;
//https://www.swtestacademy.com/allure-testng/
//allure serve allure-results
//allure generate allure-results/ -o allure-report
public class Test1 extends MainTest  {
    private ExtentTest test;
    private String ClassName = this.getClass().getSimpleName();
    private StringBuilder verificationErrors;
    @Test(groups = "HomePage")
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
    @Test(groups ="ProfilePage",dependsOnMethods = "verifyLogin")
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
    @Test(groups = "LogOutPage", dependsOnMethods = "verifyUserProfileOpened")
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
        }catch (NoSuchElementException e){e.getMessage();}
    }
    @Test(groups = "HomePage", dataProvider = "Authentication_array")
    public void ArrayAuthentication(String USER_LOGIN, String USER_PASSWORD) {
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
        verificationErrors.append(e.toString());
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
