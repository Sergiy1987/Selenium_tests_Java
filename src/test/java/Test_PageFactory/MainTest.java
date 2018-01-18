package Test_PageFactory;
import PageFactory.HomePage;
import PageFactory.LogoutAblePage;
import PageFactory.ProfilePage;
import PageFactory.SearchResultsPage;
import Tools.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import static PageFactory.AbstractPage.HOMEPAGE_URL;

public abstract class MainTest {
    public WebDriver webDriver;
    public HomePage homePage;
    public ProfilePage profilePage;
    public LogoutAblePage logoutablePage;
    public SearchResultsPage searchResultsPage;
    public Wait wait;

    @BeforeClass(alwaysRun = true,description = "Class Level setUp!")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(HOMEPAGE_URL);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homePage = new HomePage(webDriver);
        profilePage = new ProfilePage(webDriver);
        logoutablePage = new LogoutAblePage(webDriver);
        searchResultsPage = new SearchResultsPage(webDriver);
        wait = new Wait(webDriver);
    }
    @AfterClass(alwaysRun = true,description = "Class Level tearDown!")
    public void tearDown() {webDriver.quit();}
    }
/*
http://darrellgrainger.blogspot.com/2013/11/opening-two-windows-for-one-webdriver.html
WebDriver driver = new ChromeDriver();
driver.get(adminToolURL);
Set<String> windows = driver.getWindowHandles();
String adminToolHandle = driver.getWindowHandle();
((JavascriptExecutor)driver).executeScript("window.open();");
Set<String> customerWindow = driver.getWindowHandles();
customerWindow.removeAll(windows);
String customerSiteHandle = ((String)customerWindow.toArray()[0]);
driver.switchTo().window(customerSiteHandle);
driver.get(customerSiteURL);
driver.switchTo().window(adminToolHandle);
 */

