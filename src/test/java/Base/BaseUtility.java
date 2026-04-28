package Base;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseUtility{
	public WebDriver driver;
	public BaseUtility(WebDriver driver) {
		this.driver=driver;
	}
	public void getUrl(String urlString) 
	{
		driver.get(urlString);
	}
	public WebElement doFindElement(By locator) 
	{
		return driver.findElement(locator);
	}
	public List<WebElement> doFindElements(By locator) 
	{
		return driver.findElements(locator);
	}
	public void doSendKeys(By locator, String value) 
	{
		doFindElement(locator).sendKeys(value);
	}
	public void doClick(By locator) 
	{
		doFindElement(locator).click();
	}
	public void doClick(WebElement element) 
	{
		element.click();
	}
	public void doClickAll(By locator) 
	{
		int size= doFindElements(locator).size();
		for(int i=0;i<size;i++) 
		{
			List<WebElement>ele = driver.findElements(locator);
			ele.get(i).click();
		}
	}
}
