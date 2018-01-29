package Test_PageFactory;

import Tools.DataProvider;
import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static PageFactory.HomePage.*;
import static Tools.ExtentManager.createTest;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
@Listeners({Listener.class})
@Epic("HomePage")
@Feature("Login Tests with incorrect test data/Verify NewFavouritesLabelsFromPage&File")
public class Test2 extends MainTest {
    private String menuCategoriesFromSite;
    private final String RGB_OF_BLACK_COLOR = "0, 0, 0";
    private ExtentTest test;
    private String requiredErrorMessage = "Неправильно вказано логін чи пароль. Спробуйте знову.";

    @Test(groups = "HomePage", description = "Scenario: Verify New Favourites Labels From HomePage")
    @Description("Test Description: Verify New Favourites Labels From HomePage.")
    @Story("Check New Favourites Labels From HomePage and Labels color")
    public void verifyNewFavouritesLabelsFromPage() {
        test = createTest("HomePage", "verifyNewFavouritesLabelsFromPage");
        System.out.println("Found Favourites Links: " + NEW_FAVOURITES_LABELS_LEFT_LIST.size());
        for (WebElement link : NEW_FAVOURITES_LABELS_LEFT_LIST){
        System.out.println(link.getText());
        test.pass("Found Favourites Link: " + link.getText());}
        final String color = COLOR_FAVOURITES_LABELS.getCssValue("color");
        System.out.println("Color of Favourites Links: " + color);
        final boolean colorIsBlack = color.contains(RGB_OF_BLACK_COLOR);
        assertFalse(colorIsBlack, "Color of new category links isn't black.");
        test.pass(color + " Color of new category links isn't black("+ RGB_OF_BLACK_COLOR +")");
    }
    @Test(dataProvider = "ExcelDataProvider",groups = "HomePage", description = "Scenario: Verify New Favourites Labels From Excel File")
    @Description("Test Description: Verify New Favourites Labels From Excel File.")
    @Story("Check New Favourites Labels at the HomePage From Excel File")
    @Step("Verify New Favourites Labels From Excel File")
    public void verifyNewFavouritesLabelsFromFile(String categoryFromFile) {
        test = createTest("HomePage", "VerifyNewFavouritesLabelsFromFile");
        menuCategoriesFromSite = getAllMenuCategoriesFromSite();
        assertTrue(menuCategoriesFromSite.contains(categoryFromFile), "Menu doesn't contain such category: " + categoryFromFile);
        System.out.println("Menu contain such category: " + categoryFromFile);
        test.pass("Menu doesn't contain such category: " + categoryFromFile);
    }
    @Test(dataProvider = "empLogin", groups = "HomePage", description = "Invalid Login Scenario with wrong username and password.")
    @Description("Test Description: Login test with not correct username and correct password.")
    @Story("Invalid username and password login test")
    public void VerifyInvalidLoginData(String userName, String password) {
        test = createTest("HomePage" ,"VerifyInvalidLoginData");
        homePage.LoginDB(userName,password);
        wait.waitForWebElementToBeClickAble(ERROR_DATA);
        String actualErrorDisplayed = ERROR_DATA.getText();
        assertEquals(requiredErrorMessage, actualErrorDisplayed);
        test.pass("USER_LOGIN: " + userName + ", USER_PASSWORD: " + password);
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
