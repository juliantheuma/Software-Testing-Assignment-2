package org.example.jbstores.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultsPage {
    WebDriver driver;
    By productCountLocator = By.cssSelector(".product-count__text span");
    By productListLocator = By.cssSelector(".product-grid");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnItem(int itemIndex){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productListLocator));

        WebElement productGrid = driver.findElement(productListLocator);
        List<WebElement> listItems = productGrid.findElements(By.tagName("li"));
        if (itemIndex >= 0 && itemIndex < listItems.size()) {
            // Click the list item at the specified index
            listItems.get(itemIndex).click();
        } else {
            throw new IllegalArgumentException("Index out of bounds for list items");
        }
    }

    public int getProductCount(){

        //ensure it is on the screen
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productCountLocator));

        String productCountText =  driver.findElement(productCountLocator).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(productCountText);

        if (matcher.find()) {
            String numberAsString = matcher.group();
            System.out.println(numberAsString);
            return Integer.parseInt(numberAsString);
        } else return -1;
    }

    public boolean isActive(){
        String pageTitle =  driver.getTitle();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.titleContains("Search:"));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
