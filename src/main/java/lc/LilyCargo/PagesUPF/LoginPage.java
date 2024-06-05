package lc.LilyCargo.PagesUPF;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
		WebDriver driver;
	
	    @CacheLookup
		@FindBy(xpath = "//input[@id='mui-1']")
	    WebElement usernameInput;
	
	    @CacheLookup
	    @FindBy(xpath = "//input[@id='mui-2']")
	    WebElement passwordInput;
	
	    @CacheLookup
	    @FindBy(css = "button[type='submit']")
	    WebElement loginButton;
	    
	    @FindBy(xpath = "//p[text()='These credentials do not match our records.']")
	    WebElement errorMessage;
	
	    
	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	
	    public void login(String username, String password) {
	        usernameInput.sendKeys(username);
	        passwordInput.sendKeys(password);
	        loginButton.click();
	        
	    }   
	    
		/*
		 * //Method to click on Login button public void clickLogin() {
		 * loginButton.click(); }
		 */
	    
	    public String getErrorMessage() {
	       return errorMessage.getText();
	    }
}
