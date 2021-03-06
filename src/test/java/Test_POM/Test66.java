package Test_POM;

import POM.HomePage;
import POM.LogoutAblePage;
import Tools.Wait;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import static POM.HomePage.HOMEPAGE_URL;
import static POM.LogoutAblePage.SIGNOUT_MENU;
import static Tools.PropConfig.getProperty;

public class Test66 {
    private HomePage homePage;
    private LogoutAblePage logoutablePage;
    private WebDriver webDriver;
    private Wait wait;
    private Logger Log = Logger.getLogger(this.getClass().getName());
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        homePage = new HomePage(webDriver);
        logoutablePage = new LogoutAblePage(webDriver);
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }//http://www.seleniumeasy.com/selenium-tutorials/how-to-do-database-testing-using-selenium-webdriver-framework-example
    //https://github.com/alex00x6/TestTask_selenium_jdbc_maven
    @Test
    public void CreateDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName(getProperty("dbClass")).newInstance();
        Connection con = DriverManager.getConnection(getProperty("url"), getProperty("login"), getProperty("password"));
        Statement stmt = con.createStatement();
        stmt.execute("CREATE  DATABASE IF NOT EXISTS " + getProperty("DATABASE"));
        stmt.execute("CREATE TABLE IF NOT EXISTS " + getProperty("TABLE") +
                "(`id` int(11) NOT NULL AUTO_INCREMENT," +
                "`UserData` varchar(20) NOT NULL," +
                "`Password` varchar(20) NOT NULL," +
                "PRIMARY KEY (`id`)) DEFAULT CHARSET=utf8");
        ResultSet result = stmt.executeQuery("SELECT * FROM " + getProperty("TABLE"));

        while (result.next()) {
            String UserData = result.getString("UserData");
            String User_Password = result.getString("Password");
            homePage.LoginDB(UserData,User_Password);
            wait.waitForPageLoaded();
            wait.waitForClickable(By.linkText(SIGNOUT_MENU));
            logoutablePage.logOut();
            System.out.println("Passed");
            Log.info("DataBase Authentication passed successfully with Login - "
                    + UserData + " and Password - " + User_Password);
        }
        con.close();
        stmt.close();
        result.close();
    }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
}
