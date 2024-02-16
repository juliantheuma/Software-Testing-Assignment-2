package org.example.jbstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YourCartPage {
    WebDriver driver;

    By emptyCartLocator = By.cssSelector(".cart__empty-text");
    public YourCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean cartIsEmpty(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartLocator));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
