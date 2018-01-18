package Tools;

import org.openqa.selenium.WebDriver;

public class SwitchToWindow {
    private static WebDriver webDriver;

    public static void Switch_to_new_opened_window(WebDriver webDriver)//move to new opened window
    {
        String parentHandle = webDriver.getWindowHandle();
        //webDriver.switchTo().window(parentHandle);
        for(String childHandle : webDriver.getWindowHandles()){
            if (!childHandle.equals(parentHandle)){
                webDriver.switchTo().window(childHandle);
                System.out.println("New window title: " + webDriver.getTitle());
            }
        }
    }
    public static void Switch_to_parrent_opened_window (WebDriver webDriver) {
        String parentHandle = webDriver.getWindowHandle();
        webDriver.switchTo().window(parentHandle);
        System.out.println("Parrent window title: " + webDriver.getTitle());
    }
}
