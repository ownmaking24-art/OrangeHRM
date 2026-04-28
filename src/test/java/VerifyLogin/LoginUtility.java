package VerifyLogin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUtility {
 public 	WebDriver driver;
 ChromeOptions options;
 WebDriverWait wait;
 JavascriptExecutor js;

	public LoginUtility ()
	{	
		options = new ChromeOptions();
		doDisableChromeNotifs();
		this.driver= new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public WebElement doFindElement(By locator) 
	{
		return driver.findElement(locator);
	}
	public List<WebElement> doFindElements(By locator) 
	{
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public void doSendKeys(By locator, String value) 
	{
		doFindElement(locator).sendKeys(value);
	}
	public void doClick(By locator) 
	{
		doFindElement(locator).click();
	}
	//Overloaded method
	public void doClick(WebElement element) 
	{
		wait.until(ExpectedConditions.visibilityOf(element)).click();;
	}
	public void doGetURL(String url) 
	{
		driver.get(url);
	}
	public String doGetCurrentUrl() 
	{
		wait.until(driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState")
				.equals("complete"));
		return driver.getCurrentUrl();
	}
	public void isAlertPresent() 
	{
		try {
			(driver.switchTo().alert()).accept();
		}catch (Exception e)
		{}
	}
	public void doDisableChromeNotifs() 
	{
		options.addArguments("--disable-notifications");
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);

		options.setExperimentalOption("prefs", prefs);
	}
	public void doQuit() 
	{
		driver.quit();
	}
	public String doGetParentWindow() 
	{
		return driver.getWindowHandle();
	}
	public void switchToWindow(String Window) 
	{
		driver.switchTo().window(Window);
	}
	public void doSwitchtoChildWindow(String pw) 
	{
		Set<String> windowSet= driver.getWindowHandles();
		for(String window: windowSet) 
		{
			if (!window.equals(pw)) 
			{
				driver.switchTo().window(window);
				return;
			}
		}
		return;
		
	}
	public void close() {
		driver.close();
		
	}
}
