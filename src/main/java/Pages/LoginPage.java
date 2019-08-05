package Pages;

import Utilities.Driver;
import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    private static WebDriver driver = Driver.getDriver();
    public Actions action;

    @FindBy(xpath = "//*[@class='form-control' and @name  = 'login']")
    public WebElement loginLocator;

    @FindBy(id = "password")
    public WebElement passwordLocator;

    @FindBy(css = "[class='btn btn-primary']")
    public WebElement loginButtonLocator;


    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void login (String username, String password){
        loginLocator.sendKeys(username);
        passwordLocator.sendKeys(password);
        loginButtonLocator.click();



    }

}
