package Fishtechy.Main;

import Fishtechy.Basee.Capability;
import Fishtechy.Pages.ContestCreate;
import Fishtechy.Pages.LoginCode;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Contest extends Capability {
        static class User {
            String email;
            String password;

            User(String email, String password) {
                this.email = email;
                this.password = password;
            }
        }
        //list of users
        List<User> users = Arrays.asList(
                new User("testershrig+2@gmail.com", "TestTest"), // Contest creator
                new User("testershrig+1@gmail.com", "TestTest"), // invited users
                new User("testershrig+3@gmail.com", "TestTest"),
                new User("testershrig+4@gmail.com", "TestTest"),
                new User("testershrig+5@gmail.com", "TestTest"),
                new User("testershrig+6@gmail.com", "TestTest")
        );

    @Test
    public void ContestTime() throws InterruptedException {

        LoginCode loginPage = new LoginCode(driver);
        ContestCreate contest = new ContestCreate((IOSDriver) driver); //AndroidDriver write this for android

        // User 1: create contest + invite
        User creator = users.get(0);
        loginPage.enterEmail(creator.email, creator.password);
        loginPage.handlePermission();
        //loginPage.CameraGuide();

        contest.ContestStep();
        //contest.invite("aman");
        // multiple names to invite
        List<String> invitees = Arrays.asList("aman1", "aman2", "aman3");
        contest.invite(invitees);
        contest.logout();

        // Remaining users: accept invite + upload
        for (int i = 1; i < users.size(); i++) {
            User invitee = users.get(i);
            loginPage.enterEmail(invitee.email, invitee.password);
            loginPage.handlePermission();

            contest.withoutPermission();
            contest.acceptInvite();
            contest.upload();
            // each user uploads different video (i-1 because user[1] gets video[0])
           // contest.upload(i - 1);
            contest.logout();
        }


    }
}
