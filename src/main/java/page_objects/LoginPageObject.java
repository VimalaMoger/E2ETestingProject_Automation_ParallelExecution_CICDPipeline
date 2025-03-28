package page_objects;

import generic_keywords.WebElementInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends WebElementInteractions {

    private final By userNameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    public ProductsPageObject userLogin(String username, String password){

        goToApplication("https://www.saucedemo.com/");
        sendText(userNameTextField, username);
        try {
            Thread.sleep(1000);
            sendText(passwordTextField,password);
            clickElement(loginBtn);
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ProductsPageObject((driver));
    }
}
