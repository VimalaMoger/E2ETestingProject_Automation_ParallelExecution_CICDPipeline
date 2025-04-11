package page_tests;

import base.AppConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import static utils.ExtentReportHelper.getReportsObject;

public class BaseTest {
    public WebDriver driver;
    protected String browser;

    protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();

    protected static final ExtentReports reports = getReportsObject();
    public static ExtentTest extentTest;

    private FirefoxOptions fo;
    private ChromeOptions co;

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Parameters({"browserName"})
    @BeforeTest
    public void setupTest(@Optional String browserName){
        fo = new FirefoxOptions();
        co = new ChromeOptions();
        if (browserName != null)
            this.browser = browserName;
        else
            this.browser = AppConstants.browserName;

        logger.info("Browser name is: "+ browserName);

        if (browser.equalsIgnoreCase("firefox")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                fo.addArguments("--remote-allow-origins**");
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                driver = new FirefoxDriver(fo);
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                fo.addArguments("--remote-allow-origins**");
                fo.setPlatformName("linux");
                fo.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for selenium standalone browser
                    driver = new RemoteWebDriver(new URL("http://localhost:4441"), fo);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else if (AppConstants.platform.equalsIgnoreCase("remote_jenkins")) {
                fo.addArguments("--remote-allow-origins**");
                fo.setPlatformName("linux");
                fo.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //grid firefox and system ip address instead of localhost, 4444 is port to selenium grid
                    driver = new RemoteWebDriver(new URL("http://This_Machine_ip_address:4444/wd/hub"), fo);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else if(AppConstants.platform.equalsIgnoreCase("remote_grid")) {
                fo.addArguments("--remote-allow-origins**");
                fo.setPlatformName("linux");
                fo.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for selenium grid
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), fo);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else if(AppConstants.platform.equalsIgnoreCase("remote_git")){
                fo.addArguments("--headless");  //for github actions
                fo.addArguments("--disable-gpu");
                fo.addArguments("--no-sandbox");
                //co.setCapability("browserVersion", "134.0.6998.88");
                //co.addArguments("--remote-allow-origins**");
                //fo.addArguments("--remote-allow-origins=*");
                WebDriverManager.firefoxdriver().setup();//.clearDriverCache().setup();
                driver = new FirefoxDriver(fo);
            }else
                logger.error("Platform not supported!");
        }else if (browser.equalsIgnoreCase("chrome")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                //co.setCapability("browserVersion", "132.0.6778.86");
                co.addArguments("--remote-allow-origins**");
                //co.addArguments("--disable-dev-shm-usage");
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver = new ChromeDriver(co);
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                co.addArguments("--remote-allow-origins**");
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for selenium standalone browser
                    driver = new RemoteWebDriver(new URL("http://localhost:4441"), co);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else if (AppConstants.platform.equalsIgnoreCase("remote_jenkins")) {
                co.addArguments("--remote-allow-origins**");
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //grid chrome and system ip address instead of localhost, 4444 is port to selenium grid
                    driver = new RemoteWebDriver(new URL("http://This_Machine_ip_address:4444/wd/hub"), co);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else if(AppConstants.platform.equalsIgnoreCase("remote_grid")){
                co.addArguments("--remote-allow-origins**");
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for selenium grid
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), co);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else if(AppConstants.platform.equalsIgnoreCase("remote_git")){
                co.addArguments("--headless");  //for github actions
                co.addArguments("--disable-gpu");
                co.addArguments("--no-sandbox");
                //co.setCapability("browserVersion", "134.0.6998.88");
                //co.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();//.clearDriverCache().setup();
                driver = new ChromeDriver(co);
            }else
                logger.error("Platform not supported! ");
        }else {
                //System.out.println("Browser name entered is not supported !!!");
                logger.info("Invalid Browser: " + browserName);
        }
    }

    @AfterTest
    public void tearDownTest(){
    }

    @AfterClass
    public void flushTestReport(){
        reports.flush();
        driver.quit();
    }
}

