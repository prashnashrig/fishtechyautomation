package Fishtechy.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;


import java.time.Duration;
import java.util.Collections;
import java.util.List;


//using android driver for keyboard escape
public class ContestCreate {
    private IOSDriver driver;   //AndroidDriver write this for android
    private WebDriverWait wait;

    public ContestCreate(IOSDriver driver) {   //AndroidDriver write this for android
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //for contest name
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"name_textfield\"]/android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Enter contest name\"]")
    private WebElement nameField;

    //for angler style
    @AndroidFindBy(xpath = "(//android.widget.Button[@content-desc=\"-Select-\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"-Select-\"])[1]")
    private WebElement anglerStyle;

    //forend date
    @AndroidFindBy(xpath = "//XCUIElementTypeOther[@name=\"Select date\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Select date\"]")
    private WebElement endDate;

    //camera type
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"-Select-\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"-Select-\"]")
    private WebElement cameraType;

    //creating contest choosing solo, invite only, proof ball with bump board
    public void ContestStep() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Contests"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Create Contest"))).click();

//        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.xpath("//android.widget.EditText[@resource-id=\"name_textfield\"]/android.widget.EditText")));

        nameField.click();
        nameField.sendKeys("prashnacontest");

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Solo"))).click();
        System.out.println("contest type selected");

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Invite Only"))).click();
        System.out.println("accessibility mode selected");

        try {
            driver.hideKeyboard();  // Best way
            System.out.println("Keyboard hidden successfully");
        } catch (Exception e) {
            // fallback if hideKeyboard fails
            driver.navigate().back();
            System.out.println("Keyboard dismissed using back()");
        }


        //wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"-Select-\"])[1]"))).click();
        anglerStyle.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Both"))).click();
        System.out.println("angler style clicked");

        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"-Select-\"])[1]"))).click();
        anglerStyle.click(); //same xpath for measurement
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Proofball with Bumpboard"))).click();
        System.out.println("measurement type selected");


        Time(3, 47);
        System.out.println("start date");

        endDate.click();
        //wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"end_date_textfield\"]/android.view.View"))).click();

        //change end date manually cause no dynamic way
        Time(6, 50);
//        wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[11]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[17]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Done"))).click();
        System.out.println("end date");

        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@content-desc=\"-Select-\"]"))).click();
        cameraType.click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Fishtechy Camera"))).click();


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Contest Banner"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Choose Photo"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Continue"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_all_button"))).click();


        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[5]/android.view.View[5]/android.view.View[2]/android.view.View"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Crop"))).click();

        shortWait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Add"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.view.View[@content-desc=\"-Select-\"]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Adriatic Brown Trout "))).click();


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"-Select-\"])[1]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("All Angler"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@content-desc=\"-Select-\"]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Longest"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Save"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next"))).click();
    }
    public void invite(List<String> inviteeNames) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (String inviteeName : inviteeNames) {
        //to invite players
        WebElement invite = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText")));
        invite.click();
        invite.clear();
        invite.sendKeys(inviteeName);

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"Invite\"])[1]"))).click();

            driver.hideKeyboard();
            System.out.println("Keyboard hidden successfully");

        // Wait until toast msg disappears
            try {
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                        AppiumBy.xpath("//android.view.View[contains(@content-desc,'Invitation successfully sent')]")));
            } catch (TimeoutException e) {
                System.out.println("Toast message did not disappear in time for: " + inviteeName);
            }
        }
        //after all invitee--click done
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.Button[@content-desc=\"Done\"]"))).click();
        //completed till here
    }

    public void logout() {

        //logout
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Profile"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"settings_button_button\"]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();
        //(""//android.widget.ImageView[@content-desc="Logout"])
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();
        //("//android.widget.Button[@content-desc=\"Logout\"]")
    }

    public void acceptInvite() {

        //now for other invitee
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id=\"notification_button\"]"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("(//android.view.View[contains(@content-desc,'invited you to join')])[1]")
        )).click();


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Accept "))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Back"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Back\"]"))).click();
    }

    public void upload() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            //toast msg to disappear
        shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                AppiumBy.xpath("//android.view.View[contains(@content-desc,'your team ready')]")));

        //for camera upload
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[3]/android.view.View[1]/android.widget.ImageView")
        )).click();
         wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Measure\"]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@resource-id=\"measurement_tutorial_popup_close_button_button\"]"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"00:04\"])[3]"))).click();

        //  Instead of fixed index [3], pick based on parameter
        //add int videoIndex in field
//        List<WebElement> videos = wait.until(
//                ExpectedConditions.presenceOfAllElementsLocatedBy(
//                        AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc, ':')]")
//                )
//        );
//
//        if (videoIndex < videos.size()) {
//            videos.get(videoIndex).click();
//        } else {
//            throw new RuntimeException("Video index " + videoIndex + " not available. Found only " + videos.size());
//        }


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Measure Now"))).click();
        //for photo
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Crop"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Measure Fish"))).click();

        WebElement fishResult = shortWait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next")));
        fishResult.click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Contest "))).click();

        //wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Export Video"))).click();

    }

   @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='start_date_textfield']/android.view.View")
   @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Select date\"])[1]")
   private WebElement startDate;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, ':')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, ':')]")
    private WebElement time;

    @AndroidFindBy(xpath = "//android.widget.SeekBar[contains(@content-desc,'minute')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@value, 'minute']")
    private WebElement minWheel;

    @AndroidFindBy(xpath = "//android.widget.SeekBar[contains(@content-desc,\"o'clock\")]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@value,\"o'clock\")]")
    private WebElement hourWheel;

    //complicated time selection because time selection is only on clockwise
    //but went anti in some case so
    public void Time(int targetHour, int targetMinute) {
        // select the start date
//        driver.findElement(AppiumBy.xpath(
//                "//android.widget.EditText[@resource-id='start_date_textfield']/android.view.View")).click();

       startDate.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // select the time
//        wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.xpath("//android.view.View[contains(@content-desc, ':')]"))).click();
            time.click();

        // select minute wheel
        minWheel.click();
//        WebElement minWheel = wait.until(ExpectedConditions.presenceOfElementLocated(
//                AppiumBy.xpath("//android.widget.SeekBar[contains(@content-desc,'minute')]")));
        spinForwardOnly(minWheel, targetMinute, 60);

        // select hour wheel
        hourWheel.click();
//        WebElement hourWheel = wait.until(ExpectedConditions.presenceOfElementLocated(
//                AppiumBy.xpath("//android.widget.SeekBar[contains(@content-desc,\"o'clock\")]")));

        int normalizedHour = (targetHour % 12 == 0) ? 12 : (targetHour % 12);
        spinForwardOnly(hourWheel, normalizedHour, 12);

        driver.findElement(AppiumBy.accessibilityId("Done")).click();
    }

    private void spinForwardOnly(WebElement wheel, int target, int modulo) {
        int safety = modulo * 3; // avoid infinite loops
        int current = readWheelValue(wheel, modulo);

        // keep swiping forward (up) until equal
        while (current != target && safety-- > 0) {
            swipeWheel(wheel, true); // ALWAYS forward
            current = readWheelValue(wheel, modulo);
        }

        if (current != target) {
            throw new RuntimeException("Failed to set wheel to " + target + " (stuck)");
        }
    }

    private int readWheelValue(WebElement wheel, int modulo) {
        int v = extractNumber(wheel.getAttribute("content-desc"));
        // hours are shown as 1..12 (never 0); minutes are 0..59
        if (modulo == 12) {
            if (v == 0) v = 12;        // just in case any OEM shows 0
            return v;                  // already 1..12
        }
        return v % modulo;             // minutes 0..59
    }

    private int extractNumber(String desc) {
        return Integer.parseInt(desc.replaceAll("\\D+", ""));
    }

    private void swipeWheel(WebElement wheel, boolean forward) {
        Rectangle r = wheel.getRect();
        int centerX = r.x + r.width / 2;
        int centerY = r.y + r.height / 2;

        // swipe from slightly below center to center = "swipe up" on most pickers
        int swipeDistance = 100;
        int startY = forward ? centerY + swipeDistance : centerY - swipeDistance;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(250), PointerInput.Origin.viewport(), centerX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    //when first permission doesn't appear the xpath changes for camera button
    // and in beta the xpath changes for every user so trying on internal live
    public void withoutPermission() {
        try {
            WebElement camera = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath
                    ("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]")));
            camera.click();
            System.out.println("first step clicked");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath
                    ("//android.view.View[@content-desc=\"Measure fish directly from your saved photos or videos.\"]/android.view.View"))).click();
            System.out.println("second step clicked");

            driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc=\"Turn on Auto-Measure to let the app measure your catch automatically.\"]/android.view.View"))).click();
            System.out.println("auto measure guide clicked");
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Done"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"close_button\"]"))).click();
            System.out.println("guide mode done clicked");
        } catch (TimeoutException e) {
            System.out.println("No guides were seen");
        }
    }

}


