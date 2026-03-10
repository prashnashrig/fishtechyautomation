package Fishtechy.MainIos;

import Fishtechy.Basee.Capability;
import Fishtechy.Basee.CapabilityIos;
import Fishtechy.PagesIos.FishMeasureIp;
import Fishtechy.Pages.LoginCode;
import Fishtechy.PagesIos.LoginIos;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

    public class MultipleFishUser extends CapabilityIos {
        static class User {
            String email;
            String password;

            User(String email, String password) {
                this.email = email;
                this.password = password;
            }
        }

        List<User> users = Arrays.asList(
                new User("testershrig+1@gmail.com", "TestTest"),
                new User("testershrig+2@gmail.com", "TestTest"),
                new User("testershrig+3@gmail.com", "TestTest")
        );

        @Test
        public void uploadFishForMultipleUsers() throws InterruptedException {

           // LoginCode loginPage = new LoginCode((AndroidDriver) driver);
            LoginIos loginPage= new LoginIos((IOSDriver) driver); //for ios
            FishMeasureIp fishMeasure = new FishMeasureIp(driver);

            // Loop
            for (int i = 0; i < users.size(); i++) {      //given 3 users
                System.out.println("Logging in user " + users.get(i).email);

                // Login
                loginPage.enterEmail(users.get(i).email, users.get(i).password);
                loginPage.handlePermission();
                   // loginPage.CameraGuide();

                // Upload fish
                fishMeasure.Measure(2); //index i, i=1 or 2 or 3

                loginPage.logout();

                System.out.println("Logged out");

                Thread.sleep(1000);
            }
        }}
