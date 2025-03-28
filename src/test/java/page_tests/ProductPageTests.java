package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;

public class ProductPageTests extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Test
    public void testToGetProductName(){
        loginPageObject = new LoginPageObject(driver);
        productsPageObject = loginPageObject.userLogin("standard_user", "secret_sauce");
        //To check if routed to correct page
        //System.out.println(productsPageObject.getTitleOfPage());
        //System.out.println(productsPageObject.getFirstProduct());
        logger.info("ProductTitle Name is "+productsPageObject.getTitleOfPage());
        logger.info("Product Name is "+productsPageObject.getFirstProduct());
    }
}
