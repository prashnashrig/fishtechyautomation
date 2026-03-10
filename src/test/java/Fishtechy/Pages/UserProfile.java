package Fishtechy.Pages;

import com.aventstack.extentreports.gherkin.model.And;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfile {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public UserProfile(AndroidDriver driver){
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Profile")
    //("//android.widget.ImageView[@content-desc="Profile"]")
    private WebElement profile;

    @AndroidFindBy(xpath = "Tab 3 of 4\\nsaved")
    private WebElement saved;

    @AndroidFindBy(accessibility = "Edit Profile")
    private WebElement edit;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text=\"Test\"]")
    private WebElement FullName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text=\"test\"]")
    private  WebElement UserName;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Male\"]")
    private WebElement DropDown;

    @AndroidFindBy(accessibility = "Male")
    private WebElement gender;

    @AndroidFindBy(accessibility = "Save")
    private WebElement save;



    public void OwnProfile(){
        wait.until(ExpectedConditions.elementToBeClickable(profile)).click();

        wait.until(ExpectedConditions.elementToBeClickable(edit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(FullName)).click();
        FullName.clear();
        FullName.sendKeys("Tetstt");

        wait.until(ExpectedConditions.elementToBeClickable(UserName)).click();
        UserName.click();
        UserName.sendKeys("testtt");

        wait.until(ExpectedConditions.elementToBeClickable(DropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(gender)).click();

        wait.until(ExpectedConditions.elementToBeClickable(save)).click();


    }
}
