package modules;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PollBooth {
	public int countNumberOfSameVoted(WebDriver driver,int randomNum) {
		int count = 0;
		ArrayList<WebElement> pollBarTextElements =  (ArrayList<WebElement>) (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath("//div[@class='poll-bar-text']")));
		String votes = pollBarTextElements.get(randomNum).getText();
		if(!votes.isEmpty()){
			try {
				 count = Integer.parseInt(votes.split(" ")[0].trim());
			} catch (NumberFormatException e) {
				System.out.println("Parse error:" + e.toString());
			}
		}
		return count;
	}
}
