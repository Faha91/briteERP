package briteERP.tests.component;

import briteERP.utilities.ConfigurationReader;
import briteERP.utilities.Pages;
import briteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class crmTest extends TestBase {


    //Pre-condition one:
    // each CRM manager user should create at least 3 opportunities on the CRM module.

    // Pre-condition two: on Pivot table expand total and select opportunity
    // from the dropdown.


  @Test
  public void Preconditions() throws Exception {
    extentLogger = report.createTest("Sets preconditions");
      pages.loginPage().login(ConfigurationReader.getProperty("login"), ConfigurationReader.getProperty("password"));
    WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
    wait.until(ExpectedConditions.elementToBeClickable(pages.crmpage().crmlocator));
    pages.crmpage().crmlocator.click();

    for (int i = 1; i < 5; i++) {
      Thread.sleep(1000);
        pages.crmpage().createButtonLocator.click();
      wait.until(ExpectedConditions.elementToBeClickable(pages.crmpage().opportunityTitleLocator));
        pages.crmpage().opportunityTitleLocator.sendKeys("Title" + i);
        pages.crmpage().CustomerLocator.sendKeys("Customer" + i + Keys.ENTER);
        pages.crmpage().ExpectedRevenurLocator.clear();
        pages.crmpage().ExpectedRevenurLocator.sendKeys(i + "000");
        pages.crmpage().priorityLocator.click();;
      wait.until(ExpectedConditions.elementToBeClickable(pages.crmpage().createOpportunityLocator));
        pages.crmpage().createOpportunityLocator.click();
      Thread.sleep(1000);
    }
    extentLogger.pass("Successfully prepared pre-condition");
  }

   //Acceptance Criteria:
   //1.Verify that second opportunity’ Expected Revenue value on the
   // Pivot board should be the same as the Expected revenue column value on the list board.


  @Test
public void Test1() throws Exception{
    extentLogger = report.createTest("Verifies if value on Pivot is the same as on List");
    pages.loginPage().login(ConfigurationReader.getProperty("login"), ConfigurationReader.getProperty("password"));
    WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
    wait.until(ExpectedConditions.elementToBeClickable(pages.crmpage().crmlocator));
      pages.crmpage().crmlocator.click();
      pages.crmpage().pivotLocator.click();
      pages.crmpage().minusLocator.click();
      pages.crmpage().plusLocator.click();
      pages.crmpage().dropDownMenuOpportunityLocator.click();
    String pivotboardValue = pages.crmpage().pivotSecondOpporunityLocator.getText();
      pages.crmpage().listLocator.click();
    String listValue = pages.crmpage().listBoardSecondOpporunityLocator.getText();
    Assert.assertEquals(pivotboardValue,listValue);
    extentLogger.pass("Verifies values"+"\n"+ "Pivot: "+pivotboardValue+"\n"+"List: "+listValue);

  }

   //2. Verify that on the pivot table, the total expected revenue
   // should be the sum of all opportunities’ expected revenue.
  @Test
  public void Test2() throws Exception {
    extentLogger=report.createTest("Verifies if the rows sum is same as value on total cell");
      pages.loginPage().login(ConfigurationReader.getProperty("login"), ConfigurationReader.getProperty("password"));
    WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
    wait.until(ExpectedConditions.elementToBeClickable(pages.crmpage().crmlocator));
      pages.crmpage().crmlocator.click();
      pages.crmpage().pivotLocator.click();
      pages.crmpage().minusLocator.click();
      pages.crmpage().plusLocator.click();
      pages.crmpage().dropDownMenuOpportunityLocator.click();
    String expected =pages.crmpage().pivotTableTotalLocator.getText().replaceAll(",", "");
    double total = Double.valueOf(expected);
    int countOfRows = driver.findElements(By.xpath("//tbody/tr")).size();
    double sumOfAllOpportunities = 0;
    for (int i = 2; i<=countOfRows; i++){
     sumOfAllOpportunities+=Double.valueOf(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText().replaceAll(",",""));
    }
    System.out.println("Expected: "+total+"\n"+"Actual: "+sumOfAllOpportunities);
    Assert.assertEquals(total,sumOfAllOpportunities);
    extentLogger.pass("The value are similar");

  }


}
