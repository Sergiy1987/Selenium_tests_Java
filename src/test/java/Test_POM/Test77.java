package Test_POM;

import POM.HomePage;
import POM.LogoutAblePage;
import Tools.Wait;
import Tools.db_hb.SimpleData;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static POM.AbstractPage.HOMEPAGE_URL;
import static POM.LogoutAblePage.SIGNOUT_MENU;

public class Test77 {
    private HomePage homePage;
    private LogoutAblePage logoutablePage;
    private WebDriver webDriver;
    private Wait wait;
    static EntityManagerFactory emf;
    static EntityManager em;
    static int i=1;
    private Logger Log = Logger.getLogger(this.getClass().getName());
    @BeforeClass
    public void setUp() throws Exception{
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.get(HOMEPAGE_URL);
        homePage = new HomePage(webDriver);
        logoutablePage = new LogoutAblePage(webDriver);
        wait = new Wait(webDriver);
        DOMConfigurator.configure("log4j.xml");
    }
    @Test
    public void DB_Hibernate (){
        try {
            emf = Persistence.createEntityManagerFactory("JPATest");
            try{
            em = emf.createEntityManager();
            Query query = em.createQuery("SELECT d FROM SimpleData d", SimpleData.class);
            List<SimpleData> list = (List<SimpleData>) query.getResultList();
            for (SimpleData d : list) {
                //System.out.println(d.toString()+ " Status-->Passed # " + i++);
                //System.out.println(d.getUserData()+"\t"+d.getPassword());
                String UserData = d.getUserData();
                String User_Password = d.getPassword();
                homePage.LoginDB(UserData, User_Password);
                wait.waitForClickable(By.linkText(SIGNOUT_MENU));
                if (webDriver.findElement(By.linkText(SIGNOUT_MENU)).isDisplayed() && logoutablePage.isInitialized()){
                    logoutablePage.logOut();
                    System.out.println(d.toString()+ " Status-->Passed # " + i++);
                }
            Log.info("Hibernate DataBase Authentication passed successfully with Login- "
                        + UserData + " and Password- " + User_Password);
            }
        }finally {
            em.close();
            emf.close();
        }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        }
    @AfterClass
    public void tearDown() {
        webDriver.quit();
        Log.info("Browser closed");
    }
}
