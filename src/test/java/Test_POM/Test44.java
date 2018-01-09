package Test_POM;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.HomePage.SEARCH_MAIN_LINKS;
import static org.testng.Assert.assertTrue;
public class Test44 {
    private WebDriver webDriver;
    private Logger Log = Logger.getLogger(this.getClass().getName());
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files/Java/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        DOMConfigurator.configure("log4j.xml");
    }
    @Test
    public void verifyTermColor() {
        final String BlueColorRGB = "34, 87, 158";
        WebElement links_main = webDriver.findElement(By.cssSelector(SEARCH_MAIN_LINKS));
        List<WebElement> links = links_main.findElements(By.tagName("h2"));
        System.out.println("Found Main Links: " + links.size());
        Log.info("Found Main Links: " + links.size());

        for (WebElement link : links){
            final boolean colorIsBlue = link.getCssValue("color").contains(BlueColorRGB);
            System.out.println(link.getText() + "_" + link.getCssValue("color") + "=" + colorIsBlue);
            assertTrue(colorIsBlue, link.getCssValue("color"));
        }
        Log.info("Colors of Main Links are " + BlueColorRGB);
    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
}

