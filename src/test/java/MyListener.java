
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import page_tests.BaseTest;
import utils.BasePage;
import java.time.LocalDateTime;

//TestNG listener
public class MyListener extends BaseTest implements ITestListener {
        //WebDriver driver = new FirefoxDriver();
        @Override
        public void onTestStart(ITestResult result) {
            extentTest = reports.createTest(result.getMethod().getMethodName());
            testLogger.set(extentTest);
            testLogger.get().log(Status.INFO, "Driver start time " + LocalDateTime.now());
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            //extentTest.log(Status.PASS, "Test Passed");
            testLogger.get().log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName() + " is successful!", ExtentColor.GREEN));
            testLogger.get().log(Status.INFO, "Driver end time " + LocalDateTime.now());
        }

        @Override
        public void onTestFailure(ITestResult result) {
           // extentTest.fail(result.getThrowable());
            testLogger.get().log(Status.FAIL, "Test Failed due to: " + result.getThrowable());
            String screenshot = BasePage.getScreenshot(result.getMethod().getMethodName() + ".jpg", driver);
            testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertToBase64(screenshot)).build());
            testLogger.get().log(Status.INFO, "Driver end time " + LocalDateTime.now());
        }

        @Override
        public void onFinish(ITestContext context) {
            driver.quit();
        }

        @Override
        public void onTestSkipped(ITestResult iTestResult) {
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
            //driver.quit();
        }

        @Override
        public void onStart(ITestContext iTestContext) {
        }
    }
