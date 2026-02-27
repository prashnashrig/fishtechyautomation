package Fishtechy.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FishMeasure {
        private AppiumDriver driver;
        private WebDriverWait wait;

        public FishMeasure(AppiumDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void Measure() throws InterruptedException {
            //in homepage and selecting camera to upload the fish
            driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Fishtechy(Beta)\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]/XCUIElementTypeImage")).click();

            //permissions about camera......
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Continue"))).click();
            driver.switchTo().alert().accept();

            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Measure\"]"))).click();


            //this behaves so stupid failing sometime or  running sometime
            handlePermission();

            //this is auto measure off but due to login code resume can't start from here
            //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("AUTO MEASURE"))).click();

            //inside camera clicking media
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.ImageView[1]"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Continue"))).click();
            try {
                WebElement allowButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.id("com.android.permissioncontroller:id/permission_allow_all_button")
                ));
                allowButton.click();
                System.out.println("System permission popup appeared and 'Allow' was clicked.");
            } catch (TimeoutException e) {
                System.out.println("No system permission popup appeared.");
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"measurement_tutorial_popup_close_button_button\"]"))).click();


//            wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[2]"))).click();


//            WebElement guide=wait.until(ExpectedConditions.presenceOfElementLocated(
//                    AppiumBy.accessibilityId("Continue")));
//            guide.click();
//
//            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_all_button"))).click();
//
//            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("measurement_tutorial_popup_close_button_button"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"00:10\"]"))).click();
            //vdo select

            //Next
            //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("close_gallery_button"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Measure Now"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Export Video"))).click();

            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Measure Fish"))).click();
            // Wait up to 90 seconds for Next button to appear
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(200));
            WebElement nextButton = longWait.until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next"))
            );

            nextButton.click();
            System.out.println("Clicked Next after Measure Fish completed.");

            //Released for another
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Retained"))).click();

            driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Post\"]")).click();
            //if all perfect contest is fine without selecting
            //then post will be redirected to your page and then setting logout login loop

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
            }    //three same permissions in a row so
        }}
    }
