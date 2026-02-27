package Fishtechy.PagesIos;

import io.appium.java_client.AppiumBy;
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
import java.time.Duration;
import java.util.Collections;
import java.util.List;


//using android driver for keyboard escape
public class ContestIp {
     private IOSDriver driver;   //AndroidDriver write this for android
    private WebDriverWait wait;

    public ContestIp(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //for contest name
//    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"name_textfield\"]/android.widget.EditText")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Enter contest name\"]")
//    private WebElement nameField;

    //for angler style
    @AndroidFindBy(xpath = "(//android.widget.Button[@content-desc=\"-Select-\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"-Select-\"])[1]")
    private WebElement anglerStyle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='start_date_textfield']/android.view.View")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Select date\"])[1]")
    private WebElement startDate;

    //forend date
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='end_date_textfield']/android.view.View")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"Select date\"]")
    private WebElement endDate;


    //camera type
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"-Select-\"]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"-Select-\"]")
    private WebElement cameraType;

    //creating contest choosing solo, invite only, proof ball with bump board
    public void ContestStep() throws InterruptedException {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Contests"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton"))).click();
        WebElement nameField= wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Enter contest name")));
        nameField.click();
        WebElement activeField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' AND visible == 1")));
        activeField.sendKeys("prashnacontest");

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
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Create Contest"))).click();

        //wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"-Select-\"])[1]"))).click();
        anglerStyle.click();
        //wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"-Select-\"])[1]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Both"))).click();
        System.out.println("angler style clicked");

        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"-Select-\"])[1]"))).click();
        anglerStyle.click(); //same xpath for measurement
        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Proofball with Bumpboard"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Proofball"))).click();

        //need to scroll
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"-Select-\"])[1]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Forktail Measurement"))).click();

        System.out.println("measurement type selected");

       startDate.click(); //keeping it here cause below function is dependent
        setTime(12, 15);

        System.out.println("start date");

        endDate.click();
        //change end date manually cause no dynamic way
        setTime(3, 21);
        System.out.println("end date");

        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@content-desc=\"-Select-\"]"))).click();
        cameraType.click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("All Camera"))).click();


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Contest Banner"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Choose Photo"))).click();
      //not seen in iphone
        //        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Continue"))).click();
//        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_all_button"))).click();


        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("(//XCUIElementTypeImage[@name=\"PXGGridLayout-Info\"])[1]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Done\"]"))).click();
        //need to scroll

        shortWait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Add"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"-Select-\"]"))).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Adriatic Brown Trout "))).click();
        WebElement search= wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search here..")));
        search.click();
        search.sendKeys("Bull Trout ");
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Bull Trout "))).click();


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"-Select-\"])[1]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("All Angler"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"-Select-\"]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Longest"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Save"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next"))).click();
    }
    public void invite(List<String> inviteeNames) throws InterruptedException {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (String inviteeName : inviteeNames) {
            //to invite players
           // WebElement invite = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeTextField[@name=\"Search here..\"]")));
            WebElement invite = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("XCUIElementTypeTextField")));
            invite.click();
            invite.clear();
            Thread.sleep(500);
            invite.sendKeys(inviteeName);

            driver.hideKeyboard();
            WebElement inviteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("(//XCUIElementTypeButton[@name=\"Invite\"])[1]")));
            inviteBtn.click();
            System.out.println("Invite clicked for: " + inviteeName);
            System.out.println("Keyboard hidden successfully");

            // Wait until toast msg disappears
            try {
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                        AppiumBy.xpath("//XCUIElementTypeOther[contains(@name,'Invitation successfully sent')]")));
            } catch (TimeoutException e) {
                System.out.println("Toast message did not disappear in time for: " + inviteeName);
            }
        }
        //after all invitee--click done
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Done\"]"))).click();
        //completed till here
    }

    public void logout() {

        //logout
       // wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeImage'"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//XCUIElementTypeImage[contains(@label, 'Profile')]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"settings_button_button\"]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();
        //(//XCUIElementTypeImage[@name="Logout"])
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();
        //("//android.widget.Button[@content-desc=\"Logout\"]")
    }

    public void acceptInvite() {

        //now for other invitee
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"notification_button\"]"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("(//XCUIElementTypeOther[contains(@name,'invited you to join')])[1]")
        )).click();


        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Accept "))).click();

        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Back"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Back\"]"))).click();
    }

    public void upload(int videoIndex) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //toast msg to disappear
        shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                AppiumBy.xpath("//XCUIElementTypeOther[contains(@name, 'your team ready')]")));

        //click camera
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeApplication[@name=\"Fishtechy(Beta)\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]/XCUIElementTypeImage")).click();
        //inside camera switching to measure
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"Measure\"]"))).click();
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
        //ui changed
//        WebElement galleryImage = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeImage' AND visible == 1 AND enabled == 1")
//                ));
//        galleryImage.click();

        //Select video
        // Select Video (unique per user)
        // =========================
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
                wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Next"))).click();
                System.out.println("Clicked Next after Measure Fish completed.");
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

//    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, ':')]")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, ':')]")
//    private WebElement time;

//    @AndroidFindBy(xpath = "//android.widget.SeekBar[contains(@content-desc,'minute')]")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@value, 'minute']")
//    private WebElement minWheel;

//    @AndroidFindBy(xpath = "//android.widget.SeekBar[contains(@content-desc,\"o'clock\")]")
//    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@value,\"o'clock\")]")
//    private WebElement hourWheel;

    //complicated time selection because time selection is only on clockwise
    //but went anti in some case so
    public void setTime(int targetHour, int targetMinute) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tap time field
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//XCUIElementTypeStaticText[contains(@name, ':')]"))).click();

        // Locate adjustable wheels
        List<WebElement> wheels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeOther' AND traits CONTAINS 'Adjustable'")
        ));

        WebElement hourWheel = wheels.get(0);
        WebElement minuteWheel = wheels.get(1);

        // Spin hour wheel forward only
        spinWheelIOS(hourWheel, targetHour % 12 == 0 ? 12 : targetHour % 12, 12);

        // Spin minute wheel
        spinWheelIOS(minuteWheel, targetMinute, 60);

        // Tap Done
        clickDone();
    }

    private void spinWheelIOS(WebElement wheel, int target, int modulo) throws InterruptedException {
        int safety = modulo * 3;
        int current = readWheelValueIOS(wheel);

        while (current != target && safety-- > 0) {
            swipeWheelIOS(wheel, true);  // forward only
            Thread.sleep(400);           // allow wheel to settle
            current = readWheelValueIOS(wheel);
        }

        if (current != target) {
            throw new RuntimeException("Failed to set wheel to " + target);
        }
    }

    private int readWheelValueIOS(WebElement wheel) {
        String val = wheel.getAttribute("value"); // e.g., "10 o'clock" or "30 minutes"
        return Integer.parseInt(val.replaceAll("\\D+", ""));
    }

    private void swipeWheelIOS(WebElement wheel, boolean forward) {
        Rectangle r = wheel.getRect();
        int centerX = r.x + r.width / 2;
        int centerY = r.y + r.height / 2;
        int swipeDistance = 50; // long enough to trigger wheel  // small distance → move fewer ticks per swipe
        int startY = forward ? centerY + swipeDistance : centerY - swipeDistance;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {} // let wheel settle

    }

    private void clickDone() {
        try {
            driver.findElement(AppiumBy.accessibilityId("Done")).click();
        } catch (Exception e) {
            try {
                driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Done' OR name == 'Done'")).click();
            } catch (Exception ignored) {}
        }
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


