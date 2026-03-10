package Fishtechy.PagesIos;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JudgeIp {
    private AppiumDriver driver;
    private WebDriverWait wait;

    public JudgeIp(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



}
