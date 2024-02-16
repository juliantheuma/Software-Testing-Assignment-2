
package jbstores.modeltesting;

import nz.ac.waikato.modeljunit.AbstractListener;
import nz.ac.waikato.modeljunit.TestFailureException;

public class CustomFailureListener extends AbstractListener {
    public CustomFailureListener() {
    }

    public String getName() {
        return "log on failure";
    }

    public void failure(TestFailureException ex) {

        if(ex.getActionName().equals("addItemToCartFromItemDetailsPageErrorCount")){
            addItemToCartFromItemDetailsPageErrorCount++;
        }
        if(ex.getActionName().equals( "addItemToCartFromCategoryResultsPage")){
            addItemToCartFromCategoryResultsPageCount++;
        }

        errorCount++;

        System.out.println(addItemToCartFromItemDetailsPageErrorCount + ", " + addItemToCartFromCategoryResultsPageCount + ", " + errorCount + ", " + ex.getActionName());

    }

    public int addItemToCartFromItemDetailsPageErrorCount = 0;
    public int addItemToCartFromCategoryResultsPageCount = 0;
    public int errorCount = 0;
}

