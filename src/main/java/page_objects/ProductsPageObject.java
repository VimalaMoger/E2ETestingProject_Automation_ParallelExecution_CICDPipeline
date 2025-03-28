package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPageObject extends WebElementInteractions {

    private final By getTitleOfProductPage = By.xpath("//span[contains(text(), 'Products')]");

    private final By getTextOfFirstProduct = By.xpath("//a[@id='item_4_title_link']/div");
    //private final By getTextOfFirstProduct = By.xpath("//a[@id='item_111_title_link']/div"); //is used for failing the test

    public ProductsPageObject(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage(){
        return retrieveTextData(getTitleOfProductPage);
    }

    public String getFirstProduct(){
        return retrieveTextData(getTextOfFirstProduct);
    }
}
