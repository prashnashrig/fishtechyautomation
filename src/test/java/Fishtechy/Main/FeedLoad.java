package Fishtechy.Main;

import Fishtechy.Basee.Capability;
import Fishtechy.Pages.Feed;
import Fishtechy.Pages.LoginCode;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FeedLoad extends Capability {
    @Test
    public void testFeedScrollingAndLikes() throws InterruptedException {
        LoginCode loginPage = new LoginCode(driver);
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();

        Feed feed = new Feed((AndroidDriver) driver);
        feed.waitForPost();

        feed.CatchDetails(4);

        //assertTrue(feed.CatchDetails(), "Post content missing");

    }
}

