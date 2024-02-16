package jbstores.webtesting;

import org.example.jbstores.JbStoresWebsite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

public class UserLoginTests {

    @Test
    public void userCanLogin(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.navigateToLoginPage();
        jbStoresWebsite.loginPage.LoginWithEmailAndPassword("julian.theuma.20@um.edu.mt", "TestingAssignment123");

        Assert.assertTrue(jbStoresWebsite.webDriver.getCurrentUrl().equals("https://jbstoresonline.com/account"));
    }

    @Test
    public void userCanLogout(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.navigateToLoginPage();
        jbStoresWebsite.loginPage.LoginWithEmailAndPassword("julian.theuma.20@um.edu.mt", "TestingAssignment123");
        jbStoresWebsite.loginPage.LogoutUser();

        Assert.assertTrue(jbStoresWebsite.webDriver.getCurrentUrl().equals("https://jbstoresonline.com/"));

    }

}
