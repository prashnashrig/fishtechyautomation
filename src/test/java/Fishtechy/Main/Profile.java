package Fishtechy.Main;

import Fishtechy.Basee.Capability;
import Fishtechy.Pages.LoginCode;
import Fishtechy.Pages.UserProfile;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

public class Profile extends Capability {
    @Test
    public void Own() throws InterruptedException {
        LoginCode loginPage = new LoginCode(driver);
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();

        UserProfile user=new UserProfile((AndroidDriver) driver);
        user.OwnProfile();

    }
}
