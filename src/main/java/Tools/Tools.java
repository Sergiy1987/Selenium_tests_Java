package Tools;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Tools {
    @Attachment(value = "Page screenshot", type = "image/png")
    //public static takeScreenShot(WebDriver webDriver, String fileName) {
    public static byte[] takeScreenShot(WebDriver webDriver, String fileName) {
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("src\\test\\java\\output\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);//del
    }
    @Attachment(value = "Screenshot Result", type = "text/plain")
    public static String saveTextLog (String message) {
        return message;
    }
}
