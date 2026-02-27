package Fishtechy.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpCode {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public SignUpCode(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void Code(String email, String fullname, String passwordd) throws Exception {
        WebElement createAccount = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Create Account\"]")));
        createAccount.click();
        Thread.sleep(2000);

        // Click Continue with Email
        WebElement continueWithEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.ImageView[@content-desc=\"Continue with Email\"]")));
        continueWithEmail.click();

        WebElement fullnameInputField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"full_name_textfield\"]/android.widget.EditText")));
        fullnameInputField.click();
        Thread.sleep(2000);
        fullnameInputField.sendKeys(fullname);

        // Enter Email
        WebElement emailInputField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"email_textfield\"]/android.widget.EditText")));
        emailInputField.click();
        Thread.sleep(2000);
        emailInputField.sendKeys(email);

        // Enter Password
        WebElement passwordInputField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"toggle_visibility\"]")));
        passwordInputField.click();
        Thread.sleep(2000);
        passwordInputField.sendKeys(passwordd);

        WebElement signupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Sign Up\"]")));
        signupButton.click();
        Thread.sleep(2000);

        // Now fetch OTP (poll up to 30 seconds)
        String imapHost = "imap.gmail.com";
        String imapPort = "993";
        String username = "prashna@shrigsolutions.com";
        String password = "xdss oomd ifrm luos"; // use app password if necessary

        String otp = EmailOtpReader.fetchOtpFromInbox(imapHost, imapPort, username, password, 80);
        System.out.println("OTP found: " + otp);

        WebElement otpp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"otp_textfield\"]/android.widget.EditText")));
        otpp.click();



    }
    public void detail() throws InterruptedException {
        //username
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username_textfield\"]/android.widget.EditText")));
        username.click();
        username.sendKeys("tina");

        //gender
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Gender\"]")).click();
        driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Female\"]")).click();

//        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"dob_textfield\"]/android.view.View")).click();
//        driver.findElement(AppiumBy.xpath("//android.widget.SeekBar[@content-desc=\"September\"]")).click(); //index 3
//        driver.findElement(AppiumBy.xpath("//android.widget.SeekBar[@content-desc=\"2025\"]")).click(); //index 4
//
        Task("September", 2025);

        //click number
        driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[8]/android.widget.EditText")).click();
        //search nepal
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText")));
        search.sendKeys("nepal");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Nepal\n" +
                "+ 977\"]")).click();
        //type number
        WebElement number = wait.until(ExpectedConditions
                .visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ScrollView/android.view.View[7]/android.widget.EditText")));
        number.click();
        number.sendKeys("9840337445");
        //back press
        driver.hideKeyboard();
        System.out.println("Keyboard hidden successfully");

        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Save\"]")).click();

        //another save in unit page
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Save\"]")).click();
        //for guide
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"No\"]")).click();

        for (int i = 0; i < 3; i++) {
            try {
                WebElement guide = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.Button")
                ));
                guide.click();
            } catch (StaleElementReferenceException e) {
                WebElement allowBtn = driver.findElement(
                        AppiumBy.xpath("//android.widget.Button")
                );
                allowBtn.click();
            }    //three same guide in a row so
        }

        //last step
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Continue\"]")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Skip\"]")).click();
    }

    public void Task(String targetMonth, int targetDay) throws InterruptedException {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d"));

        try {
            WebElement dateTrigger = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.xpath("//android.widget.EditText[@resource-id=\"dob_textfield\"]/android.view.View")));
            dateTrigger.click();
            System.out.println("DateTime picker opened.");
        } catch (Exception e) {
            System.out.println("Could not open DateTime picker: " + e.getMessage());
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //for year
        WebElement yearWheel = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.SeekBar[@content-desc=\"2025\"]")));

        String currentYear = yearWheel.getAttribute("content-desc");
        int currentDayInt = Integer.parseInt(currentYear);

        while (currentDayInt != targetDay) {
            swipeWheel(yearWheel, targetDay > currentDayInt);
            Thread.sleep(1500);
            currentYear  = yearWheel.getAttribute("content-desc");
            currentDayInt = Integer.parseInt(currentYear);
        }

        //for month
        WebElement monthWheel = driver.findElement(
                AppiumBy.xpath("//android.widget.SeekBar[@content-desc=\"September\"]"));

        String currentMonth = monthWheel.getAttribute("content-desc");
        List<String> months = List.of(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");

        int targetMonthIndex = months.indexOf(targetMonth);
        int attemptCount = 0;
        while (!currentMonth.equalsIgnoreCase(targetMonth) && attemptCount < 12) {
            int currentMonthIndex = months.indexOf(currentMonth);
            boolean swipeDown = targetMonthIndex > currentMonthIndex;
            swipeWheel(monthWheel, swipeDown);
            Thread.sleep(400);
            currentMonth = monthWheel.getAttribute("content-desc");
            attemptCount++;
        }
        driver.findElement(AppiumBy.accessibilityId("Next")).click();
    }

    private void swipeWheel(WebElement wheel, boolean increase) {
        Rectangle rect = wheel.getRect();
        int centerX = rect.x + rect.width / 2;
        int centerY = rect.y + rect.height / 2;
        int swipeDistance = 100;
        int startY = increase ? centerY + swipeDistance : centerY - swipeDistance;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), centerX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}
