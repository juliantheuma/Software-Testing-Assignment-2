package org.example.jbstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    By inputEmail = By.name("customer[email]");
    By inputPassword = By.name("customer[password]");
    By loginButton = By.id("customer_login");
    By logoutLink = By.cssSelector("a[href='/account/logout']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void LoginWithEmailAndPassword(String email, String password){
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputPassword).sendKeys(password);
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(loginButton).submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
    }

    public void LogoutUser(){
        driver.findElement(logoutLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("https://jbstoresonline.com/"));
    }
}
