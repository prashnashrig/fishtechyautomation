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
        //driver.navigate().back();
        WebElement back=wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Or continue with")));
        back.click();
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

