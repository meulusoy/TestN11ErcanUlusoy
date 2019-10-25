package testErcan;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;



public class SeleniumTestErcan extends pagePack.SeleniumErcan {
	
	@Test (priority = 1)
	public void openN11test() {
		
		String expectedTitle = "n11.com";
		
		
		driver.get(url); // opens n11.com
		
		if(driver.getTitle() != null && driver.getTitle().contains(expectedTitle)){
			  System.out.println("n11.com is opened"); // checks if n11.com is opened or not
		}
		else{
			  System.out.println("n11.com could not open.");
		}
		
	}
	
	@Test (priority = 2)
	public void loginN11Test() {
		driver.get(url + "/giris-yap"); // goes to login page of n11.com
		WebElement username = driver.findElement(By.id("email")); 
		WebElement password = driver.findElement(By.id("password")); 
		 
		
		WebElement login=driver.findElement(By.id("loginButton")); 
		username.sendKeys("testdenememail123@gmail.com");  // test username and password for login n11.com
		password.sendKeys("denemetest123"); 
		login.click();
  
		 
		assertTrue(driver.getCurrentUrl().endsWith("https://www.n11.com/")); // checks if the login successful
		
	}
    
	
	@Test (priority = 3)
	public void searchWordN11Test() {
		WebElement search = driver.findElement(By.id("searchData")); 
		search.sendKeys("bilgisayar");
		
		WebElement buton =driver.findElement(By.xpath("//a[contains(@class,'searchBtn')]"));
		
		buton.click();
	}
	
	@Test (priority = 4)
	public void gotopage2N11Test() {
		driver.get(driver.getCurrentUrl() + "&pg=2"); // get second page
		
		assertTrue(driver.getCurrentUrl().contains("pg=2")); // checks if the process successful
		
	}
	
	@Test (priority = 5)
	public void pickARandomProductN11Test() {
		ArrayList<WebElement> listItems = new ArrayList<WebElement>(driver.findElements(By.xpath("//img[contains(@class,'lazy')]")));
		
		Random rand = new Random(); 
		int randomNum = rand.nextInt(listItems.size()-1); //gets a random number in the range of the product list
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		listItems.get(randomNum).click();
		
		
	}
	
	
	@Test (priority = 6)
	public void addToBasketN11Test() {
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		WebElement addtobasket = driver.findElement(By.xpath("//a[@title='Sepete Ekle']"));
		addtobasket.click();
	}
	
	@Test (priority = 7)
	public void checkBasketandActualPriceN11() {
		WebElement price =driver.findElement(By.xpath("//div[contains(@class,'newPrice')]"));
		
		
		ArrayList<WebElement> basketItems = new ArrayList<WebElement>(driver.findElements(By.xpath("//ins[contains(@class,'newPrice inline ')]")));
		
		
		String[] tokens1 = price.getText().split("\n");
	
		if(tokens1[0].equals(basketItems.get(0).getAttribute("innerText"))) {
			System.out.println("Basket price and actual price is equal !");
		}
		else
			System.out.println("Basket price and actual price is not equal !"  );
	
	}
	@Test (priority = 8)
	public void deleteItemfromBasketN11() {
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebElement removeBut = driver.findElement(By.xpath("//a[@title='Sil']")); // remove button web elements calls click operation
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", removeBut);
		
		
	}
	
	@Test (priority = 9)
	public void addTwoItemN11() {
		WebElement quant = driver.findElement(By.id("quantity"));
		quant.clear(); // clears the input place
		quant.sendKeys("2"); // then make the quantity 2
		WebElement addtobasket = driver.findElement(By.xpath("//a[@title='Sepete Ekle']"));
		addtobasket.click(); // add to basket process happens
		
	}
	
}




    
    
 
  
