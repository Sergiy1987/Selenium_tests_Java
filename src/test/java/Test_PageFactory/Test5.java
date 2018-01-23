package Test_PageFactory;

import Tools.SwitchToWindow;
import Tools.Tools;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static PageFactory.ProfilePage.USER_PROFILE_LINK;
import static PageFactory.ProfilePage.USER_SIGNOUT_MENU;
import static PageFactory.SearchResultsPage.*;
import static Tools.ExtentManager.createTest;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Test5 extends MainTest  {
    private String ClassName = this.getClass().getSimpleName();
    private ExtentTest test;
    private final String searchTerm = "nedved198725@gmail.com";
    @Test(groups = "SearchResultsPage")
    public void verifySearch() throws StaleElementReferenceException {
        test = createTest("SearchResultsPage", "verifySearch");
        try {
            homePage.logIn();
            System.out.println(homePage.toString());
            wait.waitForWebElementToBeClickAble(USER_PROFILE_LINK);
            if (USER_PROFILE_LINK.isDisplayed() && USER_PROFILE_LINK.isEnabled()) {
                profilePage.goToProfilePage();
                // wait.waitForPageLoaded();
                System.out.println(profilePage.toString());
                SwitchToWindow.Switch_to_new_opened_window(webDriver);
                searchResultsPage.doSearch(searchTerm);
                System.out.println(searchResultsPage.toString());
                assertTrue(SEARCH_INPUT.isEnabled());
                final String SEARCH_INPUT_TEXT = SEARCH_INPUT.getAttribute("value");
                assertEquals(searchTerm,SEARCH_INPUT_TEXT);
                test.pass("SEARCH_INPUT: " + SEARCH_INPUT.getText() + " Status: " + SEARCH_INPUT.isEnabled() + " and is Enabled");
            }
        }catch (StaleElementReferenceException ex){ex.getMessage();}
    }
    @Test(groups = "ProfilePage",dependsOnMethods = "verifySearch")
    public void testSendEmail (Method method) throws NoSuchElementException {
            test = createTest("ProfilePage", "testSendEmail");
        try {
            wait.waitForPageLoaded();
            wait.waitForWebElementToBeClickAble(BUTTON_WRITE_EMAIL);
            searchResultsPage.SendEmail();
            assertTrue(true, "Ваш лист надіслано");
            wait.waitForWebElementToBeClickAble(LINK_TEXT_SEND);
            Tools.takeScreenShot(webDriver, ClassName + "_" + method.getName() + ".jpg");
            wait.waitForWebElementVisibility(USER_SIGNOUT_MENU);
            test.pass("USER_SIGNOUT_MENU: " + USER_SIGNOUT_MENU.getText() + " Status: " + USER_SIGNOUT_MENU.isEnabled() + " and is Enabled");
            profilePage.exit();
        }catch (NoSuchElementException ex){ex.getMessage();}
    }
}
