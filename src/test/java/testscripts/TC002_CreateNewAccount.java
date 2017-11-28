package testscripts;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagelibrary.CreateAnAccount;
import pagelibrary.SignIn;
import testbase.TestBase;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sharif on 10/25/2017.
 */
public class TC002_CreateNewAccount extends TestBase {
    SignIn signIn;
    CreateAnAccount createAnAccount;
    String firstName = "David";
    String lastName = "Cameron";
    String password = "admin123";
    String emailAddress = "email"+System.currentTimeMillis()+"@gmail.com";
    String day = "10";
    String month = "January";
    String year = "2017";
    public static int indexSI = 1;

    @BeforeClass
    public void setUp() throws IOException{
        init();
        signIn = new SignIn(driver);
        createAnAccount =new CreateAnAccount(driver);
       /* test = extent.startTest("Login Test", "This test will verify login test");

        test.log(LogStatus.PASS, "Basic set up for test is done");*/
    }

    @Test(priority = 1,enabled = true,description = "Test Registration with valid data")
    public void registrationWithValidData() throws InterruptedException{
        signIn.signInForRegistration(emailAddress);
        //explicitWait(driver.findElement(createAnAccount.mrRadioButton),30);
        createAnAccount.userNewAccountRegistration(firstName,lastName,password,day,month,year);
        waitFor(2);
        boolean isSuccess = createAnAccount.getRegistrationConfirmedMsg();
        if(isSuccess){
            Assert.assertTrue(true,"Registration test is pass");
        }else{
            Assert.assertTrue(false,"Registration test is fail");
        }
    }
    /*@Test(priority = 0,enabled = true,description = "Test Registration with invalid data")
    public  void registrationWithInvalidData()throws InterruptedException{
        signIn.clickOnLogout();
        signIn.signInForRegistration(emailAddress);
        waitFor(2);

        boolean isSuccess = createAnAccount.verifyCreateAnAccountErrorMsg();
        if (isSuccess){
            Assert.assertTrue(true,"Test Registration invalid test is pass");
        }else{
            Assert.assertTrue(false,"Test Registration invalid data is fail");
        }
    }*/

    @AfterClass
    public void quitBrowser(){
        closeBrowser();
    }
}
