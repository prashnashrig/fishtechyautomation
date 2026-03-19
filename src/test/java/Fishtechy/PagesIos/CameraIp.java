package Fishtechy.PagesIos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CameraIp {
    private IOSDriver driver;
    private WebDriverWait wait;

    public CameraIp(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private static final By CAMERA_TAB = AppiumBy.xpath(
            "//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeImage");

    private static final By RETAKE_BTN = AppiumBy.accessibilityId("Retake");
    //("//XCUIElementTypeButton[@name="Retake"]")
    private static final By MEASURE_NOW_BTN = AppiumBy.accessibilityId("Measure Now");
    //("//XCUIElementTypeButton[@name="Measure Now"]")
    private static final By MEASURE_LATER_BTN = AppiumBy.accessibilityId("Measure Later");
    //("//XCUIElementTypeButton[@name="Measure Later"]")

    // Record/Stop has same xpath
    private static final By RECORD_OR_STOP_FALLBACK = AppiumBy.xpath(
            "//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[4]");
                      //to stop record      //("//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[4]")

    // Retake has two options
    private static final By RETAKE_CONFIRM = AppiumBy.accessibilityId("Retake");
    private static final By RETAKE_CANCEL = AppiumBy.accessibilityId("Cancel");

    // if clicked measure now
    private static final By NEXT_BTN = AppiumBy.accessibilityId("Next");
    private static final By RETAINED_BTN = AppiumBy.accessibilityId("Retained");
    private static final By POST_BTN = AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Post\"]");

    public enum AfterRecordingChoice {
        RETAKE,
        MEASURE_NOW,
        MEASURE_LATER
    }

    public enum AfterRecordingShown {
        RETAKE,
        MEASURE_NOW,
        MEASURE_LATER,
        UNKNOWN
    }

    //opens camera from the homepage
    public void openCamera() {
        wait.until(ExpectedConditions.elementToBeClickable(CAMERA_TAB)).click();
    }

    //main logic of recording does start stop
    public void recordFor(Duration duration) {
        WebElement recordOrStop = wait.until(ExpectedConditions.elementToBeClickable(RECORD_OR_STOP_FALLBACK));
        recordOrStop.click(); // start recording
        sleep(duration);
        // stop recording (same locator is ok; UI text changes)
        WebElement stop = wait.until(ExpectedConditions.elementToBeClickable(RECORD_OR_STOP_FALLBACK));
        stop.click();
    }

    //after recording, which options are seen
    public AfterRecordingShown detectAfterRecordingScreen() {
        if (exists(RETAKE_BTN)) return AfterRecordingShown.RETAKE;
        if (exists(MEASURE_NOW_BTN)) return AfterRecordingShown.MEASURE_NOW;
        if (exists(MEASURE_LATER_BTN)) return AfterRecordingShown.MEASURE_LATER;
        return AfterRecordingShown.UNKNOWN;
    }

    // choice means what option I want to choose
    public AfterRecordingChoice chooseAfterRecording(AfterRecordingChoice choice) {
        switch (choice) {
            case RETAKE -> {
                tap(RETAKE_BTN);
                confirmRetakeIfPrompted();
            }
            case MEASURE_NOW -> {
                tap(MEASURE_NOW_BTN);
                handleMeasureNowFlow();
            }
            case MEASURE_LATER -> tap(MEASURE_LATER_BTN);
        }
        return choice;
    }

    //this does the choice of what option you choose
    //combined helper method
    public AfterRecordingChoice recordAndChoose(Duration recordFor, AfterRecordingChoice choice) {
        openCamera();
        recordFor(recordFor);
        return chooseAfterRecording(choice);
    }

    // fallback but maybe not needed just extra care
    public AfterRecordingChoice recordAndChooseBestEffort(Duration recordFor, AfterRecordingChoice preferred) {
        openCamera();
        recordFor(recordFor);

        if (isShown(preferred)) {
            return chooseAfterRecording(preferred);
        }
        if (exists(MEASURE_NOW_BTN)) return chooseAfterRecording(AfterRecordingChoice.MEASURE_NOW);
        if (exists(MEASURE_LATER_BTN)) return chooseAfterRecording(AfterRecordingChoice.MEASURE_LATER);
        if (exists(RETAKE_BTN)) return chooseAfterRecording(AfterRecordingChoice.RETAKE);

        throw new TimeoutException("No post-recording options found (Retake / Measure Now / Measure Later).");
    }

    private boolean isShown(AfterRecordingChoice choice) {
        return switch (choice) {
            case RETAKE -> exists(RETAKE_BTN);
            case MEASURE_NOW -> exists(MEASURE_NOW_BTN);
            case MEASURE_LATER -> exists(MEASURE_LATER_BTN);
        };
    }

    private void tap(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void confirmRetakeIfPrompted() {
        // If choose retake, popup with Retake / Cancel appears.
        // If it appears, tap Retake again.
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            if (!driver.findElements(RETAKE_CANCEL).isEmpty() || !driver.findElements(RETAKE_CONFIRM).isEmpty()) {
                shortWait.until(ExpectedConditions.elementToBeClickable(RETAKE_CONFIRM)).click();
            }
        } catch (Exception ignored) {
        }
    }

    private void handleMeasureNowFlow() {
        // measure now flow, wait long for Next after measure, then Retained, then Post.
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(200));
        longWait.until(ExpectedConditions.elementToBeClickable(NEXT_BTN)).click();
        // Released for another
        wait.until(ExpectedConditions.elementToBeClickable(RETAINED_BTN)).click();
        // Final Post
        wait.until(ExpectedConditions.elementToBeClickable(POST_BTN)).click();
    }

    private boolean exists(By locator) {
        try {
            List<WebElement> els = driver.findElements(locator);
            return !els.isEmpty();
        } catch (Exception ignored) {
            return false;
        }
    }

    private static void sleep(Duration d) {
        try {
            Thread.sleep(Math.max(0, d.toMillis()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
