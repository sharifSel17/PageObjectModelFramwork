package testscripts;

import excelreader.Excel_Reader;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import pagelibrary.SignIn;
import testbase.TestBase;

import java.io.IOException;
import java.sql.Time;
import java.util.logging.Logger;

/**
 * Created by Sharif on 11/23/2017.
 */
public class TC001_TestLoginThroughDataProvider extends TestBase {
    public static int indexSI = 1;
    SignIn signIn;
    static Logger log = Logger.getLogger(TC001_TestLoginThroughDataProvider.class.getName());

    @BeforeMethod
    public void setUP() throws IOException {
        init();
        signIn = new SignIn(driver);
    }

    public Object[][] getData(String ExcelName, String testcase) {
        Excel_Reader Data = new Excel_Reader(System.getProperty("user.dir") + "\\src\\test\\resources\\"+ExcelName);
        int rowNum = Data.getRowCount(testcase);
        System.out.println(rowNum);
        int colNum = Data.getColumnCount(testcase);
        Object sampleData[][] = new Object[rowNum - 1][colNum];
        for (int i = 2; i <=rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
            }
        }
        return sampleData;
    }

    @DataProvider
    public Object[][] loginData(){
        Object[][] data = getData("Login.xlsx", "Login");
        return data;
    }
    @Test(dataProvider = "loginData")
    public void TestLoginWithDataProvider(String TestCaseName, String Email, String Password, String runMode) throws IOException{
        log.info("I am from data provider guest");
        if(runMode.equals("N")){
            //updateResult(indexSI,"Read Data From Excel","Skip","TestLoginWithDataProvider");
            throw new SkipException("Skipping the test");

        }else if(runMode.equals("L")){
            //updateResult(indexSI,"Read Data From Excel","Failed","TestLoginWithDataProvider");
            Assert.assertTrue(false, "Test is failed");
        }
        //we need to pass a parameter when call to extend report using following line
        //ITestResult result
        //getresult(result);
        signIn = new SignIn(driver);
        signIn.loginFromExcelDataApplication(Email, Password);
        signIn.clickOnLogout();
        //getresult(result);
        //updateResult(indexSI,"Read Data From Excel","Pass","TestLoginWithDataProvider");


    }
    @AfterMethod
    public void quitBrowser(){
        closeBrowser();
    }
}
