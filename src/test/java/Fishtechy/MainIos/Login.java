package Fishtechy.MainIos;


import Fishtechy.Basee.CapabilityIos;
import Fishtechy.PagesIos.LoginIos;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Fishtechy.Basee.TestListener.class)
public class Login extends CapabilityIos {

    @Test
    public void loginShouldSucceed() throws InterruptedException {

        LoginIos loginPage= new LoginIos((IOSDriver) driver); //for ios
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();
        Thread.sleep(500);
        Thread.sleep(500);
        loginPage.CameraGuide();

    }
}
