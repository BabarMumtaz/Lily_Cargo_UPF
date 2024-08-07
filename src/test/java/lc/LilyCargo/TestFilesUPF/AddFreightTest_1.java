package lc.LilyCargo.TestFilesUPF;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import lc.LilyCargo.PagesUPF.BookedFreight;
import lc.LilyCargo.PagesUPF.LoginPage;


public class AddFreightTest_1 {
	
	LoginPage loginPage;
	BookedFreight bookedFreights;
	
	
	@Test
    public void AddFreightTest() throws InterruptedException {
		
		
		SearchContext driver;
		loginPage = PageFactory.initElements(driver, LoginPage.class);
        bookedFreights = PageFactory.initElements(driver, BookedFreight.class);
                
        loginPage.login(validUsername, validPassword);
        
        log.info("Entered InValid Username and Password.");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Create Freight']")));

        bookedFreights.clickCreateFreightBtn();
        
        log.info("Clicked Create Freight Button");
        bookedFreights.enterFNO("16001");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Loaded']")));
        
		bookedFreights.enterETDDate("2024-05-25");
		log.info("Entered ETD DATE");
		bookedFreights.enterETADate("2022-06-15");
		log.info("Entered ETA DATE");
		
		bookedFreights.enterBLNO("BL#84575487454");
		
		bookedFreights.clickClientDropdown();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'Sky Phone Ltd.')]")));
		bookedFreights.clickClientDropValue();
		log.info("Selected Client Dropdown Value");
		
		bookedFreights.clickServiceTypeDropdown();		
		bookedFreights.clickServiceTypeDropValue();
		log.info("Selected Service Type Dropdown Value");
		
		bookedFreights.clickShipperDropdown();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(.,'GORGEOUS OCEAN INTERNATIONAL')]")));
		bookedFreights.clickShipperDropValue();
		log.info("Selected Shipper Dropdown Value");
        
        Thread.sleep(5000);
        
     // Add an explicit wait for a specific element after login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
		
        bookedFreights.clickLogout();     

}
