package org.example.jbstores;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.example.jbstores.components.CartModal;
import org.example.jbstores.components.Header;
import org.example.jbstores.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JbStoresWebsite {

    public WebDriver webDriver;

    /*Components*/
    public Header header;
    public CartModal cartModal;

    /*Pages*/
    public AccountPage accountPage;
    public CategoryResultsPage categoryResultsPage;
    public ItemDetailsPage itemDetailsPage;
    public LoginPage loginPage;
    public SearchResultsPage searchResultsPage;
    public YourCartPage yourCartPage;

    public JbStoresWebsite(){
        webDriver = new ChromeDriver();

        header = new Header(webDriver);
        cartModal = new CartModal(webDriver);

        accountPage = new AccountPage(webDriver);
        categoryResultsPage = new CategoryResultsPage(webDriver);
        itemDetailsPage = new ItemDetailsPage(webDriver);
        loginPage = new LoginPage(webDriver);
        searchResultsPage = new SearchResultsPage(webDriver);
        yourCartPage = new YourCartPage(webDriver);

        initialiseWebsite();
    }

    private void initialiseWebsite(){
        webDriver.get("https://jbstoresonline.com");
        By headerElement = header.profileIconLocation;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(headerElement));
        System.out.println(header.HeaderLoaded());
    }

}
