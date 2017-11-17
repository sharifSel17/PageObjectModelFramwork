package testbase;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sharif on 10/20/2017.
 */
public class TestBase {
    public static Properties Repository = new Properties();
    public File f;
    public FileInputStream FI;
    public WebDriver driver;

    public void init() throws IOException{
        String log4jConfPath = System.getProperty("user.dir")+"\\src\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        loadPropertiesFile();
        seletBrowser(Repository.getProperty("browser"));
        driver.get(Repository.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void loadPropertiesFile() throws IOException{
        f = new File(System.getProperty("user.dir")+"\\src\\test\\java\\configuration\\config.properties");
        FI = new FileInputStream(f);
        Repository.load(FI);
    }
    public WebDriver seletBrowser(String browser){
        if(browser.equals("firefox")||browser.equals("FIREFOX")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        }else if(browser.equals("chrome")||browser.equals("CHROME")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }else if(browser.equals("ie")||browser.equals("IE")){
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            return driver;
        }
        return null;
    }
    public void explicitWait(WebElement element, int timeToWaitInSSec){
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSSec);
        wait.until(ExpectedConditions.visibilityOf(element));

    }


    public void waitFor(int sec)throws InterruptedException{
        Thread.sleep(sec*1000);
    }
    public void closeBrowser(){
        driver.quit();
    }
}
