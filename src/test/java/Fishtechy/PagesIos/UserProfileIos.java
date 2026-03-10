package Fishtechy.PagesIos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfileIos {
    private AppiumDriver driver;
    private WebDriverWait wait;

    // Stable locators by index
    private static final String FULL_NAME_FIELD_XPATH = "(//XCUIElementTypeTextField)[1]";
    private static final String USERNAME_FIELD_XPATH = "(//XCUIElementTypeTextField)[2]";
    // Gender selector: button that shows current value (Male or Female)
    private static final String GENDER_SELECTOR_PREDICATE = "type == 'XCUIElementTypeButton' AND (name == 'Male' OR name == 'Female')";

    public UserProfileIos(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @iOSXCUITFindBy(accessibility = "user_profile_https://file-stage.flytechy.site/public/user/c595d40e-c9c7-4a75-90ed-b951f7054746_cover_200_200.jpeg")
    private WebElement profile;

    @iOSXCUITFindBy(accessibility = "Edit Profile")
    private WebElement edit;

    @iOSXCUITFindBy(accessibility = "Save")
    private WebElement save;

    public void fillProfile(String fullName, String userName, String gender) {
        wait.until(ExpectedConditions.elementToBeClickable(profile)).click();
        wait.until(ExpectedConditions.elementToBeClickable(edit)).click();

        setFullName(fullName);
        setUsername(userName);
        selectGender(gender);

        wait.until(ExpectedConditions.elementToBeClickable(save)).click();
    }

    public void OwnProfile() {
        fillProfile("Tetstt", "testtt", "Male");
    }

    public void setFullName(String fullName) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(FULL_NAME_FIELD_XPATH)));
        field.click();
        field.clear();
        field.sendKeys(fullName);
    }

    public void setUsername(String userName) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(USERNAME_FIELD_XPATH)));
        field.click();
        field.clear();
        field.sendKeys(userName);
    }

    public void selectGender(String targetGender) {
        WebElement selector = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.iOSNsPredicateString(GENDER_SELECTOR_PREDICATE)));
        selector.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId(targetGender)));
        option.click();
    }
}

