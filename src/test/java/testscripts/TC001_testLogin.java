package testscripts;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagelibrary.SignIn;
import testbase.TestBase;

import java.io.IOException;

/**
 * Created by Sharif on 10/20/2017.
 */
public class TC001_testLogin extends TestBase {
    public static int indexSI = 1;
    //public static ExtentReports extent;
    //public static ExtentTest test;
    SignIn signIn;

    @BeforeClass
    public void setUp() throws IOException{
      init();
      signIn = new SignIn(driver);
        test = extent.startTest("Login Test", "This test will verify login test");

        test.log(LogStatus.PASS, "Basic set up for test is done");
    }


    @Test
    public void testLogin()throws Exception{
        try{
            signIn = new SignIn(driver);
            signIn.loginToApplication();
            //we need to pass a parameter when call to extend report using following line
            //ITestResult result
            //getresult(result);
            updateResult(indexSI++,"Test Report 1","Pass","Login Form");
            Thread.sleep(4000);

        }catch (Exception e){
            updateResult(indexSI++, "testReport1", "Fail", "Test1");
        }
        extent.endTest(test);
        extent.flush();
    }

    @AfterClass
    public void quitBrowser(){
        closeBrowser();
    }
}
