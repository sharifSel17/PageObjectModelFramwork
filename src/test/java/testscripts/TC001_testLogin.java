package testscripts;


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
    public SignIn signIn;

    @BeforeClass
    public void setUp() throws IOException{
      init();
    }


    @Test
    public void testLogin(){
        signIn = new SignIn(driver);
        signIn.clickOnSignIn();
        signIn.enterAllReadyRegEmailAddress("123@gmail.com");
        signIn.enterPassword("password");
        signIn.clickSubmitButton();

    }

    @AfterClass
    public void quitBrowser(){
        closeBrowser();
    }
}
