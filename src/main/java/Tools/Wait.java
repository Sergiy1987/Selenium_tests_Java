package Tools;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Wait {
    private WebDriver webDriver;
    private final int timeToWait = 35;
    private WebDriverWait wait;

   public Wait(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(this.webDriver, timeToWait);
        }
    public void waitForClickable(By locator) {wait.until(ExpectedConditions.elementToBeClickable(locator));
       // WebElement dynamicElement = (new WebDriverWait(webDriver, 10));
        }
    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForPresence(By locator) {wait.until(ExpectedConditions.presenceOfElementLocated (locator)); }
    public boolean elementExists(By locator) {
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        boolean exists =  !webDriver.findElements(locator).isEmpty();
        webDriver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
        return exists;
    }
    public boolean elementDisplayed(By locator) {
        return webDriver.findElement(locator).isDisplayed();
    }
    //------------------------------------------------------------------------------------------------------------------
    public void waitForWebElementToBeClickAble(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));}
    public void waitForWebElementVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));}
    public boolean elementDisplayedByWebElement(WebElement webElement) {return webElement.isDisplayed();}
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver webDriver) {
                        return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
