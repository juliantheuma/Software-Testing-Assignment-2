package org.example.jbstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemDetailsPage {
    WebDriver driver;
    By itemTaxDetailsLocator = By.cssSelector(".product__tax");
    By addToCartLocator = By.cssSelector(".product-form__submit");
    By quantityInCartLocator = By.cssSelector(".quantity-cart");
    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean itemAddedToCart(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInCartLocator));
            return true;
        } catch (Exception e ){
            return false;
        }
    }
    public void addItemToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        driver.findElement(addToCartLocator).click();
    }

    public boolean isOnItemDetailsPage(){

        try{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemTaxDetailsLocator));//specific to item details page
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
