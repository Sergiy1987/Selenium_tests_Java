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

    @BeforeClass(alwaysRun = true)
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
    @AfterClass(alwaysRun = true)
    public void tearDown() {webDriver.quit();}
    }

