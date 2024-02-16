package jbstores.modeltesting;

import jbstores.modeltesting.enums.JbStoresSystemStates;
import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.example.jbstores.JbStoresWebsite;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class JbStoresSystemModelTest implements FsmModel {

    public JbStoresWebsite jbStoresWebsite;
    private JbStoresSystemStates modelState;
    private boolean navbarShown, categoriesShown, categoryResultsShown, searchResultsShown, itemDetailsPageShown, itemAddedToCartShown;
    @Override
    public Object getState() {
        return modelState;
    }

    @Override
    public void reset(boolean b) {
        modelState = JbStoresSystemStates.NAVBAR_SHOWN;
        navbarShown = true;
        categoriesShown = false;
        categoryResultsShown = false;
        searchResultsShown = false;
        itemDetailsPageShown = false;
        itemAddedToCartShown = false;

        if(b){
        if(jbStoresWebsite != null){
                jbStoresWebsite.webDriver.quit();

            }
            jbStoresWebsite = new JbStoresWebsite();
        }
    }

    //show categories (No guard needed)
    public boolean showCategoriesGuard(){
        return !getState().equals(JbStoresSystemStates.CATEGORIES_SHOWN);
    }
    public @Action void showCategories(){
        jbStoresWebsite.header.showCategories();
        categoriesShown = true;
        modelState = JbStoresSystemStates.CATEGORIES_SHOWN;

        Assert.assertEquals("Categories are shown in the model, but not in the SUT", categoriesShown, jbStoresWebsite.header.isShowingCategories() );
    }

    //show category results

    public boolean showCategoryResultsGuard(){
        return getState().equals(JbStoresSystemStates.CATEGORIES_SHOWN);
    }
    public @Action void showCategoryResults(){
        jbStoresWebsite.header.selectCategory(1);
        categoryResultsShown = true;
        modelState = JbStoresSystemStates.CATEGORY_RESULTS_SHOWN;

        Assert.assertEquals("Category results are shown in the model, but not in the SUT", categoryResultsShown, jbStoresWebsite.categoryResultsPage.isActive());
    }

    //search item
    public boolean searchItemGuard(){
        return getState().equals(JbStoresSystemStates.NAVBAR_SHOWN)
                || getState().equals(JbStoresSystemStates.CATEGORIES_SHOWN)
                || getState().equals(JbStoresSystemStates.CATEGORY_RESULTS_SHOWN)
                || getState().equals(JbStoresSystemStates.SEARCH_RESULTS_SHOWN)
                || getState().equals(JbStoresSystemStates.ITEM_DETAILS_PAGE_SHOWN);
    }

    public @Action void searchItem(){
        jbStoresWebsite.header.searchItem("Pillow");
        searchResultsShown = true;
        modelState = JbStoresSystemStates.SEARCH_RESULTS_SHOWN;

        Assert.assertEquals("Search results are shown in the model, but not in the SUT", searchResultsShown, jbStoresWebsite.searchResultsPage.isActive());
    }

    //click on item (search)
    public boolean clickOnItemFromSearchResultsGuard(){
        return getState().equals(JbStoresSystemStates.SEARCH_RESULTS_SHOWN);
    }
    public @Action void clickOnItemFromSearchResults(){
        jbStoresWebsite.searchResultsPage.clickOnItem(0);
        itemDetailsPageShown = true;
        modelState = JbStoresSystemStates.ITEM_DETAILS_PAGE_SHOWN;

        Assert.assertEquals("Model is on item details page, but SUT is not", itemDetailsPageShown, jbStoresWebsite.itemDetailsPage.isOnItemDetailsPage());
    }

    //click on item (category results)
    public boolean clickOnItemFromCategoryResultsGuard(){
        return getState().equals(JbStoresSystemStates.CATEGORY_RESULTS_SHOWN);
    }

    public @Action void clickOnItemFromCategoryResults(){
        jbStoresWebsite.categoryResultsPage.clickOnItem(0);
        itemDetailsPageShown = true;
        modelState = JbStoresSystemStates.ITEM_DETAILS_PAGE_SHOWN;

        Assert.assertEquals("Model is on item details page, but SUT is not", itemDetailsPageShown, jbStoresWebsite.itemDetailsPage.isOnItemDetailsPage());
    }


    //add item to cart (item details page)
    public boolean addItemToCartFromItemDetailsPageGuard(){
        return getState().equals(JbStoresSystemStates.ITEM_ADDED_TO_CART_SHOWN);
    }

    public @Action void addItemToCartFromItemDetailsPage(){
        jbStoresWebsite.itemDetailsPage.addItemToCart();
        itemAddedToCartShown = true;
        modelState = JbStoresSystemStates.ITEM_ADDED_TO_CART_SHOWN;

        Assert.assertEquals("Model displayed add to cart modal, but SUT did not", itemAddedToCartShown, jbStoresWebsite.cartModal.itemAddedToCart());
    }

    //add item to cart (category)
    public boolean addItemToCartFromCategoryResultsPageGuard(){
        return getState().equals(JbStoresSystemStates.CATEGORY_RESULTS_SHOWN);
    }
    public @Action void addItemToCartFromCategoryResultsPage(){
        jbStoresWebsite.categoryResultsPage.addItemToCart(0);
        itemAddedToCartShown = true;
        modelState = JbStoresSystemStates.ITEM_ADDED_TO_CART_SHOWN;

        Assert.assertEquals("Model displayed added to cart, but SUT did not", itemAddedToCartShown, jbStoresWebsite.cartModal.itemAddedToCart());
    }




    //login
    //logout

    @Test
    public void AgendaSystemModelRunner() throws Exception {
        // Instantiate your model
        JbStoresSystemModelTest model = new JbStoresSystemModelTest();

        // Create a tester instance
        Tester tester = new RandomTester(model);

        tester.setRandom(new Random());
        tester.addListener(new CustomFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(250);
        tester.printCoverage();
    }

    @Test
    public void GreedyAgendaSystemModelRunner() throws Exception {

        final Tester tester = new GreedyTester(new JbStoresSystemModelTest());
        tester.setRandom(new Random());
        tester.addListener(new CustomFailureListener());

        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(250);
        tester.printCoverage();
    }
}
