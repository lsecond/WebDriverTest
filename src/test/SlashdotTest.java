package test;

import modules.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author John L
 * @version 1.0
 * 
 */
public class SlashdotTest {

	public WebDriver driver;
	private String testEnv;
	private String testPlatform;
	private String homeLocation;
	private MainPage mainPage;
	private PollBooth pollBooth;
	private static final String SLASHDOT = "http://slashdot.org/";

	private String chrome_driver;
	
	public SlashdotTest() {
		mainPage = new MainPage();
		pollBooth = new PollBooth();
	}

	public void setTestEnv(String url) {
		this.testEnv = url;
		System.out.println("test env : " + this.testEnv);
	}

	public void setHomeLocation(String loc) {
		this.homeLocation = loc;
	}

	public void setTestPlatform(String testPlatform) {
		this.testPlatform = testPlatform;
	}

	public boolean tearDown() {
		if (driver != null) {
			System.out.println("Close driver");
			driver.close();
			driver.quit();
		}
		return true;
	}

	public void setup() {
		try {
			System.out.println("test begin");
			if (this.testPlatform.equalsIgnoreCase("windows")) {
				chrome_driver = this.homeLocation + "chromedriver.exe";
			} else {
				chrome_driver = this.homeLocation + "chromedriver";
			}
		   	System.setProperty("webdriver.chrome.driver", chrome_driver);
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			// Maximize browser size
			driver.manage().window().maximize();
			System.out.println("-----test begin----");
			driver.navigate().to(this.testEnv);
		} catch (Exception e) {
			tearDown();
			System.exit(1);
		}
		System.out.println("setup done");
	}

	public void slashdotPageTest() throws InterruptedException {
		mainPage.countArticle(driver);
		mainPage.countIcon(driver);
		int randomNum = mainPage.chooseRandomOption(driver);
		int numberOfSameVoted = pollBooth.countNumberOfSameVoted(driver, randomNum);
		System.out.println("Number of same vote :" + numberOfSameVoted );
	}

	public static void main(String[] args) throws Exception {
		SlashdotTest test = new SlashdotTest();
		test.setTestEnv(SLASHDOT);
		test.setHomeLocation("./");
		test.setTestPlatform("Mac");
		test.setup();
		test.slashdotPageTest();
		test.tearDown();
	}
}