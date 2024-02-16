package jbstores.webtesting;

import org.example.jbstores.JbStoresWebsite;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

public class CartTests {

    @Test
    public void userCanAddItemToCartFromItemDetailsPage(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.searchItem("Pillow");
        jbStoresWebsite.searchResultsPage.clickOnItem(0);
        jbStoresWebsite.itemDetailsPage.addItemToCart();
        boolean itemWasAddedToCart = jbStoresWebsite.itemDetailsPage.itemAddedToCart();
        Assert.assertTrue(itemWasAddedToCart);
    }

    @Test
    public void userCanAddItemToCartFromCategoryResultsPage(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.showCategories();
        jbStoresWebsite.header.selectCategory(1);
        jbStoresWebsite.categoryResultsPage.addItemToCart(2);

        try {
            Thread.sleep(Duration.ofSeconds(2)); //wait for network request
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jbStoresWebsite.header.navigateToCart();

        Assert.assertFalse(jbStoresWebsite.yourCartPage.cartIsEmpty());
    }

}
