package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page_objects.LoginPageObject;
import page_objects.ProductsPageObject;


public class LoginPageTest extends BaseTest{

    LoginPageObject loginPageObject;
    ProductsPageObject productsPageObject;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @Test
    public void userLoginTest(){

        loginPageObject = new LoginPageObject(driver);

        productsPageObject = loginPageObject.userLogin("standard_user", "secret_sauce");

        //logger information
        logger.info("ProductTitle Name is "+productsPageObject.getTitleOfPage());
        logger.info("Product Name is "+productsPageObject.getFirstProduct());
    }
}