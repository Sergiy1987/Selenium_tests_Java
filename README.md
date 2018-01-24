### 1.   Page Factory Pattern

<p>All tests include logging actions with using Allure and Extent reports. All tests extend from MainTest.</p>
<b>Test1: includes methods:</b>
<p><i>verifyLogin(groups = "HomePage")</i>. User input correct Login and Password and click button Login.</p>
<p><i>verifyUserProfileOpened (groups ="ProfilePage",dependsOnMethods = "verifyLogin")</i>.
User opened ProfilePage, User switches to parent opened window.</p>
<p><i>verifyLogout (groups = "LogOutPage", dependsOnMethods = "verifyUserProfileOpened")</i>
User LogOut from LogoutAblePage, screenshot the result when user LogOut.</p>
<p><i>Authentication_array (groups = "HomePage", dataProvider = "Authentication_array")</i>
Data Driven Testing (DDT), correct User and Password are located in the array testData.</p>
<b>Test2: includes methods:</b>
<p><i>verifyNewFavouritesLabelsFromPage(groups = "HomePage")</i>.
Method takes all links by tagname "a" on the left side of the site and check the color of the link.</p>
<p><i>verifyNewFavouritesLabelsFromFile(dataProvider = "ExcelDataProvider",groups = "HomePage")</i>
Method takes all links from Excel file and compare them with links which located on site.</p>
<p><i>VerifyInvalidLoginData(dataProvider = "empLogin", groups = "HomePage")</i>
Methods takes not correct Login and Password from Excel file.</p>
<b>Test3: includes methods:</b>
<p><i>verifyThatFindButtonIsVisible</i>(priority = 0, groups = "HomePage")</i>
Method check button for visibility, with priority 0.</p>
<p><i>verifyThatFindButtonIsInVisible</i>(priority = 1, groups = "HomePage")</i>
Method check button for invisibility, with priority 1.</p>
<b>Test4: includes methods:</b>
<p><i>verifyTermColor(groups = "HomePage")</i>
Method takes all links by tagname "h2" on the main side of the site and check and compare the color of the link.</p>
<b>Test5: includes methods:</b>
<p><i>verifySearch(groups = "SearchResultsPage")</i>
Search String "*" into input field in searchResultsPage, User switches to new opened window, compare Required term with Actual term.</p>
<p><i>testSendEmail(groups = "ProfilePage",dependsOnMethods = "verifySearch")</i>
Test send message (switch to frame window)  and logout from searchResultsPage, depends on method verifySearch, screenshot the result when user send message.</p>

### 2.   Page Object Pattern

<p>All tests include logging actions with using the configuration file (log4j.xml).</p>
<b>Test11: includes methods:</b>
<p><i>verifyLogin</i> (User input correct Login and Password and click button Login).</p>
<p><i>verifyUserProfileOpened</i> (depends on method verifyLogin), User opened ProfilePage, User switches to parent opened window.</p>
<p><i>verifyLogout</i> (depends on method verifyUserProfileOpened ), User LogOut from LogoutAblePage, screenshot the result when user LogOut.</p>
<p><i>Authentication_array</i> (depends on method verifyLogout), Data Driven Testing (DDT), correct User and Password are located in the array  testData.</p>
<b>Test22: includes methods:</b>
<p><i>verifyNewFavouritesLabels</i> (method takes all links by tagname "a" on the left side of the site and check the color of the link).</p>
<p><i>verifyNewLinks</i> (method takes all links from Excel file and compare them with links which located on site).</p>
<p><i>VerifyWithNotCorrectLoginData</i> (methods takes not correct Login and Password from Excel file).</p>
<b>Test33: includes methods:</b>
<p><i>verifyThatFindButtonIsVisible</i> (method check button for visibility, with priority 0).</p>
<p><i>verifyThatFindButtonIsNotVisible</i> (method check button for invisibility, with priority 1).</p>
<b>Test44: includes methods:</b>
<p><i>verifyTermColor</i> (method takes all links by tagname "h2" on the main side of the site and check and compare the color of the link).</p>
<b>Test55: includes methods:</b>
<p><i>verifySearch</i>(search String "*" into input field in searchResultsPage, User switches to new opened window, compare Required term with Actual term).</p>
<p><i>testSendEmail</i>(test send message (switch to frame window)  and logout from searchResultsPage, depends on method verifySearch, screenshot the result when user send message).</p>
<b>Test66: includes methods:</b>
<p><i>CreateDB</i> (check Login/Logout, Logins and Passwords takes from Database (test_data) & Table(test_data) variables DB takes from file DataBase.properties). Connection to the database using Jdbc Driver.</p>
<b>Test77: includes methods:</b>
<p><i>DB_Hibernate</i> (check Login/Logout, Logins and Passwords takes from Database (test_data) & Table(SimpleData)). Connection to the database using Hibernate.</p>
<b>Test88: includes methods:</b>
<p><i>ResultSend_Message_DAO_DB</i> (method puts results into DB test_DAO and send them into email, variables message takes from file SendEmail.properties). Connection to the database using Data Access Object (DAO).</p>

<b>Description and assignment of project files</b>

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
