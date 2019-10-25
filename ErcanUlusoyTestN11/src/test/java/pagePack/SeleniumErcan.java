package pagePack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SeleniumErcan {
	public static WebDriver driver;
	public String url = "https://www.n11.com/";
	
	@BeforeClass
	public void setUp() {
		System.out.println("-----------------------");
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("-----------------------");
		System.out.println("Chrome browser launched");
	}

	
	
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			System.out.println("Closing chrome browser");
			driver.quit();
		}
	}
}
