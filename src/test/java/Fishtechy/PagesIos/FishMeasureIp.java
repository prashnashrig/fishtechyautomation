package Fishtechy.PagesIos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FishMeasureIp {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public FishMeasureIp(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void Measure(int videoIndex) throws InterruptedException {
        //in homepage and selecting camera to upload the fish
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeImage")).click();

        WebElement galleryButton = wait.until(
                ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@name,'GALLERY')]")));
        System.out.println("Gallery button displayed: " + galleryButton.isDisplayed());
        galleryButton.click();

        //Handle tutorial popup
        try {
            List<WebElement> popup = driver.findElements(AppiumBy.accessibilityId("measurement_tutorial_popup_close_button_button"));
            if (!popup.isEmpty()) {
                popup.get(0).click();
                System.out.println("Popup appeared and handled.");
            } else {
                System.out.println("No popup appeared.");
            }
        } catch (Exception e) {
            System.out.println("Popup handling skipped: " + e.getMessage());
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Recents\"]"))).click();

        //Select video
        try {
            List<WebElement> videos = driver.findElements(AppiumBy.className("XCUIElementTypeImage"));
            if (videos.size() > videoIndex) {
                videos.get(videoIndex).click(); // Different video for each user
                System.out.println("Video #" + videoIndex + " selected.");
            } else {
                videos.get(0).click(); // fallback
                System.out.println("Fallback: first video selected.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Video selection failed: " + e.getMessage());
        }

        //Decide next step based on video upload status
        try {
            // If "Next" appears → uploaded video flow
            Thread.sleep(400);
            List<WebElement> nextBtns = driver.findElements(AppiumBy.accessibilityId("Next"));
            if (!nextBtns.isEmpty()) {
                System.out.println("Uploaded video detected, proceeding...");
                nextBtns.get(0).click();
                wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Next"))).click();
            } else {
                System.out.println("Unuploaded video detected, checking further...");

                // Check for Measure Now
                WebElement measureNow = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Measure Now")));
                measureNow.click();

                // Check if short video (<3 sec)
                List<WebElement> measureFish = driver.findElements(AppiumBy.accessibilityId("Measure Fish"));
                if (!measureFish.isEmpty()) {
                    measureFish.get(0).click();
                    System.out.println("Short video flow triggered (Measure Fish directly).");
                } else {
                    // For longer videos
                    wait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Next"))).click();
                    wait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Measure Fish"))).click();
                    System.out.println("Normal unuploaded video flow triggered.");
                }

                // Wait long for Next button after Measure Fish
                WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(200));
                WebElement nextAfterMeasure = longWait.until(
                        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next")));
                nextAfterMeasure.click();
                System.out.println("Clicked Next after Measure Fish completed.");

                nextAfterMeasure.click();
                System.out.println("Clicked second Next");
            }

            //Released for another
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Retained"))).click();

            //Final Post
            wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Post\"]"))).click();
            System.out.println("Post clicked successfully.");

        } catch (Exception e) {
            System.out.println("Flow handling failed: " + e.getMessage());
            throw e;
        }
    }


}
