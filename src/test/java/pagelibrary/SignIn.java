package pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import testbase.TestBase;

import java.util.logging.Logger;

/**
 * Created by Sharif on 10/20/2017.
 */
public class SignIn{
    public WebDriver driver;
    final static Logger log = Logger.getLogger(SignIn.class.getName());

    By firstSignIn = By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
    By allreadyRegEmailAddr  = By.xpath(".//*[@id='email']");
    By password  = By.xpath(".//*[@id='passwd']");
    By submitbutton  = By.xpath(".//*[@id='SubmitLogin']");

    By signInButton = By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
    By newEmailAccount = By.xpath(".//*[@id='email_create']");
    By createSubmitButton = By.xpath(".//*[@id='SubmitCreate']");

    By logOut = By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[2]/a");

    /**
     * Initialized constructor
     */
    public SignIn(WebDriver driver){
        this.driver=driver;
    }
    /**
     * initialized all the elements
     */
    public void clickOnSignIn() {
        log.info("click on sign in button");
        driver.findElement(firstSignIn).click();
    }
    public void enterAllReadyRegEmailAddress(String emailAddress){
        log.info("click on sign in button");
        driver.findElement(allreadyRegEmailAddr).sendKeys(emailAddress);
    }
    public void enterPassword(String pass){
        log.info("click on sign in button");
        driver.findElement(password).sendKeys(pass);
    }
    public void clickSubmitButton(){
        log.info("click on sign in button");
        driver.findElement(submitbutton).click();
    }


    public void clickOnSignInButton(){
        log.info("selecting sign in");
        driver.findElement(signInButton).click();
    }


    public void selectCreateAnAccountEmail(String registerEmail){
        log.info("clicking on create account button");
        driver.findElement(this.newEmailAccount).sendKeys(registerEmail);
    }
    public void clickOnCreateAccount(){
        log.info("click on account");
        driver.findElement(createSubmitButton).click();
    }
    public void loginToApplication(){
        clickOnSignIn();
        enterAllReadyRegEmailAddress("test902@gmail.com");
        enterPassword("password");
        clickSubmitButton();
    }
    public void signInForRegistration(String emailAddress){
        clickOnSignInButton();
        selectCreateAnAccountEmail(emailAddress);
        clickOnCreateAccount();
    }
    public void clickOnLogout(){
        try{
            boolean isDisplay = driver.findElement(logOut).isDisplayed();
            if(isDisplay){
               driver.findElement(logOut).click();
            }
        }catch (Exception e){
            Assert.assertTrue(false,"Sign in button is not displayed");
        }

    }
}
