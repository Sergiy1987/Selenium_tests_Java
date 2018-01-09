package Tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Tools {
    public static void takeScreenShot(WebDriver webDriver, String fileName) {
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("src\\test\\java\\output\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
