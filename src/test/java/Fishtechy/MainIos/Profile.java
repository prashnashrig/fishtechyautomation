package Fishtechy.MainIos;

import Fishtechy.Basee.CapabilityIos;
import Fishtechy.PagesIos.LoginIos;
import Fishtechy.PagesIos.UserProfileIos;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

public class Profile extends CapabilityIos {
    @Test
    public void Own() throws InterruptedException {
        LoginIos loginPage = new LoginIos((IOSDriver) driver);
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();

        UserProfileIos userProfile = new UserProfileIos(driver);
        userProfile.fillProfile("Test2", "test_2", "Male");
    }
}