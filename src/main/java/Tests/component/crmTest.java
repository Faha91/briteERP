package Tests.component;

import Pages.CrmPage;
import Pages.LoginPage;
import Utilities.ConfigurationReader;
import Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.BriteUtilites;
import javax.swing.text.Utilities;

public class crmTest extends TestBase {


    //Pre-condition one:
    // each CRM manager user should create at least 3 opportunities on the CRM module.

    // Pre-condition two: on Pivot table expand total and select opportunity
    // from the dropdown.


  @Test
  public void Preconditions() throws Exception {
    CrmPage crmpage = new CrmPage();
    LoginPage loginpage = new LoginPage();
    loginpage.login(ConfigurationReader.getProperty("login"), ConfigurationReader.getProperty("password"));
    WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
    wait.until(ExpectedConditions.elementToBeClickable(crmpage.crmlocator));
    crmpage.crmlocator.click();

    for (int i = 1; i < 5; i++) {
      Thread.sleep(1000);
      crmpage.createButtonLocator.click();
      wait.until(ExpectedConditions.elementToBeClickable(crmpage.opportunityTitleLocator));
      crmpage.opportunityTitleLocator.sendKeys("Title" + i);
      crmpage.CustomerLocator.sendKeys("Customer" + i + Keys.ENTER);
      crmpage.ExpectedRevenurLocator.clear();
      crmpage.ExpectedRevenurLocator.sendKeys(i + "000");
      crmpage.priorityLocator.click();;
      wait.until(ExpectedConditions.elementToBeClickable(crmpage.createOpportunityLocator));
      crmpage.createOpportunityLocator.click();
      Thread.sleep(1000);
    }

  }

   //Acceptance Criteria:
   //1.Verify that second opportunity’ Expected Revenue value on the
   // Pivot board should be the same as the Expected revenue column value on the list board.


  @Test
public void Test1() throws Exception{
    CrmPage crmpage = new CrmPage();
    LoginPage loginpage = new LoginPage();
    loginpage.login(ConfigurationReader.getProperty("login"), ConfigurationReader.getProperty("password"));
    WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
    wait.until(ExpectedConditions.elementToBeClickable(crmpage.crmlocator));
    crmpage.crmlocator.click();
    Thread.sleep(2000);
    crmpage.pivotLocator.click();
    Thread.sleep(2000);
    crmpage.minusLocator.click();
    Thread.sleep(2000);
    crmpage.plusLocator.click();
    crmpage.dropDownMenuOpportunityLocator.click();
    String pivotboardValue = crmpage.pivotSecondOpporunityLocator.getText();
    crmpage.listLocator.click();
    Thread.sleep(2000);
    String listValue = crmpage.listBoardSecondOpporunityLocator.getText();
    System.out.println("Expected: "+pivotboardValue+"\n"+"Actual: "+listValue);
    Assert.assertEquals(pivotboardValue,listValue);

  }

   //2. Verify that on the pivot table, the total expected revenue
   // should be the sum of all opportunities’ expected revenue.
  @Test
  public void Test2() throws Exception {
    CrmPage crmpage = new CrmPage();
    LoginPage loginpage = new LoginPage();
    loginpage.login(ConfigurationReader.getProperty("login"), ConfigurationReader.getProperty("password"));
    WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
    wait.until(ExpectedConditions.elementToBeClickable(crmpage.crmlocator));
    crmpage.crmlocator.click();
    Thread.sleep(2000);
    crmpage.pivotLocator.click();
    Thread.sleep(2000);
    crmpage.minusLocator.click();
    Thread.sleep(2000);
    crmpage.plusLocator.click();
    crmpage.dropDownMenuOpportunityLocator.click();
    String expected =crmpage.pivotTableTotalLocator.getText().replaceAll(",", "");
    double total = Double.valueOf(expected);
    int countOfRows = driver.findElements(By.xpath("//tbody/tr")).size();
    double sumOfAllOpportunities = 0;
    for (int i = 2; i<=countOfRows; i++){
     sumOfAllOpportunities+=Double.valueOf(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText().replaceAll(",",""));
    }
    System.out.println("Expected: "+total+"\n"+"Actual: "+sumOfAllOpportunities);
    Assert.assertEquals(total,sumOfAllOpportunities);



  }


}
