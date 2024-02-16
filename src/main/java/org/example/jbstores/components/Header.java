package org.example.jbstores.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;
    public By profileIconLocation = By.cssSelector("a.header__icon--account");
    public By searchButtonLocation = By.cssSelector("details .header__icon--search");
    public By searchTextBoxLocation = By.id("Search-In-Modal-1");
    public By searchSubmitButtonLocation = By.cssSelector(".search__button.field__button");
    public By openCategoriesModalLocation = By.id("HeaderMenu-household-goods");
    public By categoriesListModal = By.id("MegaMenu-Content-2");

    public By categoriesList = By.cssSelector("#MegaMenu-Content-2 ul");
    public By cartButtonLocation = By.cssSelector("a[href='/cart']");
    //public void n

    public Header(WebDriver wd){
        driver = wd;
    }

    public boolean HeaderLoaded(){
        //return true if header is loaded in 5 seconds, otherwise return false
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(profileIconLocation));
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void navigateToLoginPage(){
        driver.findElement(profileIconLocation).click();
    }

    public void searchItem(String item){
        driver.findElement(searchButtonLocation).click();
        driver.findElement(searchTextBoxLocation).sendKeys(item);
        driver.findElement(searchSubmitButtonLocation).click();
    }

    public void selectCategory(int index){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement li =  driver.findElement(categoriesList).findElements(By.tagName("li")).get(index);
        wait.until(ExpectedConditions.elementToBeClickable(li));

        li.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait2.until(ExpectedConditions.urlContains("https://jbstoresonline.com/collections"));
    }

    public void showCategories(){
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(openCategoriesModalLocation));
        driver.findElement(openCategoriesModalLocation).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoriesListModal));

    }

    public boolean isShowingCategories(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(categoriesListModal));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void navigateToCart(){
        driver.findElement(cartButtonLocation).click();
    }

}
