package jbstores.webtesting;

import org.example.jbstores.JbStoresWebsite;
import org.junit.Assert;
import org.junit.Test;

public class CategorySelectionTests {

    @Test
    public void categorySelectionResultsHaveMoreThanFiveResults(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.showCategories();
        jbStoresWebsite.header.selectCategory(1);
        int itemsFound = jbStoresWebsite.categoryResultsPage.getResultAmount();

        Assert.assertTrue(itemsFound > 5);
    }

    @Test
    public void clickingItemNavigatesToItemDetailsPage(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.showCategories();
        jbStoresWebsite.header.selectCategory(1);

        jbStoresWebsite.categoryResultsPage.clickOnItem(1);
        Assert.assertTrue(jbStoresWebsite.itemDetailsPage.isOnItemDetailsPage());
    }
}
