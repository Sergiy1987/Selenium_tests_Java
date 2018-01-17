package Test_PageFactory;

import Tools.Tools;
import Tools.SwitchToWindow;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static PageFactory.ProfilePage.USER_PROFILE_LINK;
import static PageFactory.ProfilePage.USER_SIGNOUT_MENU;
import static PageFactory.SearchResultsPage.*;
import static org.testng.Assert.assertTrue;

public class Test5 extends MainTest  {
    String ClassName = this.getClass().getSimpleName();

    @Test(groups = "SearchResultsPage")
    public void verifySearch() {
        final String searchTerm = "nedved198725@gmail.com";
        homePage.logIn();
        System.out.println(homePage.toString());
        wait.waitForWebElementToBeClickAble(USER_PROFILE_LINK);
        profilePage.goToProfilePage();
        SwitchToWindow.Switch_to_new_opened_window(webDriver);
        wait.waitForPageLoaded();
        searchResultsPage.doSearch(searchTerm);
        System.out.println(searchResultsPage.toString());
        assertTrue(SEARCH_INPUT.isEnabled());
    }
    @Test(groups = "ProfilePage",dependsOnMethods = "verifySearch")
    public void testSendEmail (Method method){
        wait.waitForWebElementToBeClickAble(BUTTON_WRITE_EMAIL);
        searchResultsPage.SendEmail();
        assertTrue(true,"Ваш лист надіслано");
        wait.waitForWebElementToBeClickAble(LINK_TEXT_SEND);
        Tools.takeScreenShot(webDriver, ClassName + "_" + method.getName()+ ".jpg");
        wait.waitForWebElementVisibility(USER_SIGNOUT_MENU);
        profilePage.exit();
    }
}
