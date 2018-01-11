package Test_POM;
import POM.HomePage;
import Tools.DataProvider;
import Tools.Wait;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.HomePage.*;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
//http://autoqa.org/tips-and-tricks/poisk-vsex-ssylok-na-stranice.html
//http://qaat.ru/kak-najti-bitye-ssylki-na-stranice-s-pomoshhyu-selenium-webdriver/
//https://seleniumhq.github.io/selenium/docs/api/javascript/module/selenium-webdriver/index_exports_WebElement.html
public class Test22 {
    private WebDriver webDriver;
    private String menuCategoriesFromSite;
    private final String RGB_OF_BLACK_COLOR = "0, 0, 0";
    private HomePage homePage;
    private Wait wait;
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
        menuCategoriesFromSite = getAllMenuCategoriesFromSite();
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }
    @Test
    public void verifyNewFavouritesLabels() {
        WebElement links_left = webDriver.findElement(By.cssSelector(NEW_FAVOURITES_LABELS_LEFT));
        List<WebElement> links = links_left.findElements(By.tagName("a"));
        System.out.println("Found Favourites Links: " + links.size());
        Log.info("Found Favourites Links: " + links.size());
      /* for (int i = 0; i<links.size(); i++){
            System.out.println(links.get(i).getText());
        }*/
        for (WebElement link : links){System.out.println(link.getText()); }

        final String color = webDriver.findElement(By.cssSelector(COLOR_FAVOURITES_LABELS)).getCssValue("color");
        System.out.println("Color of Favourites Links: " + color.toString());
        final boolean colorIsBlack = color.contains(RGB_OF_BLACK_COLOR);

        assertFalse(colorIsBlack, "Color of new category links isn't black.");
        Log.info("Color of new category links is " + color);
    }
    @Test(dataProvider = "ExcelDataProvider")
    public void verifyNewLinks(String categoryFromFile) {
        Assert.assertTrue(menuCategoriesFromSite.contains(categoryFromFile), "Menu doesn't contain such category: " + categoryFromFile);
        Log.info("Menu contain such category: " + categoryFromFile);
        System.out.println("Menu contain such category: " + categoryFromFile);
    }
    @org.testng.annotations.DataProvider(name = "ExcelDataProvider")
    public Iterator<Object[]> dataProvider() {
        return DataProvider.getDataFromXLSFile("/TestData.xls", "MenuCategories");
    }
    @Test(dataProvider = "empLogin")
    public void VerifyInvalidLoginData(String userName, String password) {
       homePage.LoginDB(userName,password);
       wait.waitForClickable(By.className(ERROR_DATA));

        String actualErrorDisplayed = webDriver.findElement(By.className(ERROR_DATA)).getText();
        String requiredErrorMessage = "Неправильно вказано логін чи пароль. Спробуйте знову.";
        assertEquals(requiredErrorMessage, actualErrorDisplayed);
        Log.info("File Authentication passed successfully with not correct Login- "
                + userName + " and Password not correct- " + password);
    }

    @org.testng.annotations.DataProvider(name="empLogin")
    public Object[][] loginData() throws IOException {
        Object[][] arrayObject = DataProvider.getExcelData("/User_Password_uncorrect.xls","User_Password");
        return arrayObject;
    }
  /*  @Test(dataProvider = "ExcelData", dependsOnMethods = "verifyNewLinks")
    public void FileAuthentication(String user, String password) {
        webDriver.findElement(By.name(LOGIN_FIELD)).sendKeys(user);
        webDriver.findElement(By.name(PASSWORD_FIELD)).sendKeys(password);
        webDriver.findElement(By.xpath(LOGIN_BUTTON)).click();
        wait.waitForElement(By.linkText(SIGNOUT_MENU));
        webDriver.findElement(By.linkText(SIGNOUT_MENU)).click();
    }
    @org.testng.annotations.DataProvider(name = "ExcelData")
    public Iterator<Object[]> dataProvider_file() {
        return DataProvider.getDataFromXLSFile("/User_Password.xls","User_Password");
    }*/

    public String getAllMenuCategoriesFromSite() {
        final StringBuilder menuCategoriesFromSite = new StringBuilder();
        final List<WebElement> spans = webDriver.findElements(By.cssSelector(NEW_FAVOURITES_LABELS_RIGHT));
        for (WebElement span : spans) {
            menuCategoriesFromSite.append(span.getText());
        }
        return menuCategoriesFromSite.toString();
    }   @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
}