package Fishtechy.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MapPage {

        private AndroidDriver driver;
    private WebDriverWait wait;
        public MapPage(AndroidDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

        public void openMap(){
            WebElement map=wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Map")));
            map.click();
           handlePermission();
            waitForMapPins();
        }
    private By listToggleButton = By.xpath("//android.widget.Button[@content-desc=\"Show list\"]");
        private By mapPins = By.xpath("//android.view.View[@content-desc=\"Map Marker\"]");
        private By mapToggleButton = By.xpath("//android.widget.Button[@content-desc=\"Show map\"]");

        public void switchToList() {
            driver.findElement(listToggleButton).click();
        }

        public void switchToMap() {
            driver.findElement(mapToggleButton).click();
        }

    public int getMapPinCount() {
        // Wait until at least one pin is present
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mapPins));

        // Optional: wait a short moment to let all pins render fully
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // Get all map pins
        List<WebElement> pins = driver.findElements(mapPins);
        System.out.println("Map pins found: " + pins.size());

        return pins.size();
    }

    private void waitForMapPins() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mapPins));
    }

    public void handlePermission(){
        for (int i = 0; i < 3; i++) {
            try {
                WebElement allowBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")
                ));
                allowBtn.click();
            } catch (StaleElementReferenceException e) {
                WebElement allowBtn = driver.findElement(
                        AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")
                );
                allowBtn.click();
            } catch (TimeoutException e) {
                System.out.println("No popup appeared.");   //three same permissions in a row so
        }}
    }
    // Modern swipe for Android
    public void scrollDown() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));

        try { Thread.sleep(500); } catch (InterruptedException e) { }
    }
}


