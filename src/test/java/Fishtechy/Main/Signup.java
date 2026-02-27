package Fishtechy.Main;

import Fishtechy.Basee.Capability;
import Fishtechy.Pages.SignUpCode;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

public class Signup extends Capability {

    @Test
    public void Sign() throws Exception {
        SignUpCode code=new SignUpCode((AndroidDriver) driver);
        code.Code("prashna@shrigsolutions.com","praashna","Test@1234");
        code.detail();
    }

}
