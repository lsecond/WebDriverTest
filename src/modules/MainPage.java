package modules;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	
	public void countArticle(WebDriver driver) {
		ArrayList<WebElement> articleElements =  (ArrayList<WebElement>) (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.className("article")));
		System.out.println(String.format("There are %d articles on the main page", articleElements.size()));
	}
	
	public void countIcon(WebDriver driver) {
		Map<String, Integer> iconMap = new HashMap<String, Integer>();
		ArrayList<WebElement> titleIconElements =  (ArrayList<WebElement>) (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath("//span[@class='topic']/a/img")));
		for(WebElement icon: titleIconElements){
			String iconTitle = icon.getAttribute("title");
			Integer count = iconMap.get(iconTitle);
			iconMap.put(iconTitle, (count == null) ? 1 : count + 1);
		}
		if(iconMap.isEmpty()){
			System.out.println("Unable to find any icon");
		} else {
			for (Map.Entry<String, Integer> entry : iconMap.entrySet()) { 
				System.out.println(String.format("'%s' icon have been used %d time on the page", entry.getKey(),entry.getValue()));
			}
		}
	}
	
	public int chooseRandomOption(WebDriver driver) throws InterruptedException {
		final Random r = new Random(); 
		ArrayList<WebElement> radioElements =  (ArrayList<WebElement>) (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath("//form[@id='pollBooth']/label/input")));
		int randomNum = r.nextInt(radioElements.size());
		WebElement randomChoice =  radioElements.get(randomNum);
		Thread.sleep(2000);
		randomChoice.click();
		WebElement voteNow = (WebElement) (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//form[@id='pollBooth']/div/button")));
		voteNow.click();
		return randomNum;
	}
}
