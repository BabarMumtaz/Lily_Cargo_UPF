package lc.LilyCargo.TestFilesUPF;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	    protected WebDriver driver;
	    protected String browser;
	    protected String url;
	    protected String validUsername;
	    protected String validPassword;
	    protected String invalidUsername;
	    protected String invalidPassword;
	
	    @BeforeMethod
	    public void setUp() {
	        // Load config properties
	    	loadConfigProperties();
	
	        // Initialize WebDriver based on config values
	        if (browser.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	        } 
	        
	        else if (browser.equalsIgnoreCase("firefox")) {
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver();
	        } 
	        
	        else {
	            throw new IllegalArgumentException("Unsupported browser specified in config.properties");
	        }
	        
	        driver.manage().window().maximize();
	        driver.get(url);
	    }
	
	    @AfterMethod
	    public void tearDown(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            captureScreenshot(result); // Capturing screenshot on test failure
	        }
	        // Your teardown code here
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	
	    protected void captureScreenshot(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            try {
	                TakesScreenshot ts = (TakesScreenshot) driver;
	                File srcFile = ts.getScreenshotAs(OutputType.FILE);
	                
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
			        String timestamp = dateFormat.format(new Date());
	                File DestFile = new File(System.getProperty("user.dir") + "/Screenshots/" + result.getName() + "_" + timestamp + ".png");
	                
	                FileUtils.copyFile(srcFile, DestFile);
	                System.out.println("Screenshot taken");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
		    private void loadConfigProperties() {
		    try {
		        Properties config = new Properties();
		        FileInputStream fis = new FileInputStream("C:\\Users\\123\\eclipse-workspace\\LilyCargoUPF\\Config File\\configFile.properties");
		        config.load(fis);
		        
				/*
				 * Retrieve browser configuration based on the prefix 
				 * 
				 * String browserPrefix = "browser."; 
				 * for (String key : config.stringPropertyNames()) { if
				 * (key.startsWith(browserPrefix)) { String browserConfig =
				 * config.getProperty(key); if (browserConfig.equalsIgnoreCase("chrome") ||
				 * browserConfig.equalsIgnoreCase("firefox")) { browser = browserConfig; break;
				 * } } }
				 */
		
		        browser = config.getProperty("browser.chrome");
		        url = config.getProperty("url");
	            validUsername = config.getProperty("valid.username");
	            validPassword = config.getProperty("valid.password");
	            invalidUsername = config.getProperty("invalid.username");
	            invalidPassword = config.getProperty("invalid.password");
		    } 
		    
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		}
    
}