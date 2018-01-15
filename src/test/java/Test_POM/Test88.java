package Test_POM;

import Tools.SendMailSSL;
import Tools.Wait;
import Tools.db.Item;
import Tools.db.ItemDao;
import Tools.db.ItemDaoImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.HomePage.SEARCH_MAIN_LINKS;

public class Test88 {
    private WebDriver webDriver;
    private Wait wait;
    private Map<String, String> results = new HashMap<>();
    private Logger Log = Logger.getLogger(this.getClass().getName());
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }
    @Test
    public void test_DAO_DB (){
        truncateTable();
        parsePage();
        printMap();
        putResultsInTable();
        SendMailSSL.send(System.getenv("UserData"),
                System.getenv("UserPassword"),
                "nedved198725@gmail.com",
                "UkrNet_Test",
                getResultsFromTable());
        Log.info("Email email was sent successfully with data from table " + Item.TABLE_NAME);
    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
    private void parsePage() {
        wait.waitForVisibility(By.cssSelector(SEARCH_MAIN_LINKS));
        WebElement links_main = webDriver.findElement(By.cssSelector(SEARCH_MAIN_LINKS));
        List<WebElement> links = links_main.findElements(By.tagName("h2"));
        for (WebElement link : links) {
            try {
                String name_link = link.getText();
                String color = link.getCssValue("color");
                results.put(name_link, color);
            } catch (StaleElementReferenceException e) {
                String name_link = link.getText();
                String color = link.getCssValue("color");
                results.put(name_link, color);
            }
        }
    }
    private void printMap(){
        for (Map.Entry<String, String> entry : results.entrySet())
        {
            System.out.println(entry.getKey() + " = " + entry.getValue());//+ entry.toString()
        }
    }
    private void putResultsInTable(){
        ItemDao itemDao = new ItemDaoImpl();
        for (Map.Entry<String, String> entry : results.entrySet())
        {
            Item item = new Item();
            item.setNameColumn(entry.getKey());
            item.setColumn(entry.getValue());
            itemDao.insert(item);
        }
    }
    private String getResultsFromTable(){
        ItemDao itemDao = new ItemDaoImpl();
        List<Item> result = itemDao.findAll();
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i< result.size(); i++){
            resultString.append(" \n ").append(result.get(i).getNameColumn()).append(" = ").append(result.get(i).getColor());
        }
        return resultString.toString();
    }
    private void truncateTable () {
        ItemDao itemDao = new ItemDaoImpl();
        Item item = new Item();
        itemDao.truncate(item);
    }
}

