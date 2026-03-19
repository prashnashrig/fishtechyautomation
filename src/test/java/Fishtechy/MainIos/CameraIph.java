package Fishtechy.MainIos;

import Fishtechy.Basee.CapabilityIos;
import Fishtechy.PagesIos.CameraIp;
import Fishtechy.PagesIos.LoginIos;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class CameraIph extends CapabilityIos {
    @Test
    public void CameraOpen() throws InterruptedException {

        LoginIos loginPage = new LoginIos((IOSDriver) driver); //for ios
        loginPage.enterEmail("testershrig+2@gmail.com", "TestTest");
        loginPage.handlePermission();
        Thread.sleep(500);

        CameraIp cam = new CameraIp((IOSDriver) driver);

        // 1. Measure Now flow
        cam.recordAndChoose(Duration.ofSeconds(6), CameraIp.AfterRecordingChoice.MEASURE_NOW);
//
//        // 2. Measure Now flow (fallback if options differ)
//        cam.recordAndChooseBestEffort(Duration.ofSeconds(6), CameraIp.AfterRecordingChoice.MEASURE_NOW);

        // 3. Retake flow: record → Retake (confirm popup) → back at camera → record again → Measure Now
        cam.recordAndChoose(Duration.ofSeconds(5), CameraIp.AfterRecordingChoice.RETAKE);
        cam.recordFor(Duration.ofSeconds(5));
        cam.chooseAfterRecording(CameraIp.AfterRecordingChoice.MEASURE_NOW);
    }
}
