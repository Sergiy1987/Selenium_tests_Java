package Test_PageFactory;

import PageFactory.HomePage;
import PageFactory.ProfilePage;
import PageFactory.SearchResultsPage;
import Tools.Tools;
import Tools.Wait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static PageFactory.AbstractPage.HOMEPAGE_URL;
import static PageFactory.ProfilePage.USER_SIGNOUT_MENU;
import static PageFactory.SearchResultsPage.*;
import static org.testng.Assert.assertTrue;

public class Test5 extends MainTest {
    String ClassName = this.getClass().getSimpleName();
    @BeforeClass
    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(HOMEPAGE_URL);
        Set<String> windows = webDriver.getWindowHandles();
        //String HOMEPAGE_URL_Handle = webDriver.getWindowHandle();
        ((JavascriptExecutor)webDriver).executeScript("window.open();");
        Set<String> MAIL_URL_Window = webDriver.getWindowHandles();
        MAIL_URL_Window.removeAll(windows);
        String MAIL_URL_Handle = ((String)MAIL_URL_Window.toArray()[0]);
        webDriver.switchTo().window(MAIL_URL_Handle);
        webDriver.get(MAIL_URL);
        //webDriver.switchTo().window(HOMEPAGE_URL_Handle);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homePage = new HomePage(webDriver);
        profilePage = new ProfilePage(webDriver);
        searchResultsPage = new SearchResultsPage(webDriver);
        wait = new Wait(webDriver);
    }
    @Test(groups = "SearchResultsPage")
    public void verifySearch() {
        final String searchTerm = "nedved198725@gmail.com";
        homePage.Mail_logIn();
        System.out.println(homePage.toString());
        searchResultsPage.doSearch(searchTerm);
        System.out.println(searchResultsPage.toString());
        assertTrue(SEARCH_INPUT.isEnabled());
    }
    @Test(groups = "ProfilePage", dependsOnMethods = "verifySearch")
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
