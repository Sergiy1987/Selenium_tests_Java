package Test_PageFactory;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static PageFactory.HomePage.allElements;
import static PageFactory.HomePage.h2;
import static org.testng.Assert.assertTrue;

public class Test4 extends MainTest {
    @Test(groups = "HomePage")
    public void verifyTermColor() {
        final String BlueColorRGB = "34, 87, 158";
        //WebElement links_main = webDriver.findElement(By.cssSelector(SEARCH_MAIN_LINKS));
        //List<WebElement> links = links_main.findElements(By.tagName("h2"));
        allElements = h2;
        System.out.println("Found Main Links: " + allElements.size());
        for (WebElement link : allElements){
            final boolean colorIsBlue = link.getCssValue("color").contains(BlueColorRGB);
            System.out.println(link.getText() + "_" + link.getCssValue("color") + "=" + colorIsBlue);
            assertTrue(colorIsBlue, link.getCssValue("color"));
        }
    }
}
