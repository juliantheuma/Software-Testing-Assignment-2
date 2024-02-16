package org.example.jbstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryResultsPage {
    WebDriver driver;
    public By pageTitle = By.cssSelector(".collection-hero__title");
    public By collectionsList = By.id("product-grid");
    public By itemCountLocator = By.id("ProductCount");

    public CategoryResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isActive(){
        try {
            // Define the expected URL prefix
            final String urlPrefix = "https://jbstoresonline.com/collections/";
            new WebDriverWait(driver, Duration.ofSeconds(3)) // wait for a maximum of 10 seconds
                    .until((ExpectedCondition<Boolean>) d -> d.getCurrentUrl().startsWith(urlPrefix));
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void clickOnItem(int index){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(collectionsList));

        driver.findElement(collectionsList).findElements(By.tagName("li")).get(index).click();
    }

    public void addItemToCart(int index){
        //NOTE: THIS DOES NOT WORK IF THERE IS AN OPEN MODAL

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(collectionsList));

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement we = driver.findElement(collectionsList).findElements(By.tagName("li")).get(index).findElement(By.name("add"));
        wait2.until(ExpectedConditions.elementToBeClickable(we));

        we.click();
    }

    public int getResultAmount(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemCountLocator));

        String productCountText =  driver.findElement(itemCountLocator).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(productCountText);

        if (matcher.find()) {
            String numberAsString = matcher.group();
            System.out.println(numberAsString);
            return Integer.parseInt(numberAsString);
        } else return -1;
    }
}
