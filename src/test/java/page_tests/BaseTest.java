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
    public static WebDriver driver;
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
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
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
            }else if(AppConstants.platform.equalsIgnoreCase("remote_grid")){
                fo.addArguments("--remote-allow-origins**");
                fo.setPlatformName("linux");
                fo.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for selenium grid
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), fo);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }else
                logger.error("Platform not supported!");
        }else if (browser.equalsIgnoreCase("chrome")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                co.addArguments("--remote-allow-origins**");
                //co.addArguments("--disable-dev-shm-usage");
                WebDriverManager.chromedriver().clearDriverCache().setup();
                driver = new ChromeDriver();
            } else if (AppConstants.platform.equalsIgnoreCase("remote")) {
                co.addArguments("--remote-allow-origins**");
                co.setPlatformName("linux");
                co.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    //remote webdriver url for selenium standalone browser
                    driver = new RemoteWebDriver(new URL("http://localhost:4442"), co);
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

