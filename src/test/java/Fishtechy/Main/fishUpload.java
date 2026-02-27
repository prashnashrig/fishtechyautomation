package Fishtechy.Main;

import Fishtechy.Basee.Capability;
import Fishtechy.Pages.FishMeasure;
import Fishtechy.Pages.LoginCode;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

//@Listeners(Fishtechy.Basee.TestListener.class)
public class fishUpload extends Capability {

    @Test
    public void Fish () throws InterruptedException {
        LoginCode loginPage = new LoginCode((AndroidDriver) driver);
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();
        loginPage.CameraGuide();
        FishMeasure fishMeasure=new FishMeasure(driver);
        fishMeasure.Measure();

    }
}
