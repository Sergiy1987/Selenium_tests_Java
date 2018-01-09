package Test_PageFactory;

import PageFactory.*;
import Tools.Wait;
import org.apache.log4j.xml.DOMConfigurator;
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
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files/Java/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        homePage = new HomePage(webDriver);
        profilePage = new ProfilePage(webDriver);
        logoutablePage = new LogoutAblePage(webDriver);
        searchResultsPage = new SearchResultsPage(webDriver);
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }
    @AfterClass
    public void tearDown() {webDriver.quit();}
    }

