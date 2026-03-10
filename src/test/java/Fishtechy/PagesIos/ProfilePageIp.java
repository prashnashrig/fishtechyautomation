package Fishtechy.PagesIos;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePageIp {

    private IOSDriver driver;
    private WebDriverWait wait;


        public ProfilePageIp(IOSDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

       public void Profile(){
           WebElement back=wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("user_profile_https://file-stage.flytechy.site/public/user/c595d40e-c9c7-4a75-90ed-b951f7054746_cover_200_200.jpeg")));
           back.click();
           //("//XCUIElementTypeStaticText[@name="4
           //Followers"]")

       }
        private By totalCatchText = By.xpath("//XCUIElementTypeStaticText[@name=\"116\"]");

        public int getTotalCatchCount() {

            String text = driver.findElement(totalCatchText).getText();
            // Example: My Catches (25)

            String numberOnly = text.replaceAll("[^0-9]", "");
            return Integer.parseInt(numberOnly);
        }
    }

