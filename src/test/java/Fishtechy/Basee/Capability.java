package Fishtechy.Basee;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Capability {
    protected AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
//        dc.setCapability("platformName", "Android");
//        dc.setCapability("appium:deviceName", "Android");
//        dc.setCapability("appium:automationName", "uiautomator2");

        dc.setCapability("platformName", "iOS");
        dc.setCapability("appium:automationName", "XCUITest");
        dc.setCapability("appium:deviceName", "iPhone 13 Pro Max");
        dc.setCapability("appium:bundleId", "io.futrix.flytechy.stg");

        dc.setCapability("appium:udid", "00008110-000A14621E61401E");
        //driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), dc);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), dc);

        System.out.println("Session started on iOS!");

    }

    @AfterMethod
    public void tearDown() {

    }
}




    //simpler time selection
        //public void Time(int targetHour, int targetMinute) {
    //selecting first start date
        // driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"start_date_textfield\"]/android.view.View")).click(); WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // selecting the time button
        // WebElement currentTimeElement = wait.until(ExpectedConditions.presenceOfElementLocated( AppiumBy.xpath("//android.view.View[contains(@content-desc, ':')]")));
        // currentTimeElement.click();
        // selecting the minute wheel
        // WebElement minWheel = wait.until(ExpectedConditions.presenceOfElementLocated( AppiumBy.xpath("//android.widget.SeekBar[contains(@content-desc,'minute')]")));
        // int currentMinInt = extractNumber(minWheel.getAttribute("content-desc")); // e.g. "22 minutes" → 22
        // while (currentMinInt != targetMinute) {
        // swipeWheel(minWheel, targetMinute > currentMinInt);
        // currentMinInt = extractNumber(minWheel.getAttribute("content-desc"));
        // }
        // selecting hour wheel
        // WebElement hourWheel = wait.until(ExpectedConditions.presenceOfElementLocated( AppiumBy.xpath("//android.widget.SeekBar[contains(@content-desc,\"o'clock\")]")));
        // int currentHourInt = extractNumber(hourWheel.getAttribute("content-desc"));
        // while (currentHourInt != targetHour) {
        // swipeWheel(hourWheel, targetHour > currentHourInt);
        // currentHourInt = extractNumber(hourWheel.getAttribute("content-desc"));
        // } driver.findElement(AppiumBy.accessibilityId("Done")).click(); }
        // private int extractNumber(String desc) { return Integer.parseInt(desc.replaceAll("\\D+", "")); // removes non-digits }
        // private void swipeWheel(WebElement wheel, boolean increase) {
        // Rectangle rect = wheel.getRect();
        // int centerX = rect.x + rect.width / 2;
        // int centerY = rect.y + rect.height / 2; int swipeDistance = 100;
        // int startY = increase ? centerY + swipeDistance : centerY - swipeDistance;
        // PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // Sequence swipe = new Sequence(finger, 1);
        // swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        // swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), centerX, centerY));
        // swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // driver.perform(Collections.singletonList(swipe)); }