package Fishtechy.MainIos;

import Fishtechy.Basee.CapabilityIos;
import Fishtechy.PagesIos.*;
import io.appium.java_client.ios.IOSDriver;

import java.util.List;

public class MapValidationTest extends CapabilityIos {

        public  void Map() throws InterruptedException{

            LoginIos loginPage= new LoginIos((IOSDriver) driver); //for ios
            loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
            loginPage.handlePermission();

            ProfilePageIp profilePage = new ProfilePageIp((IOSDriver) driver);
            MapPageIp mapPage = new MapPageIp((IOSDriver) driver);
            ListPageIp listPage = new ListPageIp((IOSDriver) driver);

            // Step 1: Get Profile total
            int profileTotal = profilePage.getTotalCatchCount();

            // Step 2: Switch to List
            mapPage.switchToList();

            // Step 3: Get all catches
            List<CatchModelIp> allCatches = listPage.getAllCatchesFromList();

            int listTotal = allCatches.size();

            if (profileTotal != listTotal) {
                System.out.println("Mismatch Found!");
            }


        }
    }

