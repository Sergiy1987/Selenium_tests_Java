package POM;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    public static final String HOMEPAGE_URL = "http://ukr.net/";
    protected final WebDriver webDriver;
    protected AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Override
    public String toString() {
        return getClass().getName();
    }
   }