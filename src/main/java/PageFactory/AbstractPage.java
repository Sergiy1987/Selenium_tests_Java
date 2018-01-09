package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

    public abstract class AbstractPage {
    public static final String HOMEPAGE_URL = "http://ukr.net/";
    public static final String MAIL_URL = "https://mail.ukr.net";
    protected WebDriver webDriver;

    protected AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
 }
