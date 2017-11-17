package pagelibrary;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Sharif on 10/25/2017.
 */
public class CreateAnAccount {
    WebDriver driver;
    final static Logger logger = Logger.getLogger(CreateAnAccount.class.getName());


    By createAccountErrorMsg = By.xpath(".//*[@id='create_account_error']/ol/li");
    public By mrRadioButton = By.xpath(".//*[@id='id_gender1']");
    By mrsRadioButton = By.xpath("./*//*[@id='id_gender2']");
    By firstName = By.xpath(".//*[@id='customer_firstname']");
    By lastName = By.xpath(".//*[@id='customer_lastname']");
    /*By emailId = By.xpath("./*//*[@id='email']");*/
    By passWord = By.xpath(".//*[@id='passwd']");
    By days = By.xpath(".//*[@id='days']");
    By months = By.xpath(".//*[@id='months']");
    By years = By.xpath(".//*[@id='years']");
    By addressFirstName = By.xpath(".//*[@id='firstname']");
    By addressLastName = By.xpath(".//*[@id='lastname']");
    By address1 = By.xpath(".//*[@id='address1']");
    By city = By.xpath(".//*[@id='city']");
    By stateL = By.xpath(".//*[@id='id_state']");
    By zipCode = By.xpath(".//*[@id='postcode']");
    By country = By.xpath(".//*[@id='id_country']");
    By mobileAAddress = By.xpath(".//*[@id='phone_mobile']");
    By assignAddress = By.xpath(".//*[@id='alias']");
    By register = By.xpath(".//*[@id='submitAccount']");


    public CreateAnAccount(WebDriver driver){
        this.driver = driver;
    }



    public boolean verifyCreateAnAccountErrorMsg(){
        try {
            logger.info("checking for account an error message");
            driver.findElement(createAccountErrorMsg).isDisplayed();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void clickMrRadioButton(){
        logger.info("click on mr radio button");
        driver.findElement(mrRadioButton).click();
    }
    public void selectFirstName(String fName){
        logger.info("Selecting First Name");
        driver.findElement(this.firstName).sendKeys(fName);
    }
    public void selectLastName(String lName){
        logger.info("selecting last name");
        driver.findElement(this.lastName).sendKeys(lName);
    }
   /* public void selectEmailId(String Email){
        logger.info("selecting email from given email");
        driver.findElement(this.emailId).sendKeys(Email);
    }*/
    public void selectPassWord(String password){
        logger.info("selecting password");
        driver.findElement(this.passWord).sendKeys(password);
    }
    public void selectDays(String day)throws InterruptedException{
        logger.info("selecting day from day drop down menu");
        driver.findElement(days).click();

        //./*//*[@id='days']/option[10]
        String day1 ="//*[@id='days']/option[";
        String day2 ="]";
        logger.info("selecting dynamic days");
        driver.findElement(By.xpath(day1+day+day2)).click();

    }
    public void selectMonth(String month)throws InterruptedException{
        logger.info("selecting month from month drop down menu");
        driver.findElement(months).click();

        List<WebElement> monthsData = driver.findElements(By.xpath(".//*[@id='months']/option"));
        for (WebElement mon: monthsData) {
            if(mon.getText().trim().toLowerCase().equals(month.toLowerCase())){
                logger.info("selecting from month drop down");
                mon.click();
            }
        }
    }
    public void selectYears(String year)throws InterruptedException{
        logger.info("selecting year from year drop down menu");
        driver.findElement(years);

        List<WebElement> yearsData = driver.findElements(By.xpath(".//*[@id='years']/option"));
        for (WebElement yr: yearsData) {
            if (yr.getText().trim().equals(year)){
                logger.info("selecting year");
                yr.click();
            }
        }
    }
    public void selectAddressFirstName(String addressFName){
        logger.info("selecting first name");
        driver.findElement(this.addressFirstName).sendKeys(addressFName);
    }
    public void selectAddressLastName(String addressLName){
        logger.info("selecting last name");
        driver.findElement(this.addressLastName).sendKeys(addressLName);
    }
    public void selectAddress1(String address){
        logger.info("selecting address");
        driver.findElement(this.address1).sendKeys(address);
    }
    public void selectCity(String city){
        logger.info("selecting city");
        driver.findElement(this.city).sendKeys(city);
    }
    //.//*[@id='id_state']/option
    public void selectState(String state)throws InterruptedException{
        logger.info("selecting month from month drop down menu");
        driver.findElement(stateL).click();
        List<WebElement> statesData = driver.findElements(By.xpath(".//*[@id='id_state']/option"));
        for (WebElement stateList: statesData) {
            if(stateList.getText().trim().toLowerCase().equals(state.toLowerCase())){
                logger.info("selecting from state drop down");
                stateList.click();
            }
        }
    }
    public void selectZipCode(String zCode)throws InterruptedException{
        logger.info("selecting zip code");
        driver.findElement(this.zipCode).sendKeys(zCode);
    }
    public void selectCountry()throws InterruptedException{
        logger.info("selecting country from drop down menu");
        driver.findElement(country).click();
        driver.findElement(By.xpath(".//*[@id='id_country']/option[2]")).click();
    }
    public void selectMobileAAddress(String mobile){
        logger.info("selecting mobile number");
        driver.findElement(this.mobileAAddress).sendKeys(mobile);
    }
    public void selectAssignAddress(String address2){
        logger.info("selecting address 2");
        driver.findElement(this.assignAddress).sendKeys(address2);
    }
    public void clickOnRegister(){
        logger.info("clicking submit button");
        driver.findElement(register).click();
    }
    public boolean getRegistrationConfirmedMsg(){
        String msg = driver.findElement(By.xpath(".//*[@id='center_column']/p")).getText();
        if(msg.contains("Welcome to your account. Here you can manage all of your personal information and orders.")){
            return true;
        }else{
            return false;
        }

    }

    public void userNewAccountRegistration(String firstName, String lastName, String passWord, String day, String month, String year )throws InterruptedException{
        clickMrRadioButton();
        selectFirstName(firstName);
        selectLastName(lastName);
        selectPassWord(passWord);
        //Thread.sleep(6000);
        selectDays(day);
        selectMonth(month);
        selectYears(year);
        selectAddressFirstName("Jhon");
        selectAddressLastName("Carry");
        selectAddress1("340 hudson walk");
        selectCity("Brooklyn");
        selectState("New York");
        selectZipCode("11201");
        selectCountry();
        selectMobileAAddress("3478909123");
        selectAssignAddress("344 Navy Walk");
        clickOnRegister();
    }

}
