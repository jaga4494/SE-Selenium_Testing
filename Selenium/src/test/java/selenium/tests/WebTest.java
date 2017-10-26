package selenium.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class WebTest
{
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() throws Exception 
	{
		//driver = new HtmlUnitDriver();
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public static void  tearDown() throws Exception
	{
		driver.close();
		driver.quit();
	}

	
	@Test
	public void googleExists() throws Exception
	{
		driver.get("http://www.google.com");
        assertEquals("Google", driver.getTitle());		
	}
	
/*
	@Test
	public void Closed() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
		
		// http://geekswithblogs.net/Aligned/archive/2014/10/16/selenium-and-timing-issues.aspx
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
		List<WebElement> spans = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));

		assertNotNull(spans);
		assertEquals(5, spans.size());
	}
*/	
	
	// The participant count of "Frustration of Software Developers" is 55
	@Test
	public void countParticipant() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dynamicStudies']//span[.='Frustration of Software Developers']/../../..//div[@class='span4']//p//span[@class='backers']")));
        WebElement element = driver.findElement(By.xpath("//*[@id='dynamicStudies']//span[.='Frustration of Software Developers']/../../..//div[@class='span4']//p//span[@class='backers']"));
        String x = element.getText();
        
        assertEquals("55", x);
       
	}
	
	// The total number of studies closed is 5
	@Test
	public void countClosed() throws Exception
	{
		driver.get("http://www.checkbox.io/studies.html");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='CLOSED']")));
        List<WebElement> element = driver.findElements(By.xpath("//a[@class='status']/span[.='CLOSED']"));
        assertNotNull(element);
        assertEquals(5, element.size());
       
	}
	
	// If a status of a study is open, you can click on a "Participate" button
	@Test 
	public void participateButton() throws Exception
	    {
		    boolean flag=false;
	        driver.get("http://www.checkbox.io/studies.html");
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button")));
	        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='status']/span[.='OPEN']/../following-sibling::div/button"));
	        for(WebElement i :elements)
	        {
	          if(i.isEnabled())
	            flag=true;
	          else
	            flag=false;
	        assertNotNull(i);
	        assertEquals(true,flag);
	        }
	    }
	
	// Check if the "Software Changes Survey" has a Amazon reward image
	@Test 
	public void amazonRewardImage() throws Exception
	    {
			String path = "http://www.checkbox.io/media/amazongc-micro.jpg";
	        driver.get("http://www.checkbox.io/studies.html");
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Software Changes Survey']/../following-sibling::div[@class='award']/div/span/img")));
	        WebElement element = driver.findElement(By.xpath("//span[.='Software Changes Survey']/../following-sibling::div[@class='award']/div/span/img"));
	        assertNotNull(element);
	        String src = element.getAttribute("src");
	        assertEquals(src,path);
	    }
	
}
