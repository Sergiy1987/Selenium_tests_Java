package Test_PageFactory;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static PageFactory.HomePage.allElements;
import static PageFactory.HomePage.h2;
import static Tools.ExtentManager.createTest;
import static org.testng.Assert.assertTrue;

public class Test4 extends MainTest {
    private final String BlueColorRGB = "34, 87, 158";
    private ExtentTest test;
    @Test(groups = "HomePage")
    public void verifyTermColor() {
        test = createTest("HomePage", "verifyTermColor");
        //WebElement links_main = webDriver.findElement(By.cssSelector(SEARCH_MAIN_LINKS));
        //List<WebElement> links = links_main.findElements(By.tagName("h2"));
        allElements = h2;
        System.out.println("Found Main Links: " + allElements.size());
        for (WebElement link : allElements){
            final boolean colorIsBlue = link.getCssValue("color").contains(BlueColorRGB);
            System.out.println(link.getText() + "_" + link.getCssValue("color") + "=" + colorIsBlue);
            assertTrue(colorIsBlue, link.getCssValue("color"));
            test.pass(link.getText() + "_" + link.getCssValue("color") + "=" + colorIsBlue);
        }
    }
}
