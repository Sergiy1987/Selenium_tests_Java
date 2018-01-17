package Test_POM;

import POM.HomePage;
import POM.LogoutAblePage;
import POM.ProfilePage;
import Tools.Tools;
import Tools.Wait;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.HomePage.LOGIN_BUTTON;
import static POM.LogoutAblePage.SIGNOUT_MENU;
import static POM.ProfilePage.USER_PROFILE_LINK;
import static org.testng.AssertJUnit.assertTrue;

public class Test11 {
    private WebDriver webDriver;
    private HomePage homePage;
    private ProfilePage profilePage;
    private LogoutAblePage logoutablePage;
    private Wait wait;
    String ClassName = this.getClass().getSimpleName();// Test11.class.getSimpleName();
    private Logger Log = Logger.getLogger(this.getClass().getName());

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
        logoutablePage = new LogoutAblePage(webDriver);
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }
    @Test
    public void verifyLogin() {
        if (wait.elementDisplayed(By.xpath(LOGIN_BUTTON)) && wait.elementExists(By.xpath(LOGIN_BUTTON))){
        homePage.logIn();
        System.out.println(homePage.toString());}
        else {System.out.println(HOMEPAGE_URL + "the page is not loaded, please try again later");}
        Log.info("User was logged in successfully");
        wait.waitForClickable(By.xpath(USER_PROFILE_LINK));
        assertTrue(webDriver.findElement(By.xpath(USER_PROFILE_LINK)).isEnabled());
    }
    @Test(dependsOnMethods = "verifyLogin")
    public void verifyUserProfileOpened()  {
        profilePage.goToProfilePage();
        wait.waitForPageLoaded();
        System.out.println(profilePage.toString());
        Log.info("User profile page is open");
        Switch_to_parrent_opened_window();
    }
    @Test(dependsOnMethods = "verifyUserProfileOpened")
    public void verifyLogout(Method method) {
        wait.waitForClickable(By.linkText(SIGNOUT_MENU));
        assertTrue(webDriver.findElement(By.linkText(SIGNOUT_MENU)).isEnabled());
        logoutablePage.logOut();
        System.out.println(logoutablePage.toString());
        Log.info("User was successfully logged out");
        Tools.takeScreenShot(webDriver, ClassName + "_" + method.getName()+ ".jpg");
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
    @Test(dataProvider = "Authentication_array",dependsOnMethods = "verifyLogout")
    public void ArrayAuthentication(String USER_LOGIN, String USER_PASSWORD) {
        StringBuffer verificationErrors = new StringBuffer();
        try {
            homePage.LoginDB(USER_LOGIN,USER_PASSWORD);
            wait.waitForPageLoaded();
            wait.waitForClickable(By.linkText(SIGNOUT_MENU));
         /* WebElement LogOut = webDriver.findElement(By.linkText(SIGNOUT_MENU));
            if (LogOut.isDisplayed() && LogOut.isEnabled() ) {
                LogOut.click();
            }*/
            if (webDriver.findElement(By.linkText(SIGNOUT_MENU)).isDisplayed() && logoutablePage.isInitialized()){
                logoutablePage.logOut();
            }
            Log.info("Array Authentication passed successfully with Login- " + USER_LOGIN +
                    " and Password- " + USER_PASSWORD);
            } catch (Error e) {
            //Capture and append Exceptions/Errors
            verificationErrors.append(e.toString());
        }
    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
    public void Switch_to_parrent_opened_window ()//move to parrent opened window
    {
        String parentHandle = webDriver.getWindowHandle();
        webDriver.switchTo().window(parentHandle);
        System.out.println("New window title: " + webDriver.getTitle());
    }
}