package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;


public class BriteUtilites {

    protected static WebDriver driver = Driver.getDriver();


             public static boolean retryingFindClick(By by) {
             boolean result = false;
             int attempts = 0;
             while(attempts < 2) {
                 try {
                     driver.findElement(by).click();
                     result = true;
                     break;
                 } catch(StaleElementReferenceException e) {
                 }
                 attempts++;
             }
             return result;
         }
        }





