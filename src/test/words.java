package test;

import modules.*;
import net.sourceforge.htmlunit.corejs.javascript.ast.NewExpression;
import sun.util.logging.resources.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * @author John L
 * @version 1.0
 * 
 */
public class words {

	public WebDriver driver;
	private String testEnv;
	private String testPlatform;
	private String homeLocation;
	private MainPage mainPage;
	private PollBooth pollBooth;
	private static final String SLASHDOT = "http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex4e.htm";

	private String chrome_driver;
	
	public words() {
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
		List<WebElement> onElements = driver.findElements(By.xpath("//tbody[@id='Questions']/tr/td[2]/select"));
		int size = onElements.size();
		WebElement check = driver.findElement(By.xpath("//*[@id='CheckButton1']"));
		//WebElement three = driver.findElement(By.xpath("//*[@id='s2_2']"));
//		three.click();
//		Thread.sleep(3000);
		ArrayList<String> answer = new ArrayList<>();
		ArrayList<String> answerPrint = new ArrayList<>();
		for(int j= 0; j<=9; j++){
			//*[@id="s8_8"]
			WebElement element = driver.findElement(By.xpath("//*[@id='s"+ j + "_" + j + "']"));
			
			//org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(element);
			for(int i=1;i<12;i++){
				//element.click();
				//*[@id="s8_8"]/option[2]
				WebElement option = driver.findElement(By.xpath("//*[@id='s"+ j + "_" + j + "']/option[" + i +"]"));
				if(answer.contains(option.getText().trim())){
					
				} else {
					option.click();
					Thread.sleep(1000);
					check.click();
					Thread.sleep(1000);
					WebElement ok = driver.findElement(By.xpath("//*[@id='FeedbackOKButton']"));
					ok.click();
					Thread.sleep(1000);
					int m = j+1;
				
					WebElement question = driver.findElement(By.xpath("//*[@id='Questions']/tr[" + m + "]/td[1]"));
					WebElement right = driver.findElement(By.xpath("//*[@id='Questions']/tr[" + m + "]/td[2]"));
					//*[@id="Questions"]/tr[10]/td[2]
					if(right.getText().length()>20){
						
					} else {
						answer.add(right.getText().trim());
						answerPrint.add(question.getText() + "/ answer:  " + right.getText().trim());
						System.out.println(question.getText() + "/ answer:  " + right.getText().trim());
						break;
					}
				}
				
				
			}
			
		}

	}

	public static void main(String[] args) throws Exception {
		words test = new words();
		ArrayList<String> pages = new ArrayList<>();
		
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex1a.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex1b.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex1c.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex1d.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex1e.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex3a.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex3b.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex3c.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex3d.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex3e.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex5a.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex5b.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex5c.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex5d.htm");
		pages.add("http://www.englishvocabularyexercises.com/AWL/AWLSublist03-Ex5e.htm");
		
		test.setHomeLocation("./");
		test.setTestPlatform("Mac");
		for(String page: pages){
			test.setTestEnv(page);
			test.setup();
			test.slashdotPageTest();
			test.tearDown();
		}
		
	}
}