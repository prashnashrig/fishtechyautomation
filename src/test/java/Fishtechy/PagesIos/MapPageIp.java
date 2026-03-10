package Fishtechy.PagesIos;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MapPageIp {

        private IOSDriver driver;
    private WebDriverWait wait;
        public MapPageIp(IOSDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

        public void Map(){
            WebElement map=wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Map")));
            map.click();
        }
        private By listToggleButton = By.id("list_toggle_button");
        private By mapPins = By.xpath("//XCUIElementTypeButton[@name=\"GMSMarker_0x128f34400\"]");
        private By mapToggleButton = By.id("map_toggle_button");

        public void switchToList() {
            driver.findElement(listToggleButton).click();
        }

        public void switchToMap() {
            driver.findElement(mapToggleButton).click();
        }

        public int getMapPinCount() {

            List<WebElement> pins = driver.findElements(mapPins);
            return pins.size();
        }
    }

