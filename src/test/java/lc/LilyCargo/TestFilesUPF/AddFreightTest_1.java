package lc.LilyCargo.TestFilesUPF;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import lc.LilyCargo.PagesUPF.BookedFreight;
import lc.LilyCargo.PagesUPF.LoginPage;


public class AddFreightTest_1 extends BaseTest {
	
	LoginPage loginPage;
	BookedFreight bookedFreights;
	
	
	@Test
    public void AddFreightTest() throws InterruptedException {
				
		loginPage = PageFactory.initElements(driver, LoginPage.class);
        bookedFreights = PageFactory.initElements(driver, BookedFreight.class);
                
        loginPage.login(validUsername, validPassword);
                
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Create Freight']")));

        
        Thread.sleep(5000);
        
     // Add an explicit wait for a specific element after login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
		
        bookedFreights.clickLogout();
        Reporter.log("Clicked Logout button", true);
	}
}
