package jbstores.webtesting;

import org.example.jbstores.JbStoresWebsite;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests {

    @Test
    public void searchResultsHaveMoreThanFiveResults(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.searchItem("Pillow");
        //get count
        int itemCount = jbStoresWebsite.searchResultsPage.getProductCount();
        Assert.assertTrue(itemCount > 5);
    }

    @Test
    public void clickingSearchResultItemNavigatesToItemDetailsPage(){
        JbStoresWebsite jbStoresWebsite = new JbStoresWebsite();
        jbStoresWebsite.header.searchItem("Pillow");
        jbStoresWebsite.searchResultsPage.clickOnItem(0);
        System.out.println("jbStoresWebsite.itemDetailsPage.isOnItemDetailsPage()");
        Assert.assertTrue(jbStoresWebsite.itemDetailsPage.isOnItemDetailsPage());
    }

}
