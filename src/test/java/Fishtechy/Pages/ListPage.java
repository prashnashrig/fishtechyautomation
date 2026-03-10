package Fishtechy.Pages;
import Fishtechy.PagesIos.CatchModelIp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ListPage {

        private AndroidDriver driver;
    private WebDriverWait wait;

        public ListPage(AndroidDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

    // Each catch item in list
    private By catchCells = By.xpath("//android.view.View[contains(@content-desc,'\nL ')]");

    // Detect Missing Location from content-desc
    public List<CatchModel> getAllCatchesFromList() {
        List<CatchModel> catchList = new ArrayList<>();
        Set<String> uniqueItems = new HashSet<>();
        boolean endReached = false;
            int emptyScrolls = 0;
            int maxEmptyScrolls = 6;  // allow more scrolls for large lists

            while (!endReached && emptyScrolls < maxEmptyScrolls) {
                List<WebElement> items = driver.findElements(catchCells);
                int beforeCount = uniqueItems.size();

                for (WebElement item : items) {
                String desc = item.getAttribute("content-desc");
                String[] lines = desc.split("\n");
                if (lines.length < 2) continue;  // safety
                String fishName = lines[0];
                String date = lines[1];
                boolean hasLocation = !desc.contains("Missing Location");

                String uniqueKey = fishName + date;
                if (!uniqueItems.contains(uniqueKey)) {
                    uniqueItems.add(uniqueKey);
                    catchList.add(new CatchModel(fishName, date, hasLocation));
                }
            }

            // Scroll
            scrollDown();

            // wait for items to load
            try { Thread.sleep(1000); } catch (InterruptedException e) { }

            int afterCount = uniqueItems.size();
            if (afterCount == beforeCount) emptyScrolls++;
            else emptyScrolls = 0;

            if (emptyScrolls >= maxEmptyScrolls) endReached = true;
        }

        return catchList;
    }

    public List<CatchModel> getCatchesWithLocation() {
        return getAllCatchesFromList().stream()
                .filter(CatchModel::hasLocation)
                .collect(Collectors.toList());
    }

    // Same swipe logic as MapPage
    private void scrollDown() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));

        try { Thread.sleep(500); } catch (InterruptedException e) { }
    }
}

