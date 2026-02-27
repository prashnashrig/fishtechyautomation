package Fishtechy.Basee;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class CapabilityIos {
    protected AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "iOS");
        dc.setCapability("appium:automationName", "XCUITest");
        dc.setCapability("appium:deviceName", "iPhone");
        dc.setCapability("appium:bundleId", "io.futrix.flytechy.stg");
        dc.setCapability("appium:udid", "00008110-001C253436C3801E");
        //dc.setCapability("autoAcceptAlerts", true);    //becoz of this line code was failing
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), dc);

        System.out.println("Session started");

    }

    @AfterMethod
    public void tearDown() {

    }
}



