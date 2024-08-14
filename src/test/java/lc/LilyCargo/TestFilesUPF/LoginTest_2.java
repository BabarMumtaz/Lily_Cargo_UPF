package lc.LilyCargo.TestFilesUPF;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import lc.LilyCargo.PagesUPF.BookedFreight;
import lc.LilyCargo.PagesUPF.LoginPage;

public class LoginTest_2 extends BaseTest {
		
		@Test
	    public void validLoginTest() { // Add ITestResult parameter
	    	             	   
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	        BookedFreight bookedFreights = PageFactory.initElements(driver, BookedFreight.class);
	        
	        loginPage.login(invalidUsername, invalidPassword);
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Booked Freights']")));

			//Capture the page heading and print on console
	        String pageHeading = bookedFreights.getHeading();
			
			if (pageHeading.equals("Booked Freights")) {

				Reporter.log("Page title " + pageHeading, true);
				Assert.assertTrue(true);

				Reporter.log("Login Test case is passed", true);
				
				// Add an explicit wait for a specific element after login
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
				
				bookedFreights.clickLogout();
				Reporter.log("Clicked Logout button", true);
			} 
			
			else {
				
				Assert.assertTrue(false);
				Reporter.log("Login Testcase is failed (Reason not confirmed yet)", true);
			}        
	   }
}
