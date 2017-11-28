package testbase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sharif on 10/20/2017.
 */
public class TestBase {
    public static Properties Repository = new Properties();
    public File f;
    public FileInputStream FI;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriver driver;


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
    public static void updateResult(int indexSI, String testCaseName, String testCaseStatus, String scriptName) throws IOException {
        String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

        String userDirector = System.getProperty("user.dir");

        String resultFile = userDirector + "\\src\\test\\java\\custom_report\\TestHtmlReport.html";

        File file = new File(resultFile);
        System.out.println(file.exists());

        if (!file.exists()) {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("<html>" + "\n");
            bw.write("<head><title>" + "Test execution custom_report" + "</title>" + "\n");
            bw.write("</head>" + "\n");
            bw.write("<body>");
            bw.write("<font face='Tahoma'size='2'>" + "\n");
            bw.write("<u><h1 align='center'>" + "Test execution custom_report" + "</h1></u>" + "\n");
            bw.flush();
            bw.close();
        }
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
        if (indexSI == 1) {
            bw1.write("<table align='center' border='0' width='70%' height='10'>");
            bw1.write("<tr><td width='70%' </td></tr>");
            bw1.write("<table align='center' border='1' width='70%' height='47'>");
            bw1.write("<tr>");
            bw1.write("<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>" + scriptName + " </font></b></td>");
            bw1.write("<td colspan='1' align='left'><b><font color='#000000' face='Tahoma' size='1'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>" + startDateTime + " </font></td>");
            bw1.write("</tr>");
            bw1.write("</tr>");
            bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
            bw1.write("<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test case ID : Test case Description </font></b></td>");

            bw1.write("<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
            bw1.write("</tr>");
        }
        bw1.write("<tr>" + "\n");
        bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI + "</font></td>" + "\n");
        bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + testCaseName + "</font></b></td>" + "\n");
        if (testCaseStatus == "Pass") {
            bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='Green' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
        } else {
            bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='red' face='Tahoma' size='2'>" + testCaseStatus + "</font></b></td>" + "\n");
        }
        bw1.write("</tr>" + "\n");
        bw1.write("</body>" + "\n");
        bw1.write("</html>");
        bw1.flush();
        bw1.close();
    }


    static {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat forMater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        extent = new ExtentReports(System.getProperty("user.dir")+"\\src\\test\\java\\extend_report\\test_" + forMater.format(calendar.getTime())+".html", false);
    }

    public void getresult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName() + " test is pass");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.STARTED) {
            test.log(LogStatus.INFO, result.getName() + " test is started");
        }
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
