package Test_PageFactory;

import Tools.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static PageFactory.HomePage.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Test2 extends MainTest {
    private String menuCategoriesFromSite;
    private final String RGB_OF_BLACK_COLOR = "0, 0, 0";
    @Test(groups = "HomePage")
    public void verifyNewFavouritesLabelsFromPage() {
        List<WebElement> links = NEW_FAVOURITES_LABELS_LEFT.findElements(By.tagName("a"));
        System.out.println("Found Favourites Links: " + links.size());

        for (WebElement link : links){System.out.println(link.getText()); }

        final String color = COLOR_FAVOURITES_LABELS.getCssValue("color");
        System.out.println("Color of Favourites Links: " + color.toString());
        final boolean colorIsBlack = color.contains(RGB_OF_BLACK_COLOR);
        assertFalse(colorIsBlack, "Color of new category links isn't black.");
    }
    @Test(dataProvider = "ExcelDataProvider",groups = "HomePage")
    public void verifyNewFavouritesLabelsFromFile(String categoryFromFile) {
        menuCategoriesFromSite = getAllMenuCategoriesFromSite();
        assertTrue(menuCategoriesFromSite.contains(categoryFromFile), "Menu doesn't contain such category: " + categoryFromFile);
        System.out.println("Menu contain such category: " + categoryFromFile);
    }
    @Test(dataProvider = "empLogin", groups = "HomePage")
    public void VerifyInvalidLoginData(String userName, String password) {
        homePage.LoginDB(userName,password);
        wait.waitForWebElementToBeClickAble(ERROR_DATA);
        String actualErrorDisplayed = ERROR_DATA.getText();
        String requiredErrorMessage = "Неправильно вказано логін чи пароль. Спробуйте знову.";
        assertEquals(requiredErrorMessage, actualErrorDisplayed);
    }
    @org.testng.annotations.DataProvider(name = "ExcelDataProvider")
    public Iterator<Object[]> dataProvider() {
        return DataProvider.getDataFromXLSFile("/TestData.xls", "MenuCategories");
    }
    @org.testng.annotations.DataProvider(name="empLogin")
    public Object[][] loginData() throws IOException {
        Object[][] arrayObject = DataProvider.getExcelData("/User_Password_uncorrect.xls","User_Password");
        return arrayObject;
    }
   public String getAllMenuCategoriesFromSite() {
        final StringBuilder menuCategoriesFromSite = new StringBuilder();
        final List<WebElement> spans = NEW_FAVOURITES_LABELS_RIGHT;
        for (WebElement span : spans) {
            menuCategoriesFromSite.append(span.getText());
        }
        return menuCategoriesFromSite.toString();
   }
   }
