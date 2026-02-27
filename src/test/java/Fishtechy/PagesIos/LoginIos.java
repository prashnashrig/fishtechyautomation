package Fishtechy.PagesIos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginIos {
    private IOSDriver driver;
    private WebDriverWait wait;

    public LoginIos(IOSDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Text fields
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='email_textfield']/android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Enter your email\"]")  // iOS email field
    private WebElement emailField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='toggle_visibility']")
    @iOSXCUITFindBy(accessibility = "toggle_visibility") // iOS password field
    private WebElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Login']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Login\"]")
    private WebElement finalLoginBtn;

    public void enterEmail(String email, String password) throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
//        wait.until(ExpectedConditions.visibilityOf(continueWithEmail)).click();
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        driver.hideKeyboard();
        wait.until(ExpectedConditions.visibilityOf(finalLoginBtn)).click();

    }

    public void handlePermission() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //Handle system permission popup
        try {
            WebElement allowButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")
            ));
            allowButton.click();
            System.out.println("System permission popup appeared and 'Allow' was clicked.");
            // Wait for permission popup to disappear
            wait.until(ExpectedConditions.invisibilityOf(allowButton));
            // Wait extra time for toast overlay to disappear
            Thread.sleep(2500);

        } catch (TimeoutException e) {
            System.out.println("No system permission popup appeared.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Handle Proof Ball popup
        try {
            WebElement skipButton = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.accessibilityId("Skip")
            ));
            //("//android.widget.Button[@content-desc='Skip']")
            skipButton.click();
            System.out.println("Register your Proof Ball popup appeared and was clicked.");

            // Wait until Proof Ball popup disappears
            wait.until(ExpectedConditions.invisibilityOf(skipButton));

            // Extra short wait to ensure UI has stabilized
            Thread.sleep(500);

        } catch (TimeoutException e) {
            System.out.println("No Register Proof Ball popup appeared.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //adding this part cause without this skip button doesn't work
        try {
            WebElement contests = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.accessibilityId("Contests")
            ));
            System.out.println("Main screen loaded successfully.");
        } catch (TimeoutException e) {
            System.out.println("Main screen (Contests) did not appear in time.");
        }
    }


    public void CameraGuide(){
        try{
//        WebElement camera=wait.until(ExpectedConditions.presenceOfElementLocated(
//                AppiumBy.xpath("///XCUIElementTypeApplication[@name=\"Fishtechy(Beta)\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther[4]/XCUIElementTypeOther[2]/XCUIElementTypeImage")));
            WebElement camera=wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]")));

            camera.click();
            System.out.println("first step clicked");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath
                    ("//android.view.View[@content-desc=\"Measure fish directly from your saved photos or videos.\"]/android.view.View"))).click();
            System.out.println("second step clicked");
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath
                    ("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]"))).click();
            System.out.println("long pressed camera");
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Continue"))).click();
            System.out.println("clicked continue");

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
            }   //trying this cause the code breakout after several run saying stale element reference exception

            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc=\"Turn on Auto-Measure to let the app measure your catch automatically.\"]/android.view.View"))).click();
            System.out.println("auto measure guide clicked");
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Done"))).click();
            System.out.println("guide mode done clicked");
            //android.view.View[@content-desc="Done"]

            //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("close_button"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"close_button\"]"))).click();
        } catch (TimeoutException e) {
            System.out.println("No guides were seen");
        }
    }

    public void logout() throws InterruptedException {
        //logout
        // wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeImage'"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//XCUIElementTypeImage[contains(@label, 'Profile')]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"settings_button_button\"]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();
        //(//XCUIElementTypeImage[@name="Logout"])
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Logout"))).click();
        //("//android.widget.Button[@content-desc=\"Logout\"]")
    }
}

