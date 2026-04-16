package VerifyLogin;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginVerify  {
	LoginUtility loginutility;
	SheetUtility xl;
	By email;
	By pass;
	String exp_url="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
	String actul_url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	ScreenshotUtility ss;
	@BeforeMethod
	public void setup() throws IOException
	{
		loginutility= new LoginUtility();
		ss= new ScreenshotUtility(loginutility.driver);
		xl= new SheetUtility("E:\\SeleniumPrgSS\\Book4.xlsx");
		loginutility.doGetURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		email= By.name("username");
		pass= By.name("password");
		
	}
	@AfterMethod
	public void teardown() throws IOException 
	{
		loginutility.doQuit();
		xl.close();
	}
	
	@Test (priority = 7)
	public void validIdPass() 
	{
		try 
		{
			loginutility.doSendKeys(email, xl.getUsername(0, 6));
			loginutility.doSendKeys(pass, xl.getPass(0, 6));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginutility.doClick(By.xpath("//button[@type='submit']"));
		System.out.println(loginutility.doGetCurrentUrl());
		Assert.assertTrue(loginutility.doGetCurrentUrl().equals(exp_url));
	}
	@Test (priority = 6)
	public void invalidId() 
	{
		try {
			loginutility.doSendKeys(email, xl.getUsername(0, 5));
			loginutility.doSendKeys(pass, xl.getPass(0, 5));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginutility.doClick(By.xpath("//button[@type='submit']"));
		
		if (loginutility.doFindElements(By.xpath("//p[text()='Invalid credentials']")).size()>0) 
		{
			Assert.assertTrue(true);
		}
		else 
		{
			Assert.fail();
		}
		
	}
	@Test (priority = 5)
	public void invalidPass() 
	{
		try {
			loginutility.doSendKeys(email, xl.getUsername(0, 4));
			loginutility.doSendKeys(pass, xl.getPass(0, 4));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginutility.doClick(By.xpath("//button[@type='submit']"));
		if (loginutility.doFindElements(By.xpath("//p[text()='Invalid credentials']")).size()>0) 
		{
			Assert.assertTrue(true);
		}
		else 
		{
			Assert.fail();
		}

	}
	@Test (priority = 4)
	public void invalidIdPass() 
	{
		try {
			loginutility.doSendKeys(email, xl.getUsername(0, 3));
			loginutility.doSendKeys(pass, xl.getPass(0, 3));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginutility.doClick(By.xpath("//button[@type='submit']"));
		System.out.println(loginutility.doFindElements(By.xpath("//p[text()='Invalid credentials']")).size());
		if (loginutility.doFindElements(By.xpath("//p[text()='Invalid credentials']")).size()>0) 
		{
			Assert.assertTrue(true);
		}
		else 
		{
			Assert.fail();
		}
		
	}
	@Test (priority = 3)
	public void blankId() 
	{
		try {
			loginutility.doSendKeys(email, xl.getUsername(0, 2));
			loginutility.doSendKeys(pass, xl.getPass(0, 2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loginutility.doClick(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(loginutility.doFindElement(By.xpath("//span[text()='Required']")).isDisplayed());
	}
	@Test (priority = 2)
	public void blankPass() 
	{
		try {
			loginutility.doSendKeys(email, xl.getUsername(0, 1));
			loginutility.doSendKeys(pass, xl.getPass(0, 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginutility.doClick(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(loginutility.doFindElement(By.xpath("//span[text()='Required']")).isDisplayed());
		
	}
	@Test(priority = 1)
	public void checkLinks() throws IOException 
	{
		String pw= loginutility.doGetParentWindow();
		URL url;
		List<String> urlsList= new ArrayList<>();
		int i=0;
		urlsList.add("https://www.linkedin.com/company/orangehrm/");
		urlsList.add("https://www.facebook.com/OrangeHRM/");
		urlsList.add("https://x.com/orangehrm?lang=en");
		urlsList.add("https://www.youtube.com/c/OrangeHRMInc");
		List<WebElement> tabs= loginutility.doFindElements(By.xpath("//div[@class='orangehrm-login-footer-sm']//a"));
		for(WebElement element: tabs) 
		{
			loginutility.doClick(element);
			loginutility.doSwitchtoChildWindow(pw);
			if(loginutility.doGetCurrentUrl().equals(urlsList.get(i))) 
			{
				loginutility.close();
				loginutility.switchToWindow(pw);
			}
			else 
			{
				url= new URL(loginutility.doGetCurrentUrl());
				String hostnameString= url.getHost().replace('.', '_');
				ss.takeSS(hostnameString);
				loginutility.close();
				loginutility.switchToWindow(pw);
			}
			i++;
		}
		Assert.assertTrue(ss.checkDirectory()==true);
		
		
	}
}
	

