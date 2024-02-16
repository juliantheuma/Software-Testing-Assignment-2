package org.example.jbstores.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartModal {
    WebDriver driver;
    Header header;
    By modalNotificationLocator = By.cssSelector(".cart-notification__heading");
    public CartModal(WebDriver wd){
        driver = wd;
        header = new Header(wd);
    }

    public boolean itemAddedToCart(){
        try{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalNotificationLocator));
        return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
