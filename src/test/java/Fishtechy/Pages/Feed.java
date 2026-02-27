package Fishtechy.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Feed {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public Feed(AndroidDriver driver) {  //AndroidDriver write this for android
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

            // Current visible post's like button
            @AndroidFindBy(xpath = ".//android.view.View[contains(@resource-id,'like_')]")
            private WebElement likeButton;

            // Current visible post's comment button
            @AndroidFindBy(xpath = ".//android.view.View[contains(@resource-id,'comment_')]")
            private WebElement commentButton;

            @AndroidFindBy(id = "clear_button")
            //("//android.widget.Button[@resource-id="clear_button"]")
            private WebElement clear;

            // Current visible post's share button
            @AndroidFindBy(accessibility = "share_button")
            private WebElement shareButton;

            public void waitForPost() {
                wait.until(ExpectedConditions.visibilityOf(likeButton));
            }

    public void likeCurrentPost() {   // adding more to validate the like count increased or not
        wait.until(ExpectedConditions.visibilityOf(likeButton));
        //read count of present state
        int beforeLike = Integer.parseInt(likeButton.getAttribute("content-desc"));

        likeButton.click();
        try { Thread.sleep(800); } catch (InterruptedException e) {}

        //Read count after like
        int afterLike = Integer.parseInt(likeButton.getAttribute("content-desc")
        );
        //verify
        if (afterLike == beforeLike + 1) {
            System.out.println(" Like verified ( " + beforeLike + " → " + afterLike + " )");
        } else {
            System.out.println(" Like NOT verified ( " + beforeLike + " → " + afterLike + " )");
        }
    }


    public void commentCurrentPost() throws InterruptedException {
                wait.until(ExpectedConditions.elementToBeClickable(commentButton)).click();
        // verify comment screen opened
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText")));

        System.out.println("Comment screen opened");

        // go back
        driver.navigate().back();
        Thread.sleep(500);
            }

            public void shareCurrentPost() {
                wait.until(ExpectedConditions.elementToBeClickable(shareButton)).click();
            }


    public void scrollPost(boolean up) {
        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int centerY = size.height / 2;
        int distance = size.height / 3;

        int startY = up ? centerY + distance : centerY - distance;
        int endY = centerY - (up ? distance : -distance);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public void CatchDetails(int numPosts) {
        for (int i = 0; i < numPosts; i++) {
            try {
                WebElement currentPost = driver.findElement(
                        AppiumBy.xpath("//android.view.View[contains(@content-desc,'Fork Length')]/ancestor::android.view.View[1]")
                );
                WebElement fishDetails = driver.findElement(
                        AppiumBy.xpath(".//android.view.View[contains(@content-desc,'Fork Length')]")
                );
                String detailsText = fishDetails.getAttribute("content-desc");

                // Username (filtered)
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement usernameElement = wait.until(ExpectedConditions.visibilityOf(
                        currentPost.findElement(
                                AppiumBy.xpath(".//android.view.View[contains(@content-desc,' ')" +
                                        " and not(contains(@content-desc,'Fork Length'))" +
                                        " and not(contains(@content-desc,'ago'))" +
                                        " and not(contains(@content-desc,'%'))]")
                        )
                ));
                String username = usernameElement.getAttribute("content-desc");

                System.out.println("Post #" + (i + 1));
                System.out.println("Username: " + username);
                System.out.println("Fish Details:\n" + detailsText);

                // for the added conditions it always does for first post and then for the iterated one
                if (i % 3 == 0) {  // doing this so it likes every 3rd post
                    likeCurrentPost();
                }
                if(i == 0){
                    commentCurrentPost();
                }
//                switch (i % 5) {   //can use this
//                    case 0 -> likeCurrentPost();
//                    case 1 -> commentCurrentPost();
//                    case 2 -> verifySave();
//                    case 3 -> verifyProfile();
//                    case 4 -> verifyShare();
//                }


                scrollPost(true);

                //return fishDetails.isDisplayed() && usernameElement.isDisplayed();
                Thread.sleep(1500);

            } catch (NoSuchElementException | TimeoutException e) {
                System.out.println("Failed to process post #" + (i + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}






