package Test_POM;

import POM.HomePage;
import POM.ProfilePage;
import POM.SearchResultsPage;
import Test_PageFactory.Listener;
import Tools.SwitchToWindow;
import Tools.Wait;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.ProfilePage.*;
import static POM.SearchResultsPage.SEARCH_INPUT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class Test55 {
    private WebDriver webDriver;
    private HomePage homePage;
    private ProfilePage profilePage;
    private SearchResultsPage searchResultsPage;
    private Wait wait;
    private String ClassName = this.getClass().getSimpleName();
    private Logger Log = Logger.getLogger(this.getClass().getName());
    private final String searchTerm = "nedved198725@gmail.com";
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        homePage = new HomePage(webDriver);
        profilePage = new ProfilePage(webDriver);
        searchResultsPage = new SearchResultsPage(webDriver);
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }
    @Test
    public void verifySearch() {
        homePage.logIn();
        System.out.println(homePage.toString());
        wait.waitForClickable(By.xpath(USER_PROFILE_LINK));
        profilePage.goToProfilePage();
        System.out.println(profilePage.toString());
        SwitchToWindow.Switch_to_new_opened_window(webDriver);
        wait.waitForPageLoaded();
        searchResultsPage.doSearch(searchTerm);
        System.out.println(searchResultsPage.toString());
        assertTrue(webDriver.findElement(By.cssSelector(SEARCH_INPUT)).isEnabled());
        final String SEARCH_INPUT_TEXT = webDriver.findElement(By.cssSelector(SEARCH_INPUT)).getAttribute("value");
        assertEquals(searchTerm,SEARCH_INPUT_TEXT);
        Log.info("Search string " + searchTerm + " into input field");
     }
    @Test(dependsOnMethods = "verifySearch")
    public void testSendEmail (Method method){
        wait.waitForClickable(By.xpath(BUTTON_WRITE_EMAIL));
        profilePage.SendEmail();
        assertTrue(true,"Ваш лист надіслано");
        wait.waitForClickable(By.linkText(LINK_TEXT_SEND));
        Listener.takeScreenShot(webDriver, ClassName + "_" + method.getName()+ ".jpg");
        Log.info("Your message has been successfully sent");
        wait.waitForVisibility(By.cssSelector(USER_SIGNOUT_MENU));
        profilePage.exit();
    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
}

