package Test_PageFactory;

import Tools.Tools;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static PageFactory.AbstractPage.HOMEPAGE_URL;
import static PageFactory.HomePage.LOGIN_BUTTON;
import static PageFactory.LogoutAblePage.SIGNOUT_MENU;
import static PageFactory.ProfilePage.USER_PROFILE_LINK;
import static org.testng.AssertJUnit.assertTrue;

public class Test1 extends MainTest  {
     String ClassName = this.getClass().getSimpleName();
    @Test(groups = "HomePage")
    public void verifyLogin() {
        if (wait.elementDisplayedByWebElement(LOGIN_BUTTON)){
            homePage.logIn();
            System.out.println(homePage.toString());
        }
        else {System.out.println(HOMEPAGE_URL + "the page is not loaded, please try again later");
        }
    }
    @Test(groups ="ProfilePage",dependsOnMethods = "verifyLogin")
    public void verifyUserProfileOpened()  {
        wait.waitForWebElementToBeClickAble(USER_PROFILE_LINK);
        assertTrue(USER_PROFILE_LINK.isEnabled());
        profilePage.goToProfilePage();
        System.out.println(profilePage.toString());
        }
    @Test(groups = "LogOutPage", dependsOnMethods = "verifyLogin")
    public void verifyLogout(Method method) {
        wait.waitForWebElementToBeClickAble(SIGNOUT_MENU);
        assertTrue(SIGNOUT_MENU.isEnabled());
        logoutablePage.logOut();
        System.out.println(logoutablePage.toString());
        Tools.takeScreenShot(webDriver, ClassName + "_" + method.getName()+ ".png");
    }
    @Test(dataProvider = "Authentication_array", groups = "HomePage")
    public void ArrayAuthentication(String USER_LOGIN, String USER_PASSWORD) {
        StringBuffer verificationErrors = new StringBuffer();
         try {
             homePage.LoginDB(USER_LOGIN,USER_PASSWORD);
             wait.waitForPageLoaded();
             //wait.waitForClickable(By.linkText(SIGNOUT_MENU1));
             wait.waitForWebElementToBeClickAble(SIGNOUT_MENU);
            // System.out.println(SIGNOUT_MENU.getText());
             //WebElement LogOut = webDriver.findElement(By.linkText(SIGNOUT_MENU1));
             if (logoutablePage.isInitialized() && SIGNOUT_MENU.isEnabled()) {
             //if (LogOut.isDisplayed() && LogOut.isEnabled()) {
            logoutablePage.logOut();
             }
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
                new Object[]{"nedved198725", "nedved1987"}};
    }
}
