### 1.   Page Factory Pattern

All tests include logging actions with using Allure and Extent reports.

All tests extend from MainTest.

Test1: includes methods:
verifyLogin(groups = "HomePage"). 
verifyUserProfileOpened (groups ="ProfilePage",dependsOnMethods = "verifyLogin"), User opened ProfilePage, User switches to parent opened window.
verifyLogout (groups = "LogOutPage", dependsOnMethods = "verifyUserProfileOpened"), User LogOut from LogoutAblePage, screenshot the result when user LogOut.
Authentication_array (groups = "HomePage", dataProvider = "Authentication_array"), Data Driven Testing (DDT), correct User and Password are located in the array testData.
Test2: includes methods:
verifyNewFavouritesLabelsFromPage(groups = "HomePage"). Method takes all links by tagname "a" on the left side of the site and check the color of the link.
verifyNewFavouritesLabelsFromFile(dataProvider = "ExcelDataProvider",groups = "HomePage"). Method takes all links from Excel file and compare them with links which located on site.
VerifyInvalidLoginData(dataProvider = "empLogin", groups = "HomePage"). Methods takes not correct Login and Password from Excel file.
Test3: includes methods:
verifyThatFindButtonIsVisible(priority = 0, groups = "HomePage"). Method check button for visibility, with priority 0.
verifyThatFindButtonIsInVisible(priority = 1, groups = "HomePage"). Method check button for invisibility, with priority 1.
Test4: includes methods:
verifyTermColor(groups = "HomePage"). Method takes all links by tagname "h2" on the main side of the site and check and compare the color of the link.
Test5: includes methods:
verifySearch(groups = "SearchResultsPage"). Search String "*" into input field in searchResultsPage, User switches to new opened window, compare Required term with Actual term.
testSendEmail(groups = "ProfilePage",dependsOnMethods = "verifySearch"). Test send message (switch to frame window)  and logout from searchResultsPage, depends on method verifySearch, screenshot the result when user send message).

### 2.   Page Object Pattern


All tests include logging actions with using the configuration file (log4j.xml).
Test11: includes methods:
verifyLogin (User input correct Login and Password).
verifyUserProfileOpened (depends on method verifyLogin), User opened ProfilePage, User switches to parent opened window.
verifyLogout (depends on method verifyUserProfileOpened ), User LogOut from LogoutAblePage, screenshot the result when user LogOut.
Authentication_array (depends on method verifyLogout), Data Driven Testing (DDT), correct User and Password are located in the array  testData.
Test22: includes methods:
verifyNewFavouritesLabels (method takes all links by tagname "a" on the left side of the site and check the color of the link).
verifyNewLinks (method takes all links from Excel file and compare them with links which located on site).
VerifyWithNotCorrectLoginData (methods takes not correct Login and Password from Excel file).
Test33: includes methods:
verifyThatFindButtonIsVisible (method check button for visibility, with priority 0).
verifyThatFindButtonIsNotVisible (method check button for invisibility, with priority 1).
Test44: includes methods:
verifyTermColor (method takes all links by tagname "h2" on the main side of the site and check and compare the color of the link).
Test55: includes methods:
verifySearch(search String "*" into input field in searchResultsPage, User switches to new opened window, compare Required term with Actual term).
testSendEmail(test send message (switch to frame window)  and logout from searchResultsPage, depends on method verifySearch, screenshot the result when user send message).
Test66: includes methods:
CreateDB (check Login/Logout, Logins and Passwords takes from Database (test_data) & Table(test_data) variables DB takes from file DataBase.properties). Connection to the database using Jdbc Driver.
Test77: includes methods:
DB_Hibernate (check Login/Logout, Logins and Passwords takes from Database (test_data) & Table(SimpleData)). Connection to the database using Hibernate.
Test88: includes methods:
ResultSend_Message_DAO_DB (method puts results into DB test_DAO and send them into email, variables message takes from file SendEmail.properties). Connection to the database using Data Access Object (DAO).

Description and assignment of project files

DataProvider.java - java class with methods (getDataFromXLSFile, getExcelData).
ExtentManager.java - java class with methods for the Extent Repotting.
PropConfig.java - java class with methods for the configuring parameters: database and send a message.
SendMailSSL.java - java class with method for send a message.
SwitchToWindow.java - java class with methods for the switching between browser windows.
Tools.java - java class with method for the screenshot of the test results.
Wait.java - java class with methods for wait and presence WebElement.
persistence.xml - configuration file for set up Persistent Unit and database.
creation.sql - file with sql queries to the database.
DataBase.properties - file with  properties to set up database connection.
SendEmail.properties - file with  properties to set up send a message.
TestData.xls - file with test data for verify new links (Test22 and Test2, method VerifyNewLinks).
User_Password.xls - file with correct test data for logging on the site (Test11 and Test1, method Authentication_array).
User_Password_uncorrect.xls - file with correct test data for logging on the site (Test22 and Test2, method VerifyInvalidLoginData).
GroupsPageFactory.xml - suite with tests and groups for the Page Factory Pattern.
TestsPageObjectPattern.xml - suite with tests for the Page Object Pattern.
PageObjectAndPageFactoryTests.xml - suite consolidate all tests from GroupsPageFactory.xml and TestsPageObjectPattern.xml files.
Parallel-Testing_PageObject.xml - suite with parallel tests for the Page Object Pattern.
log4j.xml - file with parameters for the logging actions.
logfile.log - file with the logging actions.
