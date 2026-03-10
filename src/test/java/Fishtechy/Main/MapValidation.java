package Fishtechy.Main;

import Fishtechy.Basee.Capability;
import Fishtechy.Pages.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MapValidation extends Capability {
    @Test
    public void Map() throws InterruptedException {

        LoginCode loginPage = new LoginCode(driver);  //android
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();

        ProfilePage profilePage = new ProfilePage((AndroidDriver) driver);
        MapPage mapPage = new MapPage((AndroidDriver) driver);
        ListPage listPage = new ListPage((AndroidDriver) driver);

        // Step 1: Get Profile total
        profilePage.openProfile();
        int profileTotal = profilePage.getTotalCatchCount();

        // Step 2: Switch to List
        mapPage.openMap();
        mapPage.switchToList();

        // Step 3: Get all catches
        List<CatchModel> allCatches = listPage.getAllCatchesFromList();
        List<CatchModel> catchesWithLocation = listPage.getCatchesWithLocation();

        // Step 4: Switch to map and get pins count
        mapPage.switchToMap();
        int mapPins = mapPage.getMapPinCount();

        // Step 5: Print/Compare
        System.out.println("Profile total: " + profileTotal);
        System.out.println("List total: " + allCatches.size());
        System.out.println("List catches with location: " + catchesWithLocation.size());
        System.out.println("Map pins: " + mapPins);

        if (profileTotal != allCatches.size()) {
            System.out.println("Mismatch: Profile total != List total");
        }

        if (catchesWithLocation.size() != mapPins) {
            System.out.println("Mismatch: List catches with location != Map pins");
        }
    }

}

