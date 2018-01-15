package Test_POM;

import POM.HomePage;
import Tools.Wait;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.HomePage.SEARCH_BUTTON;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
public class Test33 {
    private WebDriver webDriver;
    private Wait wait;
    private HomePage homePage;
    private Logger Log = Logger.getLogger(this.getClass().getName());
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        DOMConfigurator.configure("log4j.xml");
        homePage = new HomePage(webDriver);
        wait = new Wait(webDriver);
    }
    @Test(priority = 0)
    public void verifyThatFindButtonIsVisible() {
        assertTrue(webDriver.findElement(By.cssSelector(SEARCH_BUTTON)).isDisplayed());
        Log.info("'Find' button present!");
    }
    @Test(priority = 1)
    public void verifyThatFindButtonIsNotVisible() {
        wait.waitForPageLoaded();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style = 'display: none';", webDriver.findElement(By.cssSelector(SEARCH_BUTTON)));
        assertFalse(webDriver.findElement(By.cssSelector(SEARCH_BUTTON)).isDisplayed());
        Log.info("'Find' button doesn't present!");
    }
  /*  @Test(priority = 2)
    public void FindBrokenLinksOnAPage (){
        Map<Boolean, List<String>> map= webDriver.findElements(By.tagName("a"))  // find all elements which has href attribute
                .stream()
                .map(ele -> ele.getAttribute("href"))   // get the value of href
                .map(String::trim)                      // trim the text
                .distinct()                             // there could be duplicate links , so find unique
                .collect(Collectors.partitioningBy(link -> LinkUtil.getResponseCode(link) == 200)); // partition based on response code
                map.get(false)
                .stream()
                .forEach(System.out::println);
    }*/
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
}
