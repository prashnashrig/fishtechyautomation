package Fishtechy.PagesIos;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
public class ListPageIp {

        private IOSDriver driver;
    private WebDriverWait wait;

        public ListPageIp(IOSDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

        private By catchCells = By.xpath("//XCUIElementTypeCell");
        private By locationIcon = By.xpath(".//XCUIElementTypeImage[@name='location_icon']");

        public void scrollDown() {
            HashMap<String, String> scrollObject = new HashMap<>();
            scrollObject.put("direction", "down");
            driver.executeScript("mobile: scroll", scrollObject);
        }

        public List<CatchModelIp> getAllCatchesFromList() {

            List<CatchModelIp> catchList = new ArrayList<>();
            Set<String> uniqueItems = new HashSet<>();

            boolean endReached = false;

            while (!endReached) {

                List<WebElement> items = driver.findElements(catchCells);

                for (WebElement item : items) {

                    String fishName = item.findElement(
                                    By.xpath(".//XCUIElementTypeStaticText[1]"))
                            .getText();

                    String date = item.findElement(
                                    By.xpath(".//XCUIElementTypeStaticText[2]"))
                            .getText();

                    boolean hasLocation = item.findElements(locationIcon).size() > 0;

                    String uniqueKey = fishName + date;

                    if (!uniqueItems.contains(uniqueKey)) {
                        uniqueItems.add(uniqueKey);
                        catchList.add(new CatchModelIp(fishName, date, hasLocation));
                    }
                }

                int beforeScroll = uniqueItems.size();
                scrollDown();
                int afterScroll = uniqueItems.size();

                if (beforeScroll == afterScroll) {
                    endReached = true;
                }
            }

            return catchList;
        }
    }

