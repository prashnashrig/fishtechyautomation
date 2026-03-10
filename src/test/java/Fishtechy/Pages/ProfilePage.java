package Fishtechy.Pages;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private AndroidDriver driver;
    private WebDriverWait wait;


        public ProfilePage(AndroidDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

    public void openProfile(){
        WebElement profile = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Profile")));
        profile.click();
    }
    public void CitizenPopup(){
            try{
                WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("close_citizen_science_sheet_button")));
            popup.click();
            }catch (Exception e) {
                System.out.println("citizen science popup didn't appeared");
            }
    }

    private By totalCatchText = By.xpath("//android.view.View[contains(@content-desc,'Catches')]");

    public int getTotalCatchCount() {

        WebElement catchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalCatchText));
        String text = catchElement.getAttribute("content-desc");
        System.out.println("Raw catch text: " + text);

        // Example text: "Catches 213"
        String numberOnly = text.replaceAll("[^0-9]", "");

        return Integer.parseInt(numberOnly);
    }
    }

