package briteERP.pages;

import briteERP.utilities.BasePage;
import briteERP.utilities.Driver;
import briteERP.utilities.TestBase;
import org.jsoup.Connection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    ;

    @FindBy(xpath = "//*[@class='form-control' and @name  = 'login']")
    public WebElement loginLocator;

    @FindBy(id = "password")
    public WebElement passwordLocator;

    @FindBy(css = "[class='btn btn-primary']")
    public WebElement loginButtonLocator;


    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public void login (String username, String password){
        loginLocator.sendKeys(username);
        passwordLocator.sendKeys(password);
        loginButtonLocator.click();



    }

}
