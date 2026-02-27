package Fishtechy.MainIos;


import Fishtechy.Basee.CapabilityIos;
import Fishtechy.PagesIos.FishMeasureIp;
import Fishtechy.PagesIos.LoginIos;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

//@Listeners(Fishtechy.Basee.TestListener.class)
public class fishUploadIph extends CapabilityIos {

    @Test
    public void Fish () throws InterruptedException {
        LoginIos loginPage= new LoginIos((IOSDriver) driver);
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();
        loginPage.CameraGuide();
        FishMeasureIp fishMeasureIp=new FishMeasureIp(driver);
        fishMeasureIp.Measure(4);
    }
}
