package Fishtechy.Basee;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static Fishtechy.Basee.ExtentReportManager.getExtentReports;

public class TestListener implements ITestListener {

    private static ExtentReports extent = getExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed successfully!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture screenshot
        Object currentClass = result.getInstance();
        AppiumDriver driver = ((Capability) currentClass).driver;  // driver from Capability class

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = System.getProperty("user.dir") + "/reports/screenshots/"
                + result.getMethod().getMethodName() + ".png";
        try {
            new File(destPath).getParentFile().mkdirs();  // create folder if not exists
            Files.copy(srcFile.toPath(), new File(destPath).toPath());

            test.get().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(destPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // write everything to the report
    }

    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
}
