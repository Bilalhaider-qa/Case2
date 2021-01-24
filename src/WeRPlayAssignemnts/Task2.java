package WeRPlayAssignemnts;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Task2 {
	
	WebDriver driver = null;
	
    String RegURL = "https://www.ounass.ae/customer/register";
    String ArabicRegURL = "https://ar.ounass.ae/customer/register";
    
    String ClothingURL = "https://www.ounass.ae/women/clothing";
    String ArabicClothingURL = "https://ar.ounass.ae/women/clothing";
    
    

	@BeforeTest
	
	public void setuptest()
	{
		
	  //   System.setProperty("webdriver.gecko.driver", "C:\\Users\\Bilal's\\eclipse-workspace\\TestNG-Automation-Assignemnt\\geckodriver.exe");
	  //   driver = new FirefoxDriver();
	     System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bilal's\\eclipse-workspace\\Task2Amber\\chromedriver.exe");
	     driver = new ChromeDriver();
	}
	
	@Test(priority=0)
	public void Facebook() throws InterruptedException
	
	{
		
		driver.get(ArabicRegURL);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();
		//Click on Amber Button
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div/div[2]/div[2]/button")).click();
		Thread.sleep(8000);
		
		
		
		//Click on Login with facebook
		
		//Switching to the iFrame
	    driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[4]/div/div/iframe")));
	    //Clicking on Button
		WebElement  btnFb=driver.findElement(By.id("btnCreateAccountWithFacebook"));  
	      JavascriptExecutor fb=(JavascriptExecutor)driver;
	      fb.executeScript("arguments[0].click()", btnFb);
	      Thread.sleep(5000);
	      
	      //Switching to New Window
	      Set<String> windowId = driver.getWindowHandles();    
	        Iterator<String> itererator = windowId.iterator();
	        String mainWinID = itererator.next();
	        String  newAdwinID = itererator.next();

	        driver.switchTo().window(newAdwinID);
	        driver.findElement(By.id("email")).sendKeys("justfortesting@zohomail.com");
	        driver.findElement(By.id("pass")).sendKeys("Asdf1@345");
	        
	        driver.findElement(By.id("loginbutton")).click();
	        
	        Thread.sleep(10000);
	      
	        driver.switchTo().window(mainWinID);
	        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[4]/div/div/iframe"))); 
	 //       driver.findElement(By.xpath("/html/body/div[4]/div/div/button")).click();
	        
	   //   driver.close();
	       //Switching back to main window
	       
	  //    driver.switchTo().window(mainWinID);
	      //  driver.quit();
		
		
	}
	
	@Test(priority=1)
	public void Designerfilter() throws InterruptedException, UnsupportedEncodingException
	{
		
		//driver = new ChromeDriver();
		driver.get(ArabicClothingURL);
		Thread.sleep(3000);
		String Designer = "ALEXIS";
	    String ArabicDesigner = "اليكسيس";
		
		
	//For Arabic Version Switch the Designer Variable with ArabicDesigner in Below Function
		
		driver.findElement(By.id("search")).sendKeys(Designer);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div/div[3]/div[1]/div[5]/div/label/div/input")).click();
		Thread.sleep(3000);
		
		//Checking Product is of same designer
		
		String Product1designer = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[3]/div[1]/div[1]/div[1]/a[1]/div[2]")).getText();
	    System.out.println(Product1designer);
	    Assert.assertTrue(Product1designer.contains(Designer));
	    
	    String Product2designer = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[3]/div[1]/div[3]/div[2]/div[3]/div[2]/div[1]/div[1]/a[1]/div[2]")).getText();
	    System.out.println(Product2designer);
	    Assert.assertTrue(Product2designer.contains(Designer));
		
		
	}
	
	@Test(priority=2)
	public void Viewmorebutton() throws InterruptedException
	
	{
		
		driver.get(ArabicClothingURL);
		Thread.sleep(5000);
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		/*I am Unable to find the Viewmore button even after scrolling multiple times so
		 *  that's why i couldn't apply any assertion on that.  */
		
		
		
	}
	
	@AfterMethod
	public void invokeScreenshotMethod(ITestResult res)
	{
	if (ITestResult.FAILURE==res.getStatus()){
	try{
	TakesScreenshot ts=(TakesScreenshot)driver;
	File src = ts.getScreenshotAs(OutputType.FILE);

	//File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File("./Screenshots/"+System.currentTimeMillis()+".png"));
	System.out.println("Screenshot taken");
	}
	
	catch (IOException e)
	 
	{
	 
	System.out.println(e.getMessage());
	 
	    }
	
	
	
	}}
	
	
	
	
 }
